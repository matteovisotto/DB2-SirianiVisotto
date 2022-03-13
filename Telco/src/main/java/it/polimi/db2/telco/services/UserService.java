package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.UserNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public UserService(){}

    public User getUserById(Integer userId) throws UserNotFoundException {
        User user = em.find(User.class, userId);
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        List<User> users = query.getResultList();
        if(users.size() != 1){
            throw new UserNotFoundException();
        }
        return users.get(0);
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> users = query.getResultList();
        if(users.size() != 1){
            throw new UserNotFoundException();
        }
        return users.get(0);
    }

    public Integer createUser(User user) {
        em.persist(user);
        em.flush();
        return user.getId();
    }

    public Integer updateUser(User user) {
        user = em.merge(user);
        em.flush();
        return user.getId();
    }


    public void deleteUser(User user) {
        if (!em.contains(user)) {
            user = em.merge(user);
        }
        em.remove(user);
        em.flush();
    }
}
