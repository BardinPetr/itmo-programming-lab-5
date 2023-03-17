package ru.bardinpetr.itmo.lab5.client.api.commands;

import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.models.data.*;
import ru.bardinpetr.itmo.lab5.models.data.validation.CoordinatesValidation;
import ru.bardinpetr.itmo.lab5.models.data.validation.OrganizationValidation;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerValidation;
import ru.bardinpetr.itmo.lab5.models.fields.FieldWithDesc;

import java.time.LocalDate;
import java.util.*;

/**
 * Class for describing data class's fields with their name, type, prompt message and validation function
 */
public class DescriptionHolder {
    /**
     * Main map with all information for each field: name, type, validation function,
     * nullAble boolean and text for interact
     */
    public static Map<Class<?>, List<FieldWithDesc<?>>> dataDescriptions = new HashMap<>() {{
        put(
                Worker.class,
                new ArrayList<>() {
                    {
                        add(new FieldWithDesc<>(
                                "name",
                                String.class,
                                RussianText.get(TextKeys.NAMEINTERACT),
                                WorkerValidation::validateName,
                                false)
                        );
                        add(new FieldWithDesc<>(
                                "salary",
                                Float.class,
                                RussianText.get(TextKeys.SALARYINTERACT),
                                WorkerValidation::validateSalary,
                                false)
                        );
                        add(new FieldWithDesc<>(
                                        "startDate",
                                        Date.class,
                                        RussianText.get(TextKeys.STARTDAYINTERACT),
                                        WorkerValidation::validateStartDate,
                                        false
                                )
                        );
                        add(new FieldWithDesc<>(
                                        "endDate",
                                        LocalDate.class,
                                        RussianText.get(TextKeys.ENDNDATEINTERACT),
                                        true
                                )
                        );
                        add(new FieldWithDesc<>(
                                "coordinates",
                                Coordinates.class,
                                RussianText.get(TextKeys.COORDINATESINTERACT),
                                CoordinatesValidation::validateAll,
                                false)
                        );
                        add(new FieldWithDesc<>(
                                "organization",
                                Organization.class,
                                RussianText.get(TextKeys.ORGANIZATIONINTERACT),
                                OrganizationValidation::validateAll,
                                true)
                        );
                        add(new FieldWithDesc<>(
                                "position",
                                Position.class,
                                RussianText.get(TextKeys.POSITIONINTERACT),
                                true)
                        );


                    }
                }
        );
        put(
                Coordinates.class,
                new ArrayList<>() {
                    {
                        add(new FieldWithDesc<>(
                                "x",
                                Integer.class,
                                RussianText.get(TextKeys.COORXINTERACT),
                                CoordinatesValidation::validateX,
                                false)
                        );
                        add(new FieldWithDesc<>(
                                "y",
                                Float.class,
                                RussianText.get(TextKeys.COORYINTERACT),
                                CoordinatesValidation::validateY,
                                false)
                        );

                    }
                }
        );
        put(
                Organization.class,
                new ArrayList<>() {
                    {
                        add(new FieldWithDesc<>(
                                "fullName",
                                String.class,
                                RussianText.get(TextKeys.ORGANISATIONNAMEINTERACT),
                                OrganizationValidation::validateFullName,
                                false)
                        );
                        add(new FieldWithDesc<>(
                                        "type",
                                        OrganizationType.class,
                                        RussianText.get(TextKeys.ORGANISATIONTYPEINTERACT),
                                        false
                                )
                        );

                    }
                }
        );

    }};

}
