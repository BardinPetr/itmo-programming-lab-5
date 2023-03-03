package ru.bardinpetr.itmo.lab5.server.tests.utils;

import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

public class TestEntity implements IKeyedEntity<Integer> {
    private Integer id;

    public TestEntity(Integer id) {
        this.id = id;
    }

    public static TestEntity create() {
        return new TestEntity((int) (Math.random() * 100000));
    }

    @Override
    public Integer getPrimaryKey() {
        return id;
    }

    @Override
    public void setPrimaryKey(Integer key) {
        id = key;
    }
}
