package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields.interfaces.AbstractWorkerComboBox;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.models.data.Position;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;

import java.util.function.Consumer;

public class PositionComboBox extends AbstractWorkerComboBox<Position> {
    public PositionComboBox(Consumer<Position> handler) {
        super(handler);
    }
    @Override
    protected void groupItems() {
        addItem("");
        for (var i: Position.values()){
            var text = bundle.getString(
                    "WorkerInfoPanel.position.%s.text".formatted(i.toString().toLowerCase())
            );
            positionMap.put(text, i);
            addItem(text);
        }
    }

    private String getPosText(Position pos){
        for( var i:positionMap.entrySet()){
            if (i.getValue() == pos) return i.getKey();
        }
        return "";
    }
    @Override
    public DataContainer<Position> getData() {
        return new DataContainer(true,
                positionMap.get(((String) getSelectedItem())),
                ""
        );
    }

    @Override
    public void setData(Position pos){
        setSelectedItem(
                getPosText(pos)
        );
    }

    @Override
    public ValidationResponse validateValue() {
        return new ValidationResponse(
                true,
                ""
        );
    }

}
