package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.util.Date;

public interface IDataStorage<T> {
    DataContainer<T> getData();
    void setData(T data);
    public ValidationResponse validateValue();
}
