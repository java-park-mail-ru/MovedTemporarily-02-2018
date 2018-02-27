package main.views;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;


public final class LoginForm {

    private @NotNull String login;
    private @NotNull String password;

    public LoginForm(@JsonProperty("login") String login, @JsonProperty("password") String password) {
        this.login = login;
        this.password = password;
    }

    @SuppressWarnings("unused")
    public String getLogin() {
        return login;
    }

    @SuppressWarnings("unused")
    public void setLogin(String login) {
        this.login = login;
    }

    @SuppressWarnings("unused")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }
}
