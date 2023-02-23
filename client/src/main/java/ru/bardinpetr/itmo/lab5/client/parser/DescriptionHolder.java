package ru.bardinpetr.itmo.lab5.client.parser;

import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.models.data.*;
import ru.bardinpetr.itmo.lab5.models.data.validation.CoordinatesValidation;
import ru.bardinpetr.itmo.lab5.models.data.validation.OrganizationValidation;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerValidation;
import ru.bardinpetr.itmo.lab5.models.fields.FieldWithDesc;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Class for describing data class's fields with their name, type, prompt message and validation function
 */
public class DescriptionHolder {
    /**
     * Main map with all information
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
                                WorkerValidation::validateName)
                        );
                        add(new FieldWithDesc<>(
                                "salary",
                                Float.class,
                                RussianText.get(TextKeys.SALARYINTERACT),
                                WorkerValidation::validateSalary)
                        );

                        add(new FieldWithDesc<>(
                                        "startDate",
                                        Date.class,
                                RussianText.get(TextKeys.STARTDAYINTERACT)
                                )
                        );
                        add(new FieldWithDesc<>(
                                        "endDate",
                                        LocalDateTime.class,
                                RussianText.get(TextKeys.ENDNDATEINTERACT)
                                )
                        );
                        add(new FieldWithDesc<>(
                                "coordinates",
                                Coordinates.class,
                                RussianText.get(TextKeys.COORDINATESINTERACT),
                                CoordinatesValidation::validateAll)
                        );
                        add(new FieldWithDesc<>(
                                "organization",
                                Organization.class,
                                RussianText.get(TextKeys.ORGANIZATIONINTERACT),
                                OrganizationValidation::validateAll)
                        );
                        add(new FieldWithDesc<>(
                                "position",
                                Position.class,
                                RussianText.get(TextKeys.POSITIONINTERACT))
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
                                CoordinatesValidation::validateX)
                        );
                        add(new FieldWithDesc<>(
                                "y",
                                Float.class,
                                RussianText.get(TextKeys.COORYINTERACT))
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
                                OrganizationValidation::validateFullName)
                        );
                        add(new FieldWithDesc<>(
                                        "type",
                                        OrganizationType.class,
                                RussianText.get(TextKeys.ORGANISATIONTYPEINTERACT)
                                )
                        );

                    }
                }
        );

    }};

}
