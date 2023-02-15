package ru.bardinpetr.itmo.lab5.models.data;

import ru.bardinpetr.itmo.lab5.models.data.collection.ISetCollection;

import java.util.TreeSet;

public class WorkerCollection extends TreeSet<Worker> implements ISetCollection<Long, Worker> {
}
