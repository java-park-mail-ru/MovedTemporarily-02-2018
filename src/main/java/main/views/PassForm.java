package main.views;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PassForm {

    private String oldPassword;
    private String newPassword;

    public PassForm(@JsonProperty("oldPassword") String oldPassword,
                    @JsonProperty("newPassword") String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @SuppressWarnings("unused")
    public String getOldPassword() {
        return oldPassword;
    }

    @SuppressWarnings("unused")
    public String getNewPassword() {
        return newPassword;
    }
}
