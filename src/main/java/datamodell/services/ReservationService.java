
package datamodell.services;

import datamodell.entities.Reservation;
import datamodell.entities.User;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hallgato
 */
@Stateless
public class ReservationService {
    @PersistenceContext(unitName = "tibiPU")
    private EntityManager entityManager;
    
    public void reserve(Reservation reservation) {
        entityManager.persist(reservation);
    }
    
    public void cancel(Reservation reservation) {
        entityManager.remove(reservation);
    }
    
    public Reservation get(int id) {
        return entityManager.find(Reservation.class, id);
    }
    
    public Collection<Reservation> getReservations(User user) {
        return entityManager.createNamedQuery("reservation.byUser")
                .setParameter("user", user).getResultList();
    }

    public Collection<Reservation> listDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        
        Date from = calendar.getTime();
        
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        
        
        Date to = calendar.getTime();
        
        Query query = entityManager.createNamedQuery("reservation.listDay")
                .setParameter("from", from)
                .setParameter("to", to);
        
        return query.getResultList();
    }
    
    public Collection<Reservation> listToday() {
        return listDay(new Date());
    }
}
