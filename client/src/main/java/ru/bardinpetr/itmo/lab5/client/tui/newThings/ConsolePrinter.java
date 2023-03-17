package ru.bardinpetr.itmo.lab5.client.tui.newThings;

public class ConsolePrinter {
    public void display(String text){
        System.out.println(text);
    }
    public void displayInLine(String text){
        System.out.print(text);
    }
    public void suggestInput(){
        System.out.print("> ");
    }
}
