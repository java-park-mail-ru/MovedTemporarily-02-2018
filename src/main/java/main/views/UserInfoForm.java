package main.views;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public class UserInfoForm {

    private @NotNull String login;
    private @NotNull String email;

    public UserInfoForm() {

    }

    public UserInfoForm(@JsonProperty("login") String login, @JsonProperty("email") String email) {
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}