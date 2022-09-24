package org.example;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;
import java.util.Arrays;

public class Option {
    private final String name;
    private final boolean correct;
    private final ArrayList<Action> actions;

    public Option(String name, boolean correct, Action ...actions) {
        this.name = name;
        this.correct = correct;
        this.actions = new ArrayList<>();
        setActions(actions);
    }

    public void display() {
        System.out.println(name);
    }

    public void runActions() {
        actions.forEach(Action::run);
    }

    private void setActions(Action ...actions) {
        this.actions.addAll(Arrays.asList(actions));
    }

    public boolean isCorrect() {
        return this.correct;
    }
}
