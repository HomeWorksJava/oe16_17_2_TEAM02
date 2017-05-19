package datamodell.services;

import datamodell.entities.Drink;
import datamodell.entities.DrinkType;
import datamodell.entities.JSONABLEPRICE;
import datamodell.entities.JSONAble;
import datamodell.entities.Price;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hallgato
 */
@Stateless
public class DrinkService {

    @PersistenceContext(unitName = "tibiPU")
    private EntityManager entityManager;

    public Collection<Drink> listDrinks() {
        Collection<Drink> resl = entityManager.createNamedQuery("drink.findAll").getResultList();

        Random r = new Random();

        for (Drink t : resl) {
            try {

                List<Price> p = entityManager.createNamedQuery("price.findByDrink")
                        .setParameter("drink", t).getResultList();

                if (p.isEmpty()) {
                    p = new ArrayList<Price>();

                    for (int j = 0; j < 2; j++) {
                        Price e = new Price();

                        e.setDrink(t);

                        if (t.getType() == DrinkType.Spirit) {
                            e.setAmount(r.nextInt(3) + 1);
                            e.setMeasureUnit("cl");
                        } else {
                            e.setAmount(r.nextInt(5) + 2);
                            e.setMeasureUnit("dl");
                        }

                        e.setPrice(Math.round((r.nextDouble() + 0.1) * r.nextInt(5000)));

                        p.add(e);

                        entityManager.persist(e);
                    }
                }

                t.setPrices(p);
            } catch (Exception e) {
                e.toString();
            }
        }

        return resl;
    }

    public Collection<Drink> listDrinks(DrinkType type) {
        Collection<Drink> resl = entityManager.createNamedQuery("drink.findAll").getResultList();

        Random r = new Random();

        for (Drink t : resl) {
            try {

                List<Price> p = entityManager.createNamedQuery("price.findByDrink")
                        .setParameter("drink", t).getResultList();

                if (p.isEmpty()) {
                    p = new ArrayList<Price>();

                    for (int j = 0; j < 2; j++) {
                        Price e = new Price();

                        e.setDrink(t);

                        if (t.getType() == DrinkType.Spirit) {
                            e.setAmount(r.nextInt(3) + 1);
                            e.setMeasureUnit("cl");
                        } else {
                            e.setAmount(r.nextInt(5) + 2);
                            e.setMeasureUnit("dl");
                        }

                        e.setPrice(Math.round((r.nextDouble() + 0.1) * r.nextInt(5000)));

                        p.add(e);

                        entityManager.persist(e);
                    }
                }

                t.setPrices(p);
            } catch (Exception e) {
                e.toString();
            }
        }

        return resl;
    }

    public void createDrink(Drink drink) {
        entityManager.persist(drink);
    }
}
