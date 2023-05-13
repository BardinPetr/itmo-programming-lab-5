package ru.bardinpetr.itmo.lab5.server.db.postgres.tables;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.server.auth.models.AuthorizationObject;
import ru.bardinpetr.itmo.lab5.server.db.errors.DBCreateException;
import ru.bardinpetr.itmo.lab5.server.db.postgres.BaseDBDAO;
import ru.bardinpetr.itmo.lab5.server.db.postgres.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class UsersDAO extends BaseDBDAO<Integer, AuthorizationObject> {
    public UsersDAO(DBConnector connector) throws DBCreateException {
        super(connector, "users");
    }

//    public boolean createTable() {
//        try {
//            var t = connection.prepareStatement(
//                    """
//                            CREATE TABLE users
//                            (
//                                id int generated always as identity PRIMARY KEY,
//                                login varchar(50) UNIQUE NOT NULL,
//                                password bytea NOT NULL,
//                                salt varchar(10) NOT NULL
//                            );
//                            """
//            );
//            return t.executeUpdate() > 0;
//        } catch (SQLException e) {
//            log.error("Can't create users table", e);
//            return false;
//        }
//    }

    @Override
    public Integer insert(AuthorizationObject data) {
        if (!data.validate()) return null;
        try {
            var s = connection.prepareStatement(
                    """
                            INSERT INTO users(login, password, salt)
                            VALUES
                            	(?, ?, ?) returning id
                            """
            );
            s.setString(1, data.getUsername());
            s.setBytes(2, data.getHashedPassword());
            s.setString(3, data.getSalt());
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
    public boolean update(Integer id, AuthorizationObject newData) {
        if (!newData.validate()) return false;
        try {
            var s = connection.prepareStatement(
                    """
                            UPDATE users
                            SET login = ?,
                                password= ?,
                                salt= ?
                            WHERE id = ?;
                            """
            );
            s.setString(1, newData.getUsername());
            s.setBytes(2, newData.getHashedPassword());
            s.setString(3, newData.getSalt());
            s.setInt(4, id);
            return s.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Can't insert into users table", e);
            return false;
        }
    }

    @Override
    public Collection<AuthorizationObject> select() {
        try {
            var st = connection.createStatement();
            var s = st.executeQuery("""
                    SELECT * FROM users;
                    """);
            List<AuthorizationObject> res = new ArrayList<>();
            while (s.next()) {
                res.add(new AuthorizationObject(
                        s.getInt("id"),
                        s.getString("login"),
                        s.getBytes("password"),
                        s.getString("salt")
                ));
            }
            return res;
        } catch (SQLException e) {
            log.error("Can't select all data", e);
            return null;
        }
    }

    public AuthorizationObject selectByUsername(String username) {
        if (username.length() > 50) return null;
        try {
            var st = connection.prepareStatement("""
                    SELECT * FROM users
                    WHERE login = ?;
                    """);
            st.setString(1, username);
            var s = st.executeQuery();

            if (s.next())
                return new AuthorizationObject(
                        s.getInt("id"),
                        s.getString("login"),
                        s.getBytes("password"),
                        s.getString("salt")
                );
            else return null;
        } catch (SQLException e) {
            log.error("Can't select all data", e);
            return null;
        }
    }


    @Override
    public AuthorizationObject select(Integer id) {
        try {
            var st = connection.prepareStatement("""
                    SELECT * FROM users
                    WHERE id = ?;
                    """);
            st.setInt(1, id);
            var s = st.executeQuery();
            s.next();
            return new AuthorizationObject(
                    s.getInt("id"),
                    s.getString("login"),
                    s.getBytes("password"),
                    s.getString("salt")
            );
        } catch (SQLException e) {
            log.error("Can't select all data", e);
            return null;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            var t = connection.prepareStatement("""
                    DELETE FROM users
                    WHERE id = ?;
                    """);
            t.setInt(1, id);
            return t.execute();
        } catch (SQLException e) {
            log.error("Can't drop", e);
            return false;
        }
    }

    @Override
    public AuthorizationObject parseRow(ResultSet rs) {
        try {
            if (rs.next())
                return new AuthorizationObject(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getBytes("password"),
                        rs.getString("salt")
                );
            else return null;
        } catch (SQLException e) {
            log.error("Can't parse row");
            return null;
        }
    }


}
