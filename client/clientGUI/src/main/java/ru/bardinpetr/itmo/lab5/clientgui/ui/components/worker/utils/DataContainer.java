package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils;

import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;


public class DataContainer <T> {
    public boolean isAllowed;
    public String msg;
    public T data;

    public DataContainer(boolean isAllowed, T data, String msg) {
        this.isAllowed = isAllowed;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataContainer{" +
                "isAllowed=" + isAllowed +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public void copyMeta(DataContainer container){
        isAllowed = container.isAllowed;
        msg = container.msg;
    }

    public DataContainer(T data, ValidationResponse resp){
        this(resp.isAllowed(), data, resp.getMsg());
    }
}
