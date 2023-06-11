package ru.bardinpetr.itmo.lab5.models.commands.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.bardinpetr.itmo.lab5.models.commands.requests.UserAPICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.UserPrintableAPICommandResponse;
import ru.bardinpetr.itmo.lab5.models.data.Organization;

/**
 * Class of show command
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class GetOrganizationCommand extends UserAPICommand {
    @NonNull
    public Integer id;

    @Override
    public String getType() {
        return "go";
    }

    @Override
    public GetOrganizationCommandResponse createResponse() {
        return new GetOrganizationCommandResponse();
    }

    @Data
    @NoArgsConstructor
    public static class GetOrganizationCommandResponse extends APICommandResponse implements UserPrintableAPICommandResponse {
        @NonNull
        private Organization organization;

        @Override
        public String getUserMessage() {
            return "GetOrganizationCommandResponse.getUserMessage.text";
        }
    }
}
