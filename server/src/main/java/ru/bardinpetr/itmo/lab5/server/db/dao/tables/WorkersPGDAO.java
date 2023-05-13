package ru.bardinpetr.itmo.lab5.server.db.dao.tables;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.db.backend.impl.postgres.BasePGDAO;
import ru.bardinpetr.itmo.lab5.db.backend.impl.postgres.PGDBConnector;
import ru.bardinpetr.itmo.lab5.db.errors.DBCreateException;
import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import ru.bardinpetr.itmo.lab5.server.db.dto.WorkerDTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

@Slf4j
public class WorkersPGDAO extends BasePGDAO<Integer, WorkerDTO> {

    public WorkersPGDAO(PGDBConnector connector) throws DBCreateException {
        super(connector, "worker");
    }

    private void setPreparedStatement(PreparedStatement s, WorkerDTO data) throws SQLException {
        s.setInt(1, data.ownerId());
        s.setInt(2, data.organizationId());
        s.setString(3, data.name());
        s.setFloat(4, data.salary());
        s.setDate(5, new Date(data.startDate().getTime()));
        if (data.endDate() != null)
            s.setDate(6, Date.valueOf(data.endDate()));
        else
            s.setDate(6, null);

        s.setString(7, "(%s, %s)".formatted(data.coordinates().getX(), data.coordinates().getY()));
        s.setString(8, data.position().name());

    }

    @Override
    public Integer insert(WorkerDTO data) {
        try {
            var s = connection.prepareStatement(
                    """
                            INSERT INTO worker
                            VALUES
                            	(default,default, ?, ?, ?, ?, ?, ?, ?::worker_coordinates, ?::worker_position) returning id
                            """
            );
            setPreparedStatement(s, data);
            var rs = s.executeQuery();
            if (!rs.next())
                return null;
            return rs.getInt("id");
        } catch (SQLException e) {
            log.error("Can't insert into users table", e);
            return null;
        }

    }


    @Override
    public boolean update(Integer id, WorkerDTO newData) {
        try {
            var s = connection.prepareStatement(
                    """
                            UPDATE worker
                            SET     ownerId= ?,
                                    organizationID= ?,
                                    name= ?,
                                    salary= ?,
                                    startDate= ?,
                                    endDate= ?,
                                    coordinates= ?::worker_coordinates,
                                    position= ?::worker_position

                            WHERE id = ?;
                            """
            );
            setPreparedStatement(s, newData);
            s.setInt(9, id);
            return s.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Can't insert into users table", e);
            return false;
        }
    }

    @Override
    public WorkerDTO parseRow(ResultSet rs) {
        try {
            LocalDate endDate;
            if (rs.getTimestamp("endDate") != null) {
                endDate = rs.getTimestamp("endDate").toLocalDateTime().toLocalDate();
            } else endDate = null;

            return new WorkerDTO(
                    rs.getInt("id"),
                    rs.getInt("ownerId"),
                    rs.getInt("organizationId"),
                    rs.getTimestamp("creationDate").toInstant().atZone(ZoneId.systemDefault()),
                    rs.getTimestamp("startDate"),
                    endDate,
                    rs.getString("name"),
                    rs.getFloat("salary"),
                    Coordinates.fromString(rs.getString("coordinates")),
                    Position.valueOf(rs.getString("position")));
        } catch (SQLException e) {
            log.error("row parse failed at {}", tableName, e);
            return null;
        }
    }
}