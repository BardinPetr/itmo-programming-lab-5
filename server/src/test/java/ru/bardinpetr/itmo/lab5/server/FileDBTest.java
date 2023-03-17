package ru.bardinpetr.itmo.lab5.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bardinpetr.itmo.lab5.common.io.FileIOController;
import ru.bardinpetr.itmo.lab5.common.io.exceptions.FileAccessException;
import ru.bardinpetr.itmo.lab5.common.serdes.XMLSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.server.db.filedb.FileDBController;
import ru.bardinpetr.itmo.lab5.server.utils.TestEntity;
import ru.bardinpetr.itmo.lab5.server.utils.TestEntityCollection;

import java.io.File;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileDBTest {
    public static final Class<TestEntityCollection> TEST_COLLECTION = TestEntityCollection.class;
    public static final Class<TestEntity> TEST_ENTITY = TestEntity.class;
    private final XMLSerDesService<TestEntityCollection> serDesService = new XMLSerDesService<>(TEST_COLLECTION);

    @Test
    @DisplayName("FileDB initialization in various environments")
    void initializeTest() {
        var normalDir = FileUtils.createDir("rwx");

        var nonexistentPath = normalDir.resolve("test.dat").toString();
        validateEmptyCollection("in normal environment", nonexistentPath);

        var file = FileUtils.createFile(normalDir, "rw-");
        FileUtils.write(file, "not_an_xml_at_all");
        validateEmptyCollection("with invalid file", nonexistentPath);

        file = FileUtils.createFile(normalDir, "rw-");
        var testCol = new TestEntityCollection();
        testCol.add(new TestEntity(234));

        String serializedData;
        try {
            serializedData = new String(serDesService.serialize(testCol));
        } catch (SerDesException e) {
            throw new RuntimeException(e);
        }
        serializedData = serializedData.replaceAll("id", "uid"); // break some field's name
        FileUtils.write(file, serializedData);

        validateEmptyCollection("with valid xml but no integrity", nonexistentPath);
    }

    /**
     * Check that database could be initialized with this file when it doesn't exist of invalid.
     * File is deleted automatically
     *
     * @param taskText notice for asserts of in what environment this function run
     * @param dbPath   path to database file
     */
    private void validateEmptyCollection(String taskText, String dbPath) {
        FileDBController<?> ctrl = null;
        try {
            ctrl = new FileDBController<>(new FileIOController(dbPath), TEST_COLLECTION);
        } catch (FileAccessException e) {
            fail("File controller should be successfully initialized in normal environment");
        }

        var clearData = ctrl.data();
        assertNotNull(clearData, taskText + " Autogenerated empty collection should exist");
        assertEquals(TEST_COLLECTION, clearData.getClass(), taskText + " Collection had been read should be of valid type");
        assertEquals(0, ((Collection<?>) clearData).size(), taskText + "new collection should be empty");

        var file = new File(dbPath);
        var realFileData = FileUtils.read(file).getBytes();

        Object deserialized = null;
        try {
            deserialized = serDesService.deserialize(realFileData);
        } catch (SerDesException e) {
            fail(taskText + " Contents of real file is not an valid XML or of other schema");
        }

        assertEquals(clearData, deserialized, taskText + " created empty collection should be stored to file");

        FileUtils.delete(file);
    }

    /**
     * Creates FileDBController with checks
     *
     * @param path db path
     * @return controller
     */
    private FileDBController<TestEntityCollection> initDBAsserted(String path) {
        FileDBController<TestEntityCollection> ctrl = null;
        try {
            ctrl = new FileDBController<>(new FileIOController(path), TestEntityCollection.class);
        } catch (FileAccessException e) {
            fail("in normal environment initialization should succeed");
            return null;
        }
        return ctrl;
    }

    @Test
    @DisplayName("Valid collection load/store")
    void loadStoreTest() {
        var normalDir = FileUtils.createDir("rwx");
        var file = FileUtils.createFile(normalDir, "rw-");
        var path = file.getPath();

        var demoCollection = new TestEntityCollection();
        demoCollection.addAll(List.of(
                new TestEntity(1),
                new TestEntity(3),
                new TestEntity(2)));


        // Try store data to db and check loaded values
        var ctrl = initDBAsserted(path);
        if (ctrl == null) return;

        ctrl.data().addAll(demoCollection);

        boolean res = ctrl.store();
        assertTrue(res, "Storing to file should succeed if it was not altered and all permissions set");


        assertDoesNotThrow(
                () -> {
                    var fileData = serDesService.deserialize(FileUtils.read(file).getBytes());
                    assertEquals(demoCollection, fileData, "Written data should be as set with .data()");
                },
                "serialized data should be valid XML and follow schema"
        );


        // Try to read with other controller
        var ctrlReceiver = initDBAsserted(path);
        if (ctrlReceiver == null) return;

        assertEquals(ctrlReceiver.data(), demoCollection,
                "Loading db from existing file should load collection to local object");
    }

    @Test
    @DisplayName("FileDB should handle situations when file is deleted or altered when controlled already running")
    void inProcessFileBreakTest() {
        var normalDir = FileUtils.createDir("rwx");
        var file = FileUtils.createFile(normalDir, "rw-");
        var path = file.getPath();

        var demoEntity1 = new TestEntity(1);
        var demoEntity2 = new TestEntity(2);

        // Try store data to db and check loaded values
        var ctrl = initDBAsserted(path);
        if (ctrl == null) return;

        ctrl.data().add(demoEntity1);
        assertTrue(ctrl.store(), "store should succeed in normal situation");

        var ctrlNext = initDBAsserted(path);
        if (ctrlNext == null) return;
        assertTrue(ctrlNext.data().contains(demoEntity1), "pretest that in normal environment everything works");

        FileUtils.delete(file);

        // Try working offline
        ctrl.data().add(demoEntity2);
        assertTrue(ctrlNext.data().contains(demoEntity1), "operations on collection should not depend on files");

        assertTrue(ctrl.store(), "Store should succeed even if file was removed");

        // Check with other controller that external valid changes could be received
        assertDoesNotThrow(ctrlNext::load, "existing db should be able to be read");
        boolean check = ctrlNext.data().containsAll(List.of(demoEntity1, demoEntity2));
        assertTrue(check, "DB instance should load data on load() command from file regardless current local state or file-related reasons");

        // Try saving/loading without permissions
        file.setWritable(false);
        assertDoesNotThrow(
                () -> assertFalse(ctrl.store(), "Store procedure should indicate with a return value that changes could not be commited"),
                "Saving to file without W-permission should not crash"
        );
        file.setWritable(true);

        var originalContent = FileUtils.read(file);

        file.setReadable(false);
        assertThrows(FileAccessException.class, ctrl::load,
                "Loading from file without permissions should fail");

        file.setReadable(true);
        assertEquals(FileUtils.read(file), originalContent,
                "When file do not have read permissions only it should NOT be altered in any way");
    }
}
