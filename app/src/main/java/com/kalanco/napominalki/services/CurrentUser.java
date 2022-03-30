package com.kalanco.napominalki.services;

import com.kalanco.napominalki.moduls.Napominalka;

import java.io.Serializable;
import java.util.ArrayList;

public class CurrentUser implements Serializable {
    private static String userId;
    private String email;
    private String name;
    private ArrayList<Napominalka> list;

    public CurrentUser(String email, String name, String userId1) {
        userId = userId1;
        this.email = email;
        this.name = name;
        this.list = new ArrayList<>();
    }

    public void addNapom(String test) {
        this.list.add(new Napominalka(test));
    }

    public static String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Napominalka> getList() {
        return list;
    }

    public CurrentUser() {
    }
}
