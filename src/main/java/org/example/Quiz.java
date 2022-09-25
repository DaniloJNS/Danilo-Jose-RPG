package org.example;

import java.util.ArrayList;

public class Quiz {
    private final String question;
    private final Parser parse;
    private final ArrayList<Option> options;

    public Quiz(String question) {
        this.question = question;
        this.parse = new Parser(new String[]{"1", "2", "3"});
        this.options = new ArrayList<>();
    }

    public void addOption(String question, Action ...action) {
        Option option = new Option(question, action);
        options.add(option);
    }

    public void getDetails() {
        System.out.println(question);
    }

    public void start() {
        display();
        while (!processCommand(parse.getCommand().getCommandWord()));
    }

    private void display() {
        getDetails();
        options.forEach(option -> {
            if (option.isActive()) {
                option.display();
            }
        });
    }

    private boolean processCommand(String response) {
        if (response == null) {
            System.out.println("Ops!!! resposta invalida");
            return false;
        }

        int option_index = Integer.parseInt(response);

        Option option = options.get(option_index - 1);

        if (!option.isActive()) {
            System.out.println("Ops!!! Está ação já foi executada");
            return true;
        }

        option.runActions();

        return true;
    }
}
