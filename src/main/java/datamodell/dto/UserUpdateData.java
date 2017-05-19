
package datamodell.dto;

/**
 *
 * @author hallgato
 */
public class UserUpdateData {
    
    /**
     * Mindig ki van töltve
     */
    private String oldPassword;
    private String newPassword;
    private String telephone;
    private String username;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String old_password) {
        this.oldPassword = old_password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String new_password) {
        this.newPassword = new_password;
    }
    
}
