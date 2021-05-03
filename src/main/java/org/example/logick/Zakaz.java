package org.example.logick;


public class Zakaz {
    public int id;
    public String name;
    public String surname;
    public String patronymic;
    public String pizzaType;
    public String isAcute;

    public Zakaz(String a, String b, String c, String d, String e,int id) {
        this.name = a;
        this.surname = b;
        this.patronymic = c;
        this.pizzaType = d;
        this.isAcute = e;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Zakaz{" +
                "a='" + name + '\'' +
                ", b='" + surname + '\'' +
                ", c='" + patronymic + '\'' +
                ", d='" + pizzaType + '\'' +
                ", e=" + isAcute +
                '}';
    }
}
