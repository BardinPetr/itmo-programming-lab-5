package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.models.commands.*;


public class Main {
    public static void main(String[] args) {
        Command command = new AddCommand();
        System.out.println(command.getTYPE());
    }
}
