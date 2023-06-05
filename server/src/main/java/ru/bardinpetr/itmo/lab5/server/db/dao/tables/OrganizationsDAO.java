package ru.bardinpetr.itmo.lab5.server.db.dao.tables;

import ru.bardinpetr.itmo.lab5.db.backend.IDBDAO;
import ru.bardinpetr.itmo.lab5.db.frontend.dao.ITableDAO;
import ru.bardinpetr.itmo.lab5.server.db.dto.OrganizationDTO;

public interface OrganizationsDAO extends ITableDAO, IDBDAO<Integer, OrganizationDTO> {
}