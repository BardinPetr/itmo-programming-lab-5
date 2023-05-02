package ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.commands;

import lombok.Data;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserAPICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.UserPrintableAPICommandResponse;
import ru.bardinpetr.itmo.lab5.models.fields.Field;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.AuthenticationRegistrationResponse;

@Data
public class RegisterAPICommand extends UserAPICommand {

    private String username;
    private String password;

    @Override
    public String getType() {
        return "register";
    }

    @Override
    public Field<?>[] getInteractArgs() {
        return new Field[]{
                new Field<>("username", String.class),
                new Field<>("password", String.class)
        };
    }


    @Override
    public RegisterCommandResponse createResponse() {
        return new RegisterCommandResponse();
    }

    public static class RegisterCommandResponse extends APICommandResponse implements UserPrintableAPICommandResponse {
        private AuthenticationRegistrationResponse data;


        public AuthenticationRegistrationResponse getData() {
            return data;
        }

        public RegisterCommandResponse setData(AuthenticationRegistrationResponse data) {
            this.data = data;
            return this;
        }
    }
}
