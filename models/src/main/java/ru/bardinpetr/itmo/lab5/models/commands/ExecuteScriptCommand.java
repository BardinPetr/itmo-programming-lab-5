package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;

//TODO где файл загружать?
@Data
public class ExecuteScriptCommand extends Command{
    public final String TYPE = "execute_script";
    public String fileName;
}
