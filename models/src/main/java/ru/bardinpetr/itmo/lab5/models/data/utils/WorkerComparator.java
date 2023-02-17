package ru.bardinpetr.itmo.lab5.models.data.utils;

import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.util.Comparator;

public class WorkerComparator implements Comparator<Worker> {
    @Override
    public int compare(Worker o1, Worker o2) {
        return o1.compareTo(o2);
    }
}
