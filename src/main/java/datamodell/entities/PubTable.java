
package datamodell.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author hallgato
 */
@Entity
@Table(name="pubtable")
@NamedQueries({
    @NamedQuery(name = "pubtable.all", query = "SELECT p FROM PubTable p"),
    @NamedQuery(name = "pubtable.free", query = "SELECT p FROM PubTable p, Reservation r WHERE r.pubtable = p AND r NOT IN :reservations")
})
public class PubTable implements Serializable{
    @Id
    private String name;
    
    private int capacity;

    public PubTable() {
    }

    public PubTable(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
    
    public String getTable() {
        return name;
    }

    public void setTable(String table_) {
        this.name = table_;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    
}
