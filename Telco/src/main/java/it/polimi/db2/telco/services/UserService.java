package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.user.UserEmailAlreadyExistingException;
import it.polimi.db2.telco.exceptions.user.UserNotFoundException;
import it.polimi.db2.telco.exceptions.user.UserUsernameAlreadyExistingException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
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

    public void checkUsername(String username) throws UserUsernameAlreadyExistingException, NoResultException {
        try{
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            User user = query.getSingleResult();
            if(user != null){
                throw new UserUsernameAlreadyExistingException();
            }
        } catch(NoResultException ignored) {}
    }

    public void checkEmail(String email) throws UserEmailAlreadyExistingException, NoResultException {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            User user = query.getSingleResult();
            if(user != null){
                throw new UserEmailAlreadyExistingException();
            }
        } catch(NoResultException ignored) {}
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
