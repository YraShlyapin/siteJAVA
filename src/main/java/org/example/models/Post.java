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

    private String name,surname,patronymic,pizzaType,isAcute;

    public Post() {
    }

    public Post(String name, String surname, String patronymic, String pizzaType, String isAcute) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.pizzaType = pizzaType;
        this.isAcute = isAcute;
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
}
