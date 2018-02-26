package main.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public final class User {

    private String email;
    @NotNull private String login;
    @NotNull private String password;

    @JsonCreator
    User(@JsonProperty("email") String email, @JsonProperty("login") String login,
         @JsonProperty("password") String password) {
        this.email = email;
        this.login = login;
        this.password = password;

    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof User)) {
            return false;
        }

        User oth = (User) other;

        return email.equals(oth.email) || login.equals(oth.login);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
