/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodell.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author hallgato
 */
public class JSONABLELIST {

    public List<JSONAble> beers;
    public List<JSONAble> spirits;
    public List<JSONAble> wines;

    public JSONABLELIST() {
        beers = new ArrayList<>();
        spirits = new ArrayList<>();
        wines = new ArrayList<>();
    }

    public JSONABLELIST(Collection<Drink> incomingList) {
        beers = new ArrayList<>();
        spirits = new ArrayList<>();
        wines = new ArrayList<>();

        for (Drink curr : incomingList) {

            switch (curr.getType()) {
                case Beer:
                    JSONAble x = new JSONAble();
                    x.name = curr.getName();
                    x.type = curr.getType();
                    for (Price pitem : curr.getPrices()) {
                        JSONABLEPRICE pr = new JSONABLEPRICE();

                        pr.amount = pitem.getAmount();
                        pr.measureUnit = pitem.getMeasureUnit();
                        pr.price = pitem.getPrice();

                        x.prices = new ArrayList<>();
                        x.prices.add(pr);
                    }
                    beers.add(x);
                    break;
                case Spirit:
                    x = new JSONAble();
                    x.name = curr.getName();
                    x.type = curr.getType();
                    for (Price pitem : curr.getPrices()) {
                        JSONABLEPRICE pr = new JSONABLEPRICE();

                        pr.amount = pitem.getAmount();
                        pr.measureUnit = pitem.getMeasureUnit();
                        pr.price = pitem.getPrice();

                        x.prices = new ArrayList<>();
                        x.prices.add(pr);
                    }
                    spirits.add(x);
                    break;
                case Wine:
                    x = new JSONAble();
                    x.name = curr.getName();
                    x.type = curr.getType();
                    for (Price pitem : curr.getPrices()) {
                        JSONABLEPRICE pr = new JSONABLEPRICE();

                        pr.amount = pitem.getAmount();
                        pr.measureUnit = pitem.getMeasureUnit();
                        pr.price = pitem.getPrice();

                        x.prices = new ArrayList<>();
                        x.prices.add(pr);
                    }
                    wines.add(x);
                    break;
                default:
                    break;
            }
        }
    }
}
