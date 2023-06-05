package ru.bardinpetr.itmo.lab5.server.db.dao.tables;

import ru.bardinpetr.itmo.lab5.db.backend.IDBDAO;
import ru.bardinpetr.itmo.lab5.db.frontend.dao.ITableDAO;
import ru.bardinpetr.itmo.lab5.server.db.dao.exception.OverLimitedUsername;
import ru.bardinpetr.itmo.lab5.server.db.dto.UserDTO;

public interface UsersDAO extends ITableDAO, IDBDAO<Integer, UserDTO> {
    UserDTO selectByUsername(String username) throws OverLimitedUsername;
}
