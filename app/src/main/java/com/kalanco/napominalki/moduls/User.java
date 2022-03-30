package com.kalanco.napominalki.moduls;

import java.util.ArrayList;
import java.util.Date;

public class User {
    public String name;
    public String email;
    public Date date;
    public String id;
    //public ArrayList<Napominalka> napominalkas;

    public User(String name, String email, long date, String id) {
        this.name = name;
        this.email = email;
        this.date = new Date(date);
        this.id = id;
        //napominalkas = new ArrayList<>();
        //napominalkas.add(new Napominalka("asd"));
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                '}';
    }
}
