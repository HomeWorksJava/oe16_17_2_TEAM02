
package datamodell.services;

import datamodell.entities.User;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hallgato
 */
@Stateful
public class UserService {
    
    @PersistenceContext(unitName = "tibiPU")
    private EntityManager entityManager;
    
    public void register(User user) {
        entityManager.persist(user);
    }
    
    public User get(String name) {
        return entityManager.find(User.class, name);
    }
    
    public User update(User user) {
        return entityManager.merge(user);
    }
}
