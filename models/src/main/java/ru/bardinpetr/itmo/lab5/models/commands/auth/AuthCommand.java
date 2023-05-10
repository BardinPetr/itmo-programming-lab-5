package ru.bardinpetr.itmo.lab5.models.commands.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.LoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserAPICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.UserPrintableAPICommandResponse;
import ru.bardinpetr.itmo.lab5.models.fields.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AuthCommand extends UserAPICommand {
    @NonNull
    private DefaultAuthenticationCredentials credentials;

    @Override
    public Field<?>[] getInteractArgs() {
        return new Field[]{
                new Field<>("credentials", DefaultAuthenticationCredentials.class),
        };
    }

    @Override
    public AuthCommandResponse createResponse() {
        return new AuthCommandResponse();
    }

    public static class AuthCommandResponse extends APICommandResponse implements UserPrintableAPICommandResponse {
        private LoginResponse data;


        public LoginResponse getData() {
            return data;
        }

        public AuthCommandResponse setData(LoginResponse data) {
            this.data = data;
            return this;
        }
    }
}
