/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodell.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author hallgato
 */
@Table(name="price")
@Entity
@NamedQueries({
    @NamedQuery(name = "price.findByDrink", query = "SELECT c FROM Price c WHERE c.drink = :drink")})
public class Price implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String measureUnit;
    private int amount;
    private double price;
    
    @ManyToOne
    private Drink drink;

    public Price() {
    }
    
    public Price(String MeasureUnit, int Amount, double price)
    {
        this.measureUnit = MeasureUnit;
        this.amount = Amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }
    
}
