package org.iu.edu.modelmate.controller;

import org.iu.edu.modelmate.common.UserConstants;
import org.iu.edu.modelmate.model.User;
import org.iu.edu.modelmate.model.UserCreatedResponse;
import org.iu.edu.modelmate.model.UserLogin;
import org.iu.edu.modelmate.model.UserLoginResponse;
import org.iu.edu.modelmate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping(path = "/createUser",produces = "application/json")
    public ResponseEntity<UserCreatedResponse> createUser(@RequestBody User user){

        UserCreatedResponse userCreatedResponse = userService.createUser(user);

        if(userCreatedResponse.getError() == null || userCreatedResponse.getError().isEmpty()){

            return ResponseEntity.status(HttpStatus.CREATED).body(userCreatedResponse);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userCreatedResponse);
        }
    }

    @PostMapping(path = "/login",produces = "application/json")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLogin userLogin, HttpSession httpSession){

        UserLoginResponse userLoginResponse = userService.loginUser(userLogin);

        if(userLoginResponse.getError() == null || userLoginResponse.getError().isEmpty()){

            httpSession.setAttribute("user" ,userLogin.getUsername());
            userLoginResponse.setSessionId(httpSession.getId());
            return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userLoginResponse);
        }

    }

    @PostMapping(path = "/logout",produces = "application/json")
    private ResponseEntity<String> logoutUser(HttpSession httpSession){
        httpSession.invalidate();

        return ResponseEntity.status(HttpStatus.OK).body(UserConstants.userLoggedOut);
    }

    @GetMapping(path ="/checkLogin")
    private ResponseEntity<Boolean> isLoggedIn(HttpSession httpSession){
        Boolean isLoggedIn = (httpSession!=null && httpSession.getAttribute("user")!=null);

        return ResponseEntity.status(HttpStatus.OK).body(isLoggedIn);
    }
}
