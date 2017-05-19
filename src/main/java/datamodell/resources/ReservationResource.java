
package datamodell.resources;

import datamodell.entities.Reservation;
import datamodell.entities.Role;
import datamodell.entities.User;
import datamodell.services.ReservationService;
import datamodell.services.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hallgato
 */
@Path("/reservation")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ReservationResource {
    
    @Inject
    private ReservationService reservationService;
    
    @Inject
    private UserService userService;
    
    @POST
    @RolesAllowed({Role.User,Role.MANAGER})
    public void reserve(Reservation reservation) {
        reservationService.reserve(reservation);
    }
    
    @DELETE
    @RolesAllowed({Role.User,Role.MANAGER})
    public void cancel(@QueryParam("id") int id, @Context HttpServletRequest request) {
        Reservation reservation = reservationService.get(id);
        if (reservation == null && reservation.getUser().getUsername().equals(request.getRemoteUser())) {
            reservationService.cancel(reservation);
        }
    }
    
    @GET
    @RolesAllowed({Role.MANAGER})
    public Collection<Reservation> listReservations(@QueryParam("date") Date date) {
        if (date == null) {
            date = new Date();
        }
        return reservationService.listDay(date);
    }
    
    @GET
    @Path("/byuser")
    @RolesAllowed({Role.MANAGER})
    public Collection<Reservation> getByUser(@QueryParam("name") String name) {
        User user = userService.get(name);
        
        if (user == null) {
            return new ArrayList<>();
        }
        
        return reservationService.getReservations(user);
    }
    
}
