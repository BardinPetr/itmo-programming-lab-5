package ru.bardinpetr.itmo.lab5.client.api.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.client.api.commands.utils.ClassUtils;
import ru.bardinpetr.itmo.lab5.client.api.commands.utils.StringUtils;
import ru.bardinpetr.itmo.lab5.models.commands.validation.IValidator;
import ru.bardinpetr.itmo.lab5.models.data.annotation.FieldValidator;
import ru.bardinpetr.itmo.lab5.models.data.annotation.InteractText;
import ru.bardinpetr.itmo.lab5.models.data.annotation.NotPromptRequired;
import ru.bardinpetr.itmo.lab5.models.data.annotation.Nullable;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;
import ru.bardinpetr.itmo.lab5.models.data.validation.Validator;
import ru.bardinpetr.itmo.lab5.models.fields.FieldWithDesc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for describing data class's fields with their name, type, prompt message and validation function
 */
@Data
public class DescriptionHolder {
    /**
     * Main map with all information for each field: name, type, validation function,
     * nullAble boolean and text for interact
     */
    private Map<Class<?>, List<FieldWithDesc<?>>> dataDescriptions = new HashMap<>();

    public DescriptionHolder(Class<?>[] listOfClasses) {
        for (var i : listOfClasses) {
            var fieldsList = new ArrayList<FieldWithDesc<?>>();
            for (var field : i.getDeclaredFields()) {
                if (!field.isAnnotationPresent(NotPromptRequired.class)) {
                    //System.out.println(field.getName());
                    fieldsList.add(new FieldWithDesc<>(
                            field.getName(),
                            ClassUtils.wrap(field.getType()),
                            field.getAnnotation(InteractText.class).value(),
                            getValidator(
                                    field.getAnnotation(FieldValidator.class).value(),
                                    field),
                            (!(field.getClass().isPrimitive()) && field.isAnnotationPresent(Nullable.class))
                    ));
                }
            }
            this.dataDescriptions.put(i, fieldsList);
        }

//        this.dataDescriptions = new HashMap<>() {{
//            put(
//                    Worker.class,
//                    new ArrayList<>() {
//                        {
//                            add(new FieldWithDesc<>(
//                                    "name",
//                                    String.class,
//                                    RussianText.get(TextKeys.NAMEINTERACT),
//                                    WorkerValidation::validateName,
//                                    false)
//                            );
//                            add(new FieldWithDesc<>(
//                                    "salary",
//                                    Float.class,
//                                    RussianText.get(TextKeys.SALARYINTERACT),
//                                    WorkerValidation::validateSalary,
//                                    false)
//                            );
//                            add(new FieldWithDesc<>(
//                                            "startDate",
//                                            Date.class,
//                                            RussianText.get(TextKeys.STARTDAYINTERACT),
//                                            WorkerValidation::validateStartDate,
//                                            false
//                                    )
//                            );
//                            add(new FieldWithDesc<>(
//                                            "endDate",
//                                            LocalDate.class,
//                                            RussianText.get(TextKeys.ENDNDATEINTERACT),
//                                            true
//                                    )
//                            );
//                            add(new FieldWithDesc<>(
//                                    "coordinates",
//                                    Coordinates.class,
//                                    RussianText.get(TextKeys.COORDINATESINTERACT),
//                                    CoordinatesValidation::validateAll,
//                                    false)
//                            );
//                            add(new FieldWithDesc<>(
//                                    "organization",
//                                    Organization.class,
//                                    RussianText.get(TextKeys.ORGANIZATIONINTERACT),
//                                    OrganizationValidation::validateAll,
//                                    true)
//                            );
//                            add(new FieldWithDesc<>(
//                                    "position",
//                                    Position.class,
//                                    RussianText.get(TextKeys.POSITIONINTERACT),
//                                    true)
//                            );
//
//
//                        }
//                    }
//            );
//            put(
//                    Coordinates.class,
//                    new ArrayList<>() {
//                        {
//                            add(new FieldWithDesc<>(
//                                    "x",
//                                    Integer.class,
//                                    RussianText.get(TextKeys.COORXINTERACT),
//                                    CoordinatesValidation::validateX,
//                                    false)
//                            );
//                            add(new FieldWithDesc<>(
//                                    "y",
//                                    Float.class,
//                                    RussianText.get(TextKeys.COORYINTERACT),
//                                    CoordinatesValidation::validateY,
//                                    false)
//                            );
//
//                        }
//                    }
//            );
//            put(
//                    Organization.class,
//                    new ArrayList<>() {
//                        {
//                            add(new FieldWithDesc<>(
//                                    "fullName",
//                                    String.class,
//                                    RussianText.get(TextKeys.ORGANISATIONNAMEINTERACT),
//                                    OrganizationValidation::validateFullName,
//                                    false)
//                            );
//                            add(new FieldWithDesc<>(
//                                            "type",
//                                            OrganizationType.class,
//                                            RussianText.get(TextKeys.ORGANISATIONTYPEINTERACT),
//                                            false
//                                    )
//                            );
//
//                        }
//                    }
//            );
//
//        }};
    }

    public static <T> IValidator<T> getValidator(Class<? extends Validator> validatorClass, Field field) {
        try {
            var method = validatorClass.getMethod(
                    "validate%s".formatted(
                            StringUtils.capitalize(field.getName())
                    ),
                    ClassUtils.wrap(field.getType())
            );
            var constr = validatorClass.getConstructor().newInstance();
            return s -> {
                try {
                    return (ValidationResponse) method.invoke(constr, new Object[]{s});
                } catch (IllegalAccessException e) {
                    throw new Error("method access: " + e);
                }
            };
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            //System.out.println(field.getName()+" "+e.getMessage());
            return s -> new ValidationResponse(true, "Auto create");
        } catch (InstantiationException e) {
            throw new Error("Constructor: " + e);
        } catch (IllegalAccessException e) {
            throw new Error("Access: " + e);
        }
    }
}
