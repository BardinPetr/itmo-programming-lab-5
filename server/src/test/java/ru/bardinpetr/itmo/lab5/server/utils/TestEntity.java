package ru.bardinpetr.itmo.lab5.server.utils;

import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

public class TestEntity implements IKeyedEntity<Integer>, Comparable<TestEntity> {
    private Integer id;

    public TestEntity() {
    }

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
    public int compareTo(TestEntity o) {
        return id.compareTo(o.id);
    }
}
