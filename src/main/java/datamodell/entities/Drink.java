package datamodell.entities;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hallgato
 */
@Entity
@Table(name = "drink")
@XmlRootElement
@NamedQueries({
    /*@NamedQuery(name = "drink.findAll", query = "SELECT d FROM Drink d, Price p " +
    "JOIN d dDrink " +
    "JOIN p.drink pDrink " +
    "WHERE dDrink = pDrink"),*/
    @NamedQuery(name = "drink.findAll", query = "SELECT c FROM Drink c")
    ,
    @NamedQuery(name = "drink.findById", query = "SELECT c FROM Drink c WHERE c.id = :id")
    ,
    @NamedQuery(name = "drink.findByName", query = "SELECT c FROM Drink c WHERE c.name = :name")
    ,
    @NamedQuery(name = "drink.findByType", query = "SELECT c FROM Drink c WHERE c.type = :type")})
public class Drink implements Serializable {

    protected static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    protected Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    protected String name;

    @Basic(optional = false)
    @NotNull
    @Enumerated
    @Column(name = "type")
    protected DrinkType type;

    @Basic(optional = false)
    @Column(name = "alcoholperc")
    protected double alcoholperc;

    @OneToMany(mappedBy = "drink", fetch = FetchType.EAGER)
    private List<Price> prices = new ArrayList<>();

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public void addPrice(Price price) {
        this.prices.add(price);
    }

    public Drink() {
    }

    public Drink(String name, DrinkType type, double alcoholperc, Price... prices) {
        this.name = name;
        this.type = type;
        this.alcoholperc = alcoholperc;

        this.prices = new ArrayList<Price>();
        for (int i = 0; i < prices.length; i++) {
            this.prices.add(prices[i]);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        name = name.replace("ő", "ő");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DrinkType getType() {
        return type;
    }

    public void setType(DrinkType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "datamodel.Drink[ id=" + id + " ]";
    }
}
