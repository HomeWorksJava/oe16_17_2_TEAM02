
package datamodell;

import datamodell.resources.DrinkResource;
import datamodell.resources.PubTableResource;
import datamodell.resources.ReservationResource;
import datamodell.resources.UserResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
/**
 *
 * @author hallgato
 */
@ApplicationPath("rest")
public class RESTConfiguration extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        
        classes.add(DrinkResource.class);
        classes.add(PubTableResource.class);
        classes.add(ReservationResource.class);
        classes.add(UserResource.class);

        return classes;
    }
    
    
}
