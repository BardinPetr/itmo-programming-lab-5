package ru.bardinpetr.itmo.lab5.models.commands.base.resonses;

import java.util.List;

public class ListCommandResponse<T> implements ICommandResponse {
    private List<T> result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }


    @Override
    public String getUserMessage() {
        return getResult().toString();
    }
}
