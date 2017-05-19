
package datamodell.dto;

import datamodell.entities.Drink;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 *
 * @author hallgato
 */

public class DrinkList {
    public List<Drink> beers;
    public List<Drink> spirits;
    public List<Drink> wines;
    
    public DrinkList()
    {
        beers = new ArrayList<>();
        spirits = new ArrayList<>();
        wines = new ArrayList<>();
    }
    
    public DrinkList(Collection<Drink> incomingList)
    {
        beers = new ArrayList<>();
        spirits = new ArrayList<>();
        wines = new ArrayList<>();
        
        for(Drink curr : incomingList)
        {
          
            switch (curr.getType()) {
                case Beer:
                    beers.add(curr);
                    break;
                case Spirit:
                    spirits.add(curr);
                    break;
                case Wine:
                    wines.add(curr);
                    break;
                default:
                    break;
            }
        }
    }
}
