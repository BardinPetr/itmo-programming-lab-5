package ru.bardinpetr.itmo.lab5.models.data.validation;

public class CredentialsValidator {
    public ValidationResponse validateUsername(String username){
        if (username.isEmpty()){
            return new ValidationResponse(
                    false,
                    "CredentialsValidator.username.empty"
            );
        } else if (username.length()>40){
            return new ValidationResponse(
                    false,
                    "CredentialsValidator.username.invalidLength"
            );
        }
        return new ValidationResponse(true,"");
    }



    public ValidationResponse validatePassword(String password){
        if (password.length()<8)
            return new ValidationResponse(
                    false,
                    "CredentialsValidator.password.invalidLength"
            );
        return new ValidationResponse(true, "");
    }
}
