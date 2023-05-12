package ru.bardinpetr.itmo.lab5.server.db.postgres.tables;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.server.db.errors.DBCreateException;
import ru.bardinpetr.itmo.lab5.server.db.postgres.BaseDBDAO;
import ru.bardinpetr.itmo.lab5.server.db.postgres.DBConnector;

import java.sql.SQLException;
import java.util.Collection;

@Slf4j
public class WorkersDBDAO extends BaseDBDAO<Integer, Worker> {

    public WorkersDBDAO(DBConnector connector) throws DBCreateException {
        super(connector, "worker");
    }

    @Override
    public boolean createTable() {
        try {
            connection.prepareStatement(
                    """
                            CREATE TABLE worker
                            (
                                
                            );
                            """
            );
        } catch (SQLException e) {
            log.error("");
            return false;
        }
        return true;
    }

    @Override
    public boolean insert(Worker data) {
        return false;
    }

    @Override
    public boolean update(Integer id, Worker newData) {
        return false;
    }

    @Override
    public Collection<Worker> select() {
        return null;
    }

    @Override
    public Worker select(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
