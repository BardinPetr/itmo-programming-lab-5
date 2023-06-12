package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserAPICommand;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.validation.OrganizationValidator;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

/**
 * Class of update command
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UpdateOrgCommand extends UserAPICommand {
    @NonNull
    public Integer id;
    @NonNull
    public Organization element;

    @Override
    public String getType() {
        return "update_org";
    }

    @Override
    public ValidationResponse validate() {
        return OrganizationValidator.validateAll(element);
    }

    @Override
    public Field<?>[] getInlineArgs() {
        return new Field[]{
                new Field<>("id", Integer.class)
        };
    }

    @Override
    public Field<?>[] getInteractArgs() {
        return new Field[]{
                new Field<>("element", Organization.class)
        };
    }
}
