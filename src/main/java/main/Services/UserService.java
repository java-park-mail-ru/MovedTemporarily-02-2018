package main.Services;

import main.Models.User;
import main.Views.LoginForm;
import main.Views.MailForm;
import main.Views.UserInfoForm;
import main.Views.PassForm;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class UserService {

    private LinkedList<User> users = new LinkedList<>();

    public enum ErrorCodes {

        @SuppressWarnings({"EnumeratedConstantNamingConvention", "unused"})
        OK,
        INVALID_LOGIN,
        INVALID_PASSWORD,
        LOGIN_OCCUPIED,
        INVALID_AUTH_DATA,
        INVALID_REG_DATA,
    }

    private User getUserByLogin(String login) {

        for (User user: users) {
            if (user.getLogin().equals(login)){
                return user;
            }
        }

        return null;
    }

    public ErrorCodes addUser(User newUser) {
//        // Проверка на корректность пользователя
//        for (User user : users) {
//            // проверка на наличие пользователя с таким же логином и email
//            System.out.println(user.getEmail());
//            System.out.println(user.getLogin());
//            System.out.println(user.getPassword());
//        }

        if (newUser.getLogin() == null || newUser.getEmail() == null || newUser.getPassword() == null) {
            return ErrorCodes.INVALID_REG_DATA;
        }

        for (User user : users) {
            // проверка на наличие пользователя с таким же логином и email
            if (user.equals(newUser)) {
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

        data.login = user.getLogin();
        data.email = user.getEmail();

        return ErrorCodes.OK;

    }

}