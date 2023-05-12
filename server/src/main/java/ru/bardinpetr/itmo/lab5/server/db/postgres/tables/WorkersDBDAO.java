package ru.bardinpetr.itmo.lab5.server.db.postgres.tables;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.server.db.dto.WorkerDTO;
import ru.bardinpetr.itmo.lab5.server.db.errors.DBCreateException;
import ru.bardinpetr.itmo.lab5.server.db.postgres.BaseDBDAO;
import ru.bardinpetr.itmo.lab5.server.db.postgres.DBConnector;

import java.sql.ResultSet;
import java.util.Collection;

@Slf4j
public class WorkersDBDAO extends BaseDBDAO<Integer, WorkerDTO> {

    public WorkersDBDAO(DBConnector connector) throws DBCreateException {
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
    public Collection<WorkerDTO> select() {
        return null;
    }

    @Override
    public WorkerDTO select(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public WorkerDTO parseRow(ResultSet rs) {
        return null;
    }
}
