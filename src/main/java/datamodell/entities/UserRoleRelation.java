
package datamodell.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hallgato
 */
@Entity
@Table(name = "userroles_")
public class UserRoleRelation implements Serializable {
    
    @Column(name = "role_")
    private String role;
    
    @ManyToOne
    @JoinColumn(name = "user_")
    public User user_;
    
    @Id
    @GeneratedValue
    private int id;

    public UserRoleRelation() {
    }

    public UserRoleRelation(String role, User user) {
        this.role = role;
        this.user_ = user;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user_;
    }

    public void setUser(User user) {
        this.user_ = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
