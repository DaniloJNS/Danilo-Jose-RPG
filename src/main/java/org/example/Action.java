package org.example;

public class Action {
    private final Actionable action;
    private Person person;

    Action(Person person, Actionable action) {
        this.action = action;
        this.person = person;
    }

    public void run() {
        action.run(person);
    }
}
