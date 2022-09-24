package org.example;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Person {
    private ArrayList<Item> items;

    public Person() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) { items.add(item); }

    public void displayItens() {
        if (items.size() > 0) {
            IntStream.range(0, items.size())
                    .mapToObj(i -> i + ". " + items.get(i).getName())
                    .forEach(System.out::println);
            return;
        }

        System.out.println("Inventario vazio");
    }
}
