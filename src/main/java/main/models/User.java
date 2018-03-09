package main.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

@SuppressWarnings("unused")
public final class User {

    private @NotNull String email;
    private @NotNull String login;
    private @NotNull String password;
    private Integer score;

    @JsonCreator
    public User(@JsonProperty("email") String email,
         @JsonProperty("login") String login,
         @JsonProperty("password") String password,
         @JsonProperty("score") Integer score) {
        this.email = email;
        this.login = login;
        this.password = password;
        if (score == null) {
            this.score = 0;
        } else {
            this.score = score;
        }

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

    public Integer getScore() {
        return score;
    }

}
