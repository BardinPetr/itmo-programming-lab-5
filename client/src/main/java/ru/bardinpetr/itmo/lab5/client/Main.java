package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.dataException.WrongDataException;


public class Main {
    public static void main(String[] args) {
        var c1 = Coordinates.builder().x(2).y(2321).build();
        try {
            c1.check();
            System.out.println("Successful");
        } catch (WrongDataException e) {
            System.out.println(e.getExceptionInfo());
        }
    }
}
