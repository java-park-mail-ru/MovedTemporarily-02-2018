package main.views;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("unused")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseMsg {

    OK("OK"),
    CREATED("User Created"),
    FORBIDDEN("Invalid auth data"),
    BAD_REQUEST("Not all Json fields are filled"),
    NOT_LOGGED_IN("You are not logged in"),
    CONFLICT("Login or Email are already exist"),
    INVALID_LOGIN("User with such is not registered"),
    INCORRECT_PASSWORD("Incorrect password"),
    INTERNAL_SERVER_ERROR("Server error. Sorry:(");

    private final String msg;

    ResponseMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}