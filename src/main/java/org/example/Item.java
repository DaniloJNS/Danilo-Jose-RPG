package org.example;

import java.util.ArrayList;

public class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Person person) {
        person.addItem(this);
    }
}
