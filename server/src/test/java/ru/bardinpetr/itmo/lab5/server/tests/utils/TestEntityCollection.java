package ru.bardinpetr.itmo.lab5.server.tests.utils;

import ru.bardinpetr.itmo.lab5.models.data.collection.ISetCollection;

import java.util.TreeSet;

public class TestEntityCollection extends TreeSet<TestEntity> implements ISetCollection<Integer, TestEntity> {

}
