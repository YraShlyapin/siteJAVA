package org.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private String name, img,Sostav;

    int price;

    public Pizza(){

    }

    public Pizza(String name, String surname, String Sostav, int price) {
        this.name = name;
        this.img = surname;
        this.Sostav = Sostav;
        this.price = price;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSostav() {
        return Sostav;
    }

    public void setSostav(String sostav) {
        Sostav = sostav;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
