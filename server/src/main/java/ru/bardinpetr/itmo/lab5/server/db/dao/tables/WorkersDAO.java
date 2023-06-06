package ru.bardinpetr.itmo.lab5.server.db.dao.tables;

import ru.bardinpetr.itmo.lab5.db.backend.IDBDAO;
import ru.bardinpetr.itmo.lab5.db.frontend.dao.ITableDAO;
import ru.bardinpetr.itmo.lab5.server.db.dto.WorkerDTO;

public interface WorkersDAO extends ITableDAO, IDBDAO<Integer, WorkerDTO> {
}
