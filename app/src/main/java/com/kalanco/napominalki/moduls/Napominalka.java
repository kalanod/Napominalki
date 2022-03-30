package com.kalanco.napominalki.moduls;

import java.util.Random;

public class Napominalka {
    public String title;
    private String id;
    public boolean isCheck;
    Random random = new Random();

    public Napominalka(String title) {
        this.title = title;
        isCheck = false;
        id = Integer.toString(random.nextInt());
    }

    public Napominalka() {
    }

    public String getId() {
        return id;
    }
}
