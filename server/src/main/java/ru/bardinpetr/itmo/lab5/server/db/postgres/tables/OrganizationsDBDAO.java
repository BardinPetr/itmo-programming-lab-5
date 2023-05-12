package ru.bardinpetr.itmo.lab5.server.db.postgres.tables;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;
import ru.bardinpetr.itmo.lab5.server.db.dto.OrganizationDTO;
import ru.bardinpetr.itmo.lab5.server.db.errors.DBCreateException;
import ru.bardinpetr.itmo.lab5.server.db.postgres.BaseDBDAO;
import ru.bardinpetr.itmo.lab5.server.db.postgres.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static ru.bardinpetr.itmo.lab5.server.db.utils.RowSetUtils.rowSetStream;

@Slf4j
public class OrganizationsDBDAO extends BaseDBDAO<Integer, OrganizationDTO> {
    public OrganizationsDBDAO(DBConnector connector) throws DBCreateException {
        super(connector, "organization");
    }

    @Override
    public Integer insert(OrganizationDTO data) {
        try (var stmt = connection.prepareStatement("insert into organization values (default, ?, ?::organization_type) returning id")) {
            stmt.setString(1, data.fullName());
            stmt.setString(2, data.type().name());
            var rs = stmt.executeQuery();
            if (!rs.next())
                return null;
            return rs.getInt("id");
        } catch (Exception ex) {
            log.error("insert failed: ", ex);
        }
        return null;
    }

    @Override
    public boolean update(Integer id, OrganizationDTO newData) {
        return false;
    }

    @Override
    public List<OrganizationDTO> select() {
        try (var rs = connector.getRowSet()) {
            rs.setCommand("select * from organization");
            return rowSetStream(rs).map(i -> parseRow(rs)).collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("select failed: ", ex);
        }
        return null;
    }

    @Override
    public OrganizationDTO select(Integer id) {
        try (var stmt = connection.prepareStatement("select * from organization where id=?")) {
            stmt.setInt(1, id);
            var rowSet = stmt.executeQuery();
            if (!rowSet.next()) return null;
            return parseRow(rowSet);
        } catch (Exception ex) {
            log.error("select failed: ", ex);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        try (var stmt = connection.prepareStatement("delete from organization where id=?")) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception ex) {
            log.error("delete failed: ", ex);
        }
        return false;
    }

    @Override
    public OrganizationDTO parseRow(ResultSet rs) {
        try {
            return new OrganizationDTO(rs.getInt("id"), rs.getString("fullName"), OrganizationType.valueOf(rs.getString("type")));
        } catch (SQLException e) {
            log.error("row parse failed at {}", tableName, e);
            return null;
        }
    }
}
