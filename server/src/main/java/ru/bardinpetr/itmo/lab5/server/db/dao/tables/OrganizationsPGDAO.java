package ru.bardinpetr.itmo.lab5.server.db.dao.tables;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.db.backend.impl.postgres.BasePGDAO;
import ru.bardinpetr.itmo.lab5.db.backend.impl.postgres.PGDBConnector;
import ru.bardinpetr.itmo.lab5.db.errors.DBCreateException;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;
import ru.bardinpetr.itmo.lab5.server.db.dto.OrganizationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class OrganizationsPGDAO extends BasePGDAO<Integer, OrganizationDTO> {
    public OrganizationsPGDAO(PGDBConnector connector) throws DBCreateException {
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
    public OrganizationDTO parseRow(ResultSet rs) {
        try {
            return new OrganizationDTO(rs.getInt("id"), rs.getString("fullName"), OrganizationType.valueOf(rs.getString("type")));
        } catch (SQLException e) {
            log.error("row parse failed at {}", tableName, e);
            return null;
        }
    }
}
