package ru.bardinpetr.itmo.lab5.clientgui.utils.presenters;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;

import java.util.ArrayList;
import java.util.List;

@Data
public class EnumPresenter<T extends Enum<T>> implements Comparable<EnumPresenter> {
    private final Enum<T> enumData;

    public EnumPresenter(Enum<T> enumData) {
        this.enumData = enumData;
    }

    public static List<EnumPresenter> fromList(List<Enum> dataList) {
        ArrayList<EnumPresenter> resList = new ArrayList<>();
        for (var i : dataList) {
            resList.add(
                    new EnumPresenter(i)
            );
        }
        return resList;
    }

    @Override
    public String toString() {
        if (enumData == null)
            return UIResources.getInstance()
                    .get("WorkerInfoPanel.endDateNull.text");
        return UIResources.getInstance()
                .get("EnumPresenter.value.%s.text".formatted(enumData.name().toLowerCase()));
    }

    @Override
    public int compareTo(EnumPresenter o) {
        if (o.enumData == null) {
            if (enumData == null) return 0;
            return 1;
        }
        if (enumData == null) return -1;
        return enumData.compareTo((T) o.enumData);
    }
}
