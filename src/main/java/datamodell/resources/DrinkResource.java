package datamodell.resources;

import datamodell.dto.DrinkList;
import datamodell.entities.Drink;
import datamodell.entities.DrinkType;
import datamodell.entities.JSONABLELIST;
import datamodell.entities.JSONABLEPRICE;
import datamodell.entities.JSONAble;
import datamodell.entities.Price;
import datamodell.services.DrinkService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hallgato
 */
@Path("/drink")
public class DrinkResource {

    @Inject
    private DrinkService drinkService;

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public JSONABLELIST getDrinks() {
        try {
            JSONABLELIST newList = new JSONABLELIST(drinkService.listDrinks());

            return newList;

        } catch (NoResultException e) {
            return new JSONABLELIST();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public JSONABLELIST getDrinks(@QueryParam("type") DrinkType type) {
        return new JSONABLELIST(drinkService.listDrinks(type));
    }
}
