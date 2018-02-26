package main.Controllers;

import main.Models.User;
import main.Views.LoginForm;
import main.Views.MailForm;
import main.Views.UserInfoForm;
import main.Views.PassForm;
import main.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    private UserService users = new UserService();

    @RequestMapping(path= "/api/user/signup", method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody User signUpData) {

        final UserService.ErrorCodes error = users.addUser(signUpData);
        switch (error) {
            case INVALID_REG_DATA: {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some Error");
            }

            case LOGIN_OCCUPIED: {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login is already exist");
            }

            case OK: {
                return ResponseEntity.status(HttpStatus.CREATED).body("successfully");
            }

            default: {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
            }
        }
    }

    @RequestMapping(path = "/api/user/login", method = RequestMethod.POST)
    public ResponseEntity logIn(@RequestBody LoginForm loginData, HttpSession httpSession) {
        final UserService.ErrorCodes error = users.login(loginData);
        switch (error) {
            case INVALID_AUTH_DATA: {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid auth data");
            }

            case INVALID_LOGIN: {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid login");
            }

            case INVALID_PASSWORD: {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Password");
            }

            case OK: {
                httpSession.setAttribute("userLogin", loginData.getLogin());
                return ResponseEntity.status(HttpStatus.OK).body("successfully");
            }

            default: {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
            }
        }
    }

    @RequestMapping(path = "/api/user/logout", method = RequestMethod.POST)
    public ResponseEntity logOut(HttpSession httpSession) {
        final String login = (String) httpSession.getAttribute("userLogin");
        if (login == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You are not logged in");
        }
        httpSession.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("successfully");
    }

    @RequestMapping(path = "/api/user/changeMail", method = RequestMethod.POST)
    public ResponseEntity changeMail(@RequestBody MailForm mailData, HttpSession httpSession) {
        final String login = (String) httpSession.getAttribute("userLogin");
        if (login == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not logged in");
        }

        final UserService.ErrorCodes error = users.changeMail(mailData, login);
        switch (error) {

            case OK: {
                return ResponseEntity.status(HttpStatus.OK).body("Successfully");
            }

            case INVALID_LOGIN:{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You are not logged in");
            }

            default: {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
            }
        }
    }

    @RequestMapping(path = "api/user/changePass", method = RequestMethod.POST)
    public ResponseEntity changePass(@RequestBody PassForm passData, HttpSession httpSession) {
        final String login = (String) httpSession.getAttribute("userLogin");

        if (login == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not logged in");
        }

        final UserService.ErrorCodes error = users.changePass(passData, login);
        switch (error) {
            case INVALID_LOGIN:{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You are not logged in");
            }

            case OK: {
                return ResponseEntity.status(HttpStatus.OK).body("Successfully");
            }

            default: {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
            }
        }
    }

    @RequestMapping(path = "api/user/data", method = RequestMethod.GET)
    public ResponseEntity currentUserInfo(HttpSession httpSession) {
        UserInfoForm data = new UserInfoForm();
        String login = (String) httpSession.getAttribute("userLogin");

        if (login == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not logged in");
        }

        UserService.ErrorCodes error = users.getUserInfo(data, login);

        switch (error) {
            case INVALID_LOGIN: {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You are not registered\n");
            }

            case OK: {
                return ResponseEntity.status(HttpStatus.OK).body(data);
            }

            default: {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
            }
        }
    }

}

