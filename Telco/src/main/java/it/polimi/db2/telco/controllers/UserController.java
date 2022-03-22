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

    public User getUserByEmail(String mail) throws UserException {
        return userService.getUserByEmail(mail);
    }

    public User getUserByUsername(String username) throws UserException {
        return userService.getUserByUsername(username);
    }

    public void checkIfUsernameIsAlreadyUsed(String username) throws UserUsernameAlreadyExistingException {
        userService.checkUsername(username);
    }

    public void checkIfEmailIsAlreadyUsed(String email) throws UserEmailAlreadyExistingException {
        userService.checkEmail(email);
    }

    public Integer createUser(User user) throws UserException {
        Integer userId;
        userId = userService.createUser(user);
        /*if (userService.getUserByEmail(user.getEmail()) == null) {
            if (userService.getUserByUsername(user.getUsername()) == null) {
                userId = userService.createUser(user);
            } else {
                throw new UserUsernameAlreadyExistingException();
            }
        } else {
            throw new UserEmailAlreadyExistingException();
        }*/
        return userId;
    }

    public Integer updateUser(User user) throws UserException {
        Integer userId;
        if (userService.getUserByEmail(user.getEmail()) != null) {
            if (userService.getUserByEmail(user.getEmail()).getUsername().equals(user.getUsername())) {
                userId = userService.updateUser(user);
            } else {
                throw new UserNotCorrespondentException();
            }
        } else {
            throw new UserNotFoundException();
        }
        return userId;
    }

    public void deleteUser(Integer userId) throws UserException {
        User user = userService.getUserById(userId);
        if (user != null) {
            userService.deleteUser(user);
        } else {
            throw new UserNotFoundException();
        }
    }
}
