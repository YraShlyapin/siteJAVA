package org.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private Long idUset;

    private String name,surname,patronymic,pizzaType,isAcute;

    int price,size;

    Boolean isReady;

    public Post() {
    }
//
    public Post(String name, String surname, String patronymic, String pizzaType, String isAcute,Long idUset, int price,int size,boolean isReady) {
        this.idUset = idUset;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.pizzaType = pizzaType;
        this.isAcute = isAcute;
        this.price = price;
        this.size=size;
        this.isReady=isReady;
    }

    public Long getIdUset() {
        return idUset;
    }

    public void setIdUset(Long idUset) {
        this.idUset = idUset;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public String getIsAcute() {
        return isAcute;
    }

    public void setIsAcute(String isAcute) {
        this.isAcute = isAcute;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }
}
