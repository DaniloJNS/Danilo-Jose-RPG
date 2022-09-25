package org.example;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Person {
    private final ArrayList<Item> items;
    private boolean alive;

    public Person() {
        this.items = new ArrayList<>();
        this.alive = true;
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

    public void kill() {
        alive = false;
        System.out.println("Você está morto, GAME-OVER");
    }

    public boolean isAlive() {
        return alive;
    }
}
