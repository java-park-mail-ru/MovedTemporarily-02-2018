package main.services;

import main.models.User;
import main.views.LoginForm;
import main.views.MailForm;
import main.views.UserInfoForm;
import main.views.PassForm;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class UserService {

    private LinkedList<User> users = new LinkedList<>();
    
    public enum ErrorCodes {
        @SuppressWarnings("EnumeratedConstantNamingConvention")
        OK,
        INVALID_LOGIN,
        INVALID_PASSWORD,
        LOGIN_OCCUPIED,
        INVALID_AUTH_DATA,
        INVALID_REG_DATA,
    }

    private User getUserByLogin(String login) {

        for (User user: users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }

        return null;
    }

    public ErrorCodes addUser(User newUser) {

        if (newUser.getLogin() == null || newUser.getEmail() == null || newUser.getPassword() == null) {
            return ErrorCodes.INVALID_REG_DATA;
        }

        for (User user : users) {
            // проверка на наличие пользователя с таким же логином и email
            if (user.getLogin().equals(newUser.getLogin())  || user.getEmail().equals(newUser.getEmail())) {
                return ErrorCodes.LOGIN_OCCUPIED;
            }
        }

        users.add(newUser);
        return ErrorCodes.OK;
    }

    public ErrorCodes login(LoginForm userData) {
        final String login = userData.getLogin();

        if (login == null || userData.getPassword() == null) {
            return ErrorCodes.INVALID_AUTH_DATA;
        }

        final User user = getUserByLogin(login);

        if (user == null) {
            return ErrorCodes.INVALID_LOGIN;
        }

        if (!user.getPassword().equals(userData.getPassword())) {
            return ErrorCodes.INVALID_PASSWORD;
        }

        return ErrorCodes.OK;
    }

    public ErrorCodes changeMail(MailForm newMail, String login) {
        final User user = getUserByLogin(login);

        if (user == null) {
            return ErrorCodes.INVALID_LOGIN;
        }

        user.setEmail(newMail.getUserMail());
        return ErrorCodes.OK;
    }

    public ErrorCodes changePass(PassForm passData, String login) {
        final User user = getUserByLogin(login);

        if (user == null) {
            return ErrorCodes.INVALID_LOGIN;
        }

        user.setPassword(passData.getPassword());
        return ErrorCodes.OK;
    }

    public ErrorCodes getUserInfo(UserInfoForm data, String login) {
        User user = getUserByLogin(login);

        if (login == null || user == null) {
            return ErrorCodes.INVALID_LOGIN;
        }

        data.setLogin(user.getLogin());
        data.setEmail(user.getEmail());
        return ErrorCodes.OK;
    }
}