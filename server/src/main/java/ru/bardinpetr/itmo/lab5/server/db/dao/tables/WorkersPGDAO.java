package ru.bardinpetr.itmo.lab5.server.db.dao.tables;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.db.backend.impl.postgres.BasePGDAO;
import ru.bardinpetr.itmo.lab5.db.backend.impl.postgres.PGDBConnector;
import ru.bardinpetr.itmo.lab5.db.errors.DBCreateException;
import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import ru.bardinpetr.itmo.lab5.server.db.dto.WorkerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

@Slf4j
public class WorkersPGDAO extends BasePGDAO<Integer, WorkerDTO> {

    public WorkersPGDAO(PGDBConnector connector) throws DBCreateException {
        super(connector, "worker");
    }

    @Override
    public Integer insert(WorkerDTO data) {
        return null;
    }

    @Override
    public boolean update(Integer id, WorkerDTO newData) {
        return false;
    }

    @Override
    public WorkerDTO parseRow(ResultSet rs) {
        try {
            return new WorkerDTO(
                    rs.getInt("id"),
                    rs.getInt("ownerId"),
                    rs.getInt("organizationId"),
                    rs.getTimestamp("creationDate").toInstant().atZone(ZoneId.systemDefault()),
                    rs.getTimestamp("startDate"),
                    rs.getTimestamp("endDate").toLocalDateTime().toLocalDate(),
                    rs.getString("name"),
                    rs.getFloat("salary"),
                    (Coordinates) rs.getObject("coordinates"),
                    Position.valueOf(rs.getString("position")));
        } catch (SQLException e) {
            log.error("row parse failed at {}", tableName, e);
            return null;
        }
    }
}
