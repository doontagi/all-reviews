package com.allreviews.controller;

import com.allreviews.DTO.LoginRequestDTO;
import com.allreviews.User;
import com.allreviews.service.LoginService;
import com.allreviews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserController {

    private static final ResponseEntity<String> SUCCESSFUL_SIGNUP = new ResponseEntity<String>("Successful Sign-up", HttpStatus.OK);
    private static final ResponseEntity<String> BAD_SIGNUP = new ResponseEntity<String>("Wrong form", HttpStatus.BAD_REQUEST);

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public HttpStatus login(@RequestBody LoginRequestDTO loginRequestDTO, HttpSession httpSession) {
        User user = loginService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        if (user == null) {
            return HttpStatus.UNAUTHORIZED;
        } else {
            httpSession.setAttribute("LOGIN_USER_ID", loginRequestDTO.getUsername());
            return HttpStatus.OK;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return BAD_SIGNUP;
        }
        User newUser = userService.createUser(user);

        return SUCCESSFUL_SIGNUP;
    }

    @PostMapping("/delete")
    public ResponseEntity<String> signUp(@RequestBody String password, HttpSession httpSession) {
        if (errors.hasErrors()) {
            return BAD_SIGNUP;
        }
        User newUser = userService.createUser(user);

        return SUCCESSFUL_SIGNUP;
    }
}
