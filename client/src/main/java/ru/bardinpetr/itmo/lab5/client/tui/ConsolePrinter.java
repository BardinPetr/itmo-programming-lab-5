package ru.bardinpetr.itmo.lab5.client.tui;

/**
 * Class for writing in console
 */
@Deprecated
public class ConsolePrinter implements Printer {
    public void show(String str) {
        System.out.println(str);
    }
    @Override
    public void suggestInput() {
        System.out.print("> ");
    }
    public void showLine(String str) {
        System.out.print(str);
    }
}
