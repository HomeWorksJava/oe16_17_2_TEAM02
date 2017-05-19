
package datamodell.services;

import datamodell.entities.Drink;
import datamodell.entities.DrinkType;
import datamodell.entities.Price;
import datamodell.entities.PubTable;
import datamodell.entities.Role;
import datamodell.entities.User;
import datamodell.entities.UserRoleRelation;
import datamodell.resources.UserResource;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author hallgato
 */
@Stateless
@Startup
public class CreateDataBase {
    
    @Inject
    private UserService userService;
    
    @Inject
    private DrinkService drinkService;
    
    @Inject
    private PubTableService pubTableService;
    
    @PostConstruct
    public void init() {
        System.out.println("Create db");
        
        User admin = new User();
        admin.setEmail("admin@admin.hu");
        admin.setPassword(UserResource.hashPassword("qwe123"));
        admin.setUsername("admin");
        admin.setTelephone("+36301234567");
        
        Set<UserRoleRelation> roles = new HashSet<>();
        roles.add(new UserRoleRelation(Role.MANAGER,admin));
        roles.add(new UserRoleRelation(Role.User,admin));
        
        admin.setRoles(roles);
        userService.register(admin);
        
        drinkService.createDrink(new Drink("Kőbányai", DrinkType.Beer, 0.4, new Price("dl",3,190), new Price("dl",5,250)));
        drinkService.createDrink(new Drink("Hunters", DrinkType.Beer, 6.2, new Price("dl",5,920), new Price("l",1,1840)));
        drinkService.createDrink(new Drink("A belga rettenetes", DrinkType.Beer, 11.95, new Price("dl",3,190)));
        
        drinkService.createDrink(new Drink("Sima Bor", DrinkType.Wine, 0.2, new Price("dl",10,500), new Price("dl",15,700)));
        drinkService.createDrink(new Drink("Neszmélyi Irsai Olivér", DrinkType.Wine, 11.6, new Price("dl",7,3190), new Price("dl",2,1100)));
        drinkService.createDrink(new Drink("Egri Muscat Ottonel", DrinkType.Wine, 17.5, new Price("dl",7,4250), new Price("dl",2,1500)));
        drinkService.createDrink(new Drink("Hilltop Prémium Chardonnay", DrinkType.Wine, 21.2, new Price("dl",7,5250), new Price("dl",2,1750)));
        
        drinkService.createDrink(new Drink("Jim Beam", DrinkType.Spirit, 62.7, new Price("cl",3,390)));
        drinkService.createDrink(new Drink("Jack Daniel's Gentleman's Jack", DrinkType.Spirit, 42.2, new Price("cl",4,650), new Price("dl",1,1250)));
        drinkService.createDrink(new Drink("Chivas Regal", DrinkType.Spirit, 72.8, new Price("cl",3,850), new Price("cl",5,1450)));
        
        pubTableService.createPubtable(new PubTable("hosszú",10));
        pubTableService.createPubtable(new PubTable("kicsi1",4));
        pubTableService.createPubtable(new PubTable("kicsi2",4));
        
        System.out.println("Create db finished");
    }
}
