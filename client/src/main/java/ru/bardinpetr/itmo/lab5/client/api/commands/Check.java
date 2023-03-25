package ru.bardinpetr.itmo.lab5.client.api.commands;

import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ConsolePrinter;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.ObjectScanner;
import ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors.ParserException;
import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Check {


    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, ParserException {
        var objectScanner = new ObjectScanner(
                new DescriptionHolder(new Class[]{
                        Worker.class,
                        Coordinates.class,
                        Organization.class
                }), new ConsolePrinter(),
                new Scanner(System.in));

        objectScanner.scan(Worker.class, null);
    }
}
