
package datamodell.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hallgato
 */
@Table(name="reservation")      
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "reservation.listDay", query = "SELECT r FROM Reservation r WHERE r.date > :from AND r.date < :to"),
            @NamedQuery(name = "reservation.byUser", query = "SELECT r FROM Reservation r WHERE r.user_ = :user")
        }
)
public class Reservation implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "res_user")
    private User user_;
    
    @Column(name = "res_date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @NotNull
    @ManyToOne
    private PubTable pubtable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user_;
    }

    public void setUser(User user) {
        this.user_ = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PubTable getPubtable() {
        return pubtable;
    }

    public void setPubtable(PubTable pubtable) {
        this.pubtable = pubtable;
    }
    
}
