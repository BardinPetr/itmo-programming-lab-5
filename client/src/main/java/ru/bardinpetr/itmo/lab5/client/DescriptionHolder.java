package ru.bardinpetr.itmo.lab5.client;

import ru.bardinpetr.itmo.lab5.client.texts.RussianText;
import ru.bardinpetr.itmo.lab5.client.texts.TextKeys;
import ru.bardinpetr.itmo.lab5.models.commands.FiledWithDesc;
import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.validation.CoordinatesValidation;
import ru.bardinpetr.itmo.lab5.models.data.validation.OrganizationValidation;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerValidation;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Class for describing data class's fields with their name, type, prompt message and validation function
 */
public class DescriptionHolder {
    /**
     * Main map with all information
     */
    public static Map<Class<?>, List<FiledWithDesc>> dataDescriptions = new HashMap<>(){{
        put(
                Worker.class,
                new ArrayList<>(){
                    {
                        add(new FiledWithDesc(
                                        "startDate",
                                        Date.class,
                                        RussianText.getMap().get(TextKeys.STARTDAYINTERACT)
                                )
                        );
                        add(new FiledWithDesc(
                                        "endDate",
                                        LocalDateTime.class,
                                        RussianText.getMap().get(TextKeys.ENDNDATEINTERACT)
                                )
                        );
                        add(new FiledWithDesc(
                                "name",
                                String.class,
                                RussianText.getMap().get(TextKeys.NAMEINTERACT),
                                WorkerValidation::validateName)
                        );
                        add(new FiledWithDesc(
                                "salary",
                                float.class,
                                RussianText.getMap().get(TextKeys.SALARYINTERACT),
                                WorkerValidation::validateSalary)
                        );
                        add(new FiledWithDesc(
                                "coordinates",
                                Coordinates.class,
                                RussianText.getMap().get(TextKeys.COORDINATESINTERACT),
                                CoordinatesValidation::validateAll)
                        );


                    }
                }
        );
        put(
                Coordinates.class,
                new ArrayList<>(){
                    {
                        add(new FiledWithDesc(
                                "x",
                                Integer.class,
                                RussianText.getMap().get(TextKeys.COORXINTERACT),
                                CoordinatesValidation::validateX)
                        );
                        add(new FiledWithDesc(
                                "y",
                                int.class,
                                RussianText.getMap().get(TextKeys.COORYINTERACT))
                        );

                    }
                }
        );
        put(
                Organization.class,
                new ArrayList<>(){
                    {
                        add(new FiledWithDesc(
                                "fullName",
                                String.class,
                                RussianText.getMap().get(TextKeys.ORGANISATIONNAMEINTERACT),
                                OrganizationValidation::validateFullName)
                        );
                        add(new FiledWithDesc(
                                "type",
                                OrganizationType.class,
                                RussianText.getMap().get(TextKeys.ORGANISATIONTYPEINTERACT)
                                )
                        );

                    }
                }
        );

    }};

}
