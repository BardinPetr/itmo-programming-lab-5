package ru.bardinpetr.itmo.lab5.server.auth;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.server.db.postgres.tables.UsersDAO;

import java.util.Arrays;

@Slf4j
public class Main {
    public static void main(String[] args) {
//        var dbConnector = new DBConnector(
//                "jdbc:postgresql://172.28.21.75:5432/studs",
//                new BasicAuthProvider("s367079", "aKNKcUmScdpvwhYu")
//        );
//
//        UsersDAO usersDB = null;
//        try {
//            usersDB = new UsersDAO(dbConnector);
//        } catch (DBCreateException e) {
//            log.error("Failed to bootstrap DB", e);
//            System.exit(-1);
//        }
//        usersDB.drop();
//
//
//
//        var ch = new DBAuthenticationReceiver(usersDB);
//        try {
//            RegisterCommand t = new RegisterCommand();
//            t.setCredentials(new DefaultAuthenticationCredentials("Artem".repeat(12121), "12"));
//            System.out.println(ch.register(t));
//
//            t.setCredentials(new DefaultAuthenticationCredentials("Artem2", "34"));
//            ch.register(t);
//        } catch (UserExistsException e) {
//            throw new RuntimeException(e);
//        }
//        showDB(usersDB);
//        System.out.println(ch.authorize(new DefaultAuthenticationCredentials("Artem".repeat(1), "12".repeat(1))));
//        System.out.println(ch.authorize(new DefaultAuthenticationCredentials("Artem", "122")));
//        showDB(usersDB);

    }

    private static void showDB(UsersDAO usersDB) {
        var t = usersDB.select();
        System.out.println("id\tlogin\tpassword\tsalt");
        for (var i : t) {
            System.out.println(
                    i.getId() + "\t" +
                            i.getUsername() + "\t" +
                            Arrays.toString(i.getHashedPassword()).substring(0, 10) + "\t" +
                            i.getSalt()
            );
        }
    }

}
