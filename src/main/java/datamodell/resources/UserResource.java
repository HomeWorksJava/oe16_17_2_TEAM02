package datamodell.resources;

import datamodell.dto.UserUpdateData;
import datamodell.entities.Role;
import datamodell.entities.User;
import datamodell.entities.UserRoleRelation;
import datamodell.services.UserService;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hallgato
 */
@Path("/user")
public class UserResource {

    @Inject
    private UserService userService;

    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*public void dologin(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        try {
            // create a session
            req.getSession(true);

            // login
            req.login(username, password);

        } catch (final ServletException se) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
    }*/
    @POST
    @Path("/login")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @PermitAll
    public void login(@Context HttpServletRequest servletRequest, String username, String password) throws ServletException {
        servletRequest.login(username, password);
    }

    @GET
    @PermitAll
    @Path("/logged")
    public boolean isLoggedIn(@Context HttpServletRequest request) {
        return request.getRemoteUser() != null;
    }

    @GET
    @PermitAll
    @Path("/whoisloggedin")
    public User WhoisLoggedIn(@Context HttpServletRequest request) {
        return userService.get(request.getUserPrincipal().getName());
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Path("/registration")
    @RolesAllowed({Role.User, Role.MANAGER})
    public void register(String email, String username, String password, String telephone) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(hashPassword(password));
        newUser.setTelephone(telephone);

        Set<UserRoleRelation> roles = new HashSet<UserRoleRelation>();

        roles.add(new UserRoleRelation(Role.User, newUser));

        newUser.setRoles(roles);

        userService.register(newUser);
    }

    @POST
    @Path("/update")
    @RolesAllowed({Role.User, Role.MANAGER})
    public void updateUser(UserUpdateData data, @Context HttpServletRequest request) {
        User oldUser = userService.get(request.getRemoteUser());

        if (hashPassword(data.getOldPassword()).equals(oldUser.getPassword())) {
            if (data.getUsername() != null) {
                oldUser.setUsername(data.getUsername());
            }
            if (data.getNewPassword() != null) {
                oldUser.setPassword(hashPassword(data.getNewPassword()));
            }
            if (data.getTelephone() != null) {
                oldUser.setTelephone(data.getTelephone());
            }
            userService.update(oldUser);
        } else {
            throw new RuntimeException("Ehhez nincs joga!");
        }
    }

    public static String hashPassword(String password) {
        return new String(Base64.getEncoder().encode(messageDigest.digest(password.getBytes())));
    }
}
