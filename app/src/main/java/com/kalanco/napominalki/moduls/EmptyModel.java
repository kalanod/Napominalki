package com.kalanco.napominalki.moduls;

import java.util.ArrayList;

public class EmptyModel {
    public String name;
    public int age;
    public ArrayList<String> list;

    public EmptyModel(String name, int age) {
        this.name = name;
        this.age = age;
        this.list = new ArrayList<>();
        this.list.add("kalan1");
        this.list.add("kalan2");
    }

    public EmptyModel() {
    }

    @Override
    public String toString() {
        return "name='" + name + ", age=" + age;
    }
}
