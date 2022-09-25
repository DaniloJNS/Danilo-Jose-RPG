package org.example;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Person {
    private final ArrayList<Item> inventory;
    private boolean alive;

    public Person() {
        this.inventory = new ArrayList<>();
        this.alive = true;
    }

    public void addItem(Item item) { inventory.add(item); }

    public void displayInventory() {
        if (inventory.size() > 0) {
            IntStream.range(0, inventory.size())
                    .mapToObj(i -> i + ". " + inventory.get(i).getName())
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
