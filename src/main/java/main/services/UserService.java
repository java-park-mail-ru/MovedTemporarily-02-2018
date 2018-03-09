package main.services;

import main.models.User;
import main.views.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private ArrayList<User> users = new ArrayList<>();

    UserService() {
        addTestUser();
    }

    public enum ErrorCodes {
        @SuppressWarnings("EnumeratedConstantNamingConvention") OK,
        INVALID_LOGIN,
        INCORRECT_PASSWORD,
        LOGIN_OCCUPIED,
        INVALID_AUTH_DATA,
        INVALID_REG_DATA,
    }

    private void addTestUser() {
        final int count = 10 * 3;
        for (int i = 0; i < count; i++) {
            final String login = "test" + String.valueOf(i);
            final String email = "test" + String.valueOf(i) + "@mail.ru";
            final String pass = "pass";
            final Integer score = i * 10;
            users.add(new User(email, login, pass, score));
        }
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
            return ErrorCodes.INCORRECT_PASSWORD;
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

        if (!user.getPassword().equals(passData.getOldPassword())) {
            return ErrorCodes.INCORRECT_PASSWORD;
        }

        user.setPassword(passData.getNewPassword());
        return ErrorCodes.OK;

    }

    public ErrorCodes getUserInfo(UserInfoForm data, String login) {
        final User user = getUserByLogin(login);

        if (login == null || user == null) {
            return ErrorCodes.INVALID_LOGIN;
        }

        data.setLogin(user.getLogin());
        data.setEmail(user.getEmail());
        return ErrorCodes.OK;
    }


    public ArrayList<ScoreView> getScoreBoard() {
        final ArrayList<ScoreView> result = new ArrayList<>();

        for (User user: users) {
            result.add(new ScoreView(user));
        }

        result.sort(new ScoreView.ScoreComparator());
        return result;
    }

    public ArrayList<ScoreView> getScoreBoard(Integer from, Integer count) {
        if (from > users.size()) {
            return new ArrayList<>();
        }

        if (from + count > users.size()) {
            count = users.size() - from;
        }

        return new ArrayList<>(getScoreBoard().subList(from, from + count));
    }
}