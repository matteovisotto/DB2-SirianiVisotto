package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.InsolventUser;
import it.polimi.db2.telco.exceptions.user.UserNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class InsolventUserService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public InsolventUserService(){}

    public InsolventUser getInsolventUserById(Integer insolventUserId) throws UserNotFoundException {
        InsolventUser insolventUser = em.find(InsolventUser.class, insolventUserId);
        if(insolventUser == null){
            throw new UserNotFoundException();
        }
        return insolventUser;
    }

    public InsolventUser getInsolventUserByEmail(String email) throws UserNotFoundException {
        TypedQuery<InsolventUser> query = em.createQuery("SELECT i FROM InsolventUser i WHERE i.email = :email", InsolventUser.class);
        query.setParameter("email", email);
        List<InsolventUser> insolventUsers = query.getResultList();
        if(insolventUsers.size() != 1){
            throw new UserNotFoundException();
        }
        return insolventUsers.get(0);
    }

    public InsolventUser getInsolventUserByUsername(String username) throws UserNotFoundException {
        TypedQuery<InsolventUser> query = em.createQuery("SELECT i FROM InsolventUser i WHERE i.username = :username", InsolventUser.class);
        query.setParameter("username", username);
        List<InsolventUser> insolventUsers = query.getResultList();
        if(insolventUsers.size() != 1){
            throw new UserNotFoundException();
        }
        return insolventUsers.get(0);
    }

    public List<InsolventUser> getAllInsolventUsers() throws UserNotFoundException {
        TypedQuery<InsolventUser> query = em.createQuery("SELECT i FROM InsolventUser i", InsolventUser.class);
        List<InsolventUser> insolventUsers = query.getResultList();
        if (insolventUsers.size() == 0) {
            throw new UserNotFoundException();
        }
        return insolventUsers;
    }
}
