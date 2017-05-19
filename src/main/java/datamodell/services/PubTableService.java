
package datamodell.services;

import datamodell.entities.PubTable;
import datamodell.entities.Reservation;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hallgato
 */
@Stateless
public class PubTableService {
    
    @PersistenceContext(unitName = "tibiPU")
    private EntityManager entityManager;
    
    @Inject
    private ReservationService reservationService;
    
    public Collection<PubTable> getTables() {
        return entityManager.createNativeQuery("pubtable.all").getResultList();
    }
    
    public Collection<PubTable> getFreeTables(Date date) {
        Collection<Reservation> reservations = reservationService.listDay(date);
        
        Query query = entityManager.createNativeQuery("pubtable.free").setParameter("reservations", reservations);
        
        return query.getResultList();
    }
    
    public void createPubtable(PubTable pubTable) {
        entityManager.persist(pubTable);
    }
}
