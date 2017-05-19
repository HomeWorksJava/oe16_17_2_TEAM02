package datamodell.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hallgato
 */
@Entity
@Table(name = "user_")
public class User implements Serializable {

    @OneToMany(mappedBy = "user_")
    private List<Reservation> reservations;

    @Id
    private String email;
    
    private String username;
    
    @NotNull
    private String password;
    
    @NotNull
    private String telephone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user_", cascade = CascadeType.ALL)
    private Set<UserRoleRelation> roles;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRoleRelation> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRoleRelation> roles) {
        this.roles = roles;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
