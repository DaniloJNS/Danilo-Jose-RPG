package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Option {
    private final String name;
    private final ArrayList<Action> actions;

    private boolean active;

    public Option(String name, Action ...actions) {
        this.name = name;
        this.actions = new ArrayList<>();
        this.active = true;
        setActions(actions);
    }

    public boolean isActive() {
        return this.active;
    }

    public void display() {
        System.out.println(name);
    }

    public void runActions() {
        if (actions.size() > 0) {
            actions.forEach(Action::run);
            this.active = false;
        }
    }

    private void setActions(Action ...actions) {
        this.actions.addAll(Arrays.asList(actions));
    }
}
