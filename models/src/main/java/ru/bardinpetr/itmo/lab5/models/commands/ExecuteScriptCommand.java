package ru.bardinpetr.itmo.lab5.models.commands;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

//TODO где файл загружать?
@Data
public class ExecuteScriptCommand extends Command{
    @JsonIgnore public final String TYPE = "execute_script";
    public String fileName;

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public Field[] getInlineArgs(){
        return new Field[]{
                new Field("file_name", String.class)
        };
    }

}
