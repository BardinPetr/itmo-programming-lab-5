package ru.bardinpetr.itmo.lab5.server.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;
import ru.bardinpetr.itmo.lab5.server.tests.utils.TestEntityCollection;

import static org.junit.jupiter.api.Assertions.fail;

public class FileDBTest {


    @Test
    @DisplayName("")
    void initializeTest() {
        var dir = FileUtils.createDir("rwx");
        var file = FileUtils.createFile(dir, "rw-");
        var path = file.getPath();

        FileIOController fileIO;
        try {
            fileIO = new FileIOController(path);
        } catch (FileAccessException e) {
            fail("File controller should be successfully initialized");
            return;
        }

        var ctrl = new FileDBController<>(fileIO, TestEntityCollection.class);
//        var dao = new FileDBWorkersDAO(ctrl);
//
//        dao.g
    }

//    @Test
//    @DisplayName("")
//    void Test() {
//
//    }
//
//    @Test
//    @DisplayName("")
//    void Test() {
//
//    }
//
//    @Test
//    @DisplayName("")
//    void Test() {
//
//    }
}
