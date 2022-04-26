package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.user.*;
import it.polimi.db2.telco.services.UserService;

import javax.inject.Inject;

public class UserController {
    @Inject
    UserService userService;

    public UserController(){}

    public User loginUserByEmail(String email, String password) throws UserException {
        User user = userService.getUserByEmail(email);
        if(user == null || !user.getPassword().equals(password)){
            throw new UserCredentialException();
        }
        return user;
    }

    public User loginUserByUsername(String username, String password) throws UserException {
        User user = userService.getUserByUsername(username);
        if(user == null || !user.getPassword().equals(password)){
            throw new UserCredentialException();
        }
        return user;
    }

    public boolean checkIfUsernameIsAlreadyUsed(String username) {
        return userService.checkUsername(username);
    }

    public boolean checkIfEmailIsAlreadyUsed(String email){
        return userService.checkEmail(email);
    }

    public Integer createUser(User user) throws UserException {
        Integer userId;
        userId = userService.createUser(user);
        return userId;
    }
}
