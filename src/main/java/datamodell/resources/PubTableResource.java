
package datamodell.resources;

import datamodell.entities.PubTable;
import datamodell.entities.Role;
import datamodell.services.PubTableService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hallgato
 */
@Path("/table")
@Produces({MediaType.APPLICATION_JSON})
@RolesAllowed({Role.User,Role.MANAGER})
public class PubTableResource {
    
    @Inject
    private PubTableService pubTableService;
    
    @Path("/all")
    @GET
    public Collection<PubTable> getTabes() {
        return pubTableService.getTables();
    }
    
    @Path("/free")
    @GET
    public Collection<PubTable> getFreeTables(@QueryParam("date") Date date) {
        try {
        return pubTableService.getFreeTables(date);
        }catch(NoResultException e) {
            return new ArrayList();
        }
    }
}
