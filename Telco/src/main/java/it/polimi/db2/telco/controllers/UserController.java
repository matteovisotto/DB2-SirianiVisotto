package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.UserCredentialException;
import it.polimi.db2.telco.exceptions.UserException;
import it.polimi.db2.telco.services.UserService;

import javax.inject.Inject;

public class UserController {
    @Inject
    UserService userService;

    public UserController(){}

    public User loginUserByEmail(String email, String password) throws UserException {
        User user = userService.getUserByEmail(email);
        if(!user.getPassword().equals(password)){
            throw new UserCredentialException();
        }
        return user;
    }

    public User loginUserByUsername(String username, String password) throws UserException {
        User user = userService.getUserByUsername(username);
        if(!user.getPassword().equals(password)){
            throw new UserCredentialException();
        }
        return user;
    }

    public User getUserById(Integer userId) throws UserException {
        return userService.getUserById(userId);
    }
}
