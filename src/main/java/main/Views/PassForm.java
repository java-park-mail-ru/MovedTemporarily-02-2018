package main.Views;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PassForm {
    private String password;

    public PassForm(@JsonProperty("password") String password) {
        this.password = password;
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
