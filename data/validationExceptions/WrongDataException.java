package data.validationExceptions;

public class WrongDataException extends Exception{

    private String msg;

    public WrongDataException(String msg){
        this.msg = msg;
    }

    public String getExceptionIngo(){
        return msg;
    }
}
