package main.Views;

import com.fasterxml.jackson.annotation.JsonProperty;
@SuppressWarnings("unused")
public class UserInfoForm {

    public String login;
    public String email;

    public UserInfoForm() {}

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