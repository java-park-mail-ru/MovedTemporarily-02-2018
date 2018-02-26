package main.views;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class MailForm {

    private String userMail;

    public MailForm(@JsonProperty("userMail") String userMail) {
        this.userMail = userMail;
    }

    @SuppressWarnings("unused")
    public String getUserMail() {
        return userMail;
    }

    @SuppressWarnings("unused")
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
