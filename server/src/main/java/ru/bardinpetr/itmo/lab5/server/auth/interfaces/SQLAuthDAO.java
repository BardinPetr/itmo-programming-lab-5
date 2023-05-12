package ru.bardinpetr.itmo.lab5.server.auth.interfaces;

import ru.bardinpetr.itmo.lab5.server.auth.models.AuthorizationObject;

public interface SQLAuthDAO {
    boolean checkUser(String userName);

    void insert(AuthorizationObject authorizationObject);

    AuthorizationObject getByUserName(String userName);
}
