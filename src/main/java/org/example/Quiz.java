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

    public void addOption(String question, boolean isCorrect, Action ...action) {
        Option option = new Option(question, isCorrect, action);
        options.add(option);
    }

    public void getDetails() {
        System.out.println(question);
    }

    public void start() {
        display();
        Command command = parse.getCommand();
        processCommand(command.getCommandWord());
    }

    private void display() {
        getDetails();
        options.forEach(Option::display);
    }

    private void processCommand(String response) {
        if (response == null) {
            System.out.println("Ops!!! resposta invalida");
            return;
        }

        int option_index = Integer.parseInt(response);

        Option option = options.get(option_index - 1);

        option.runActions();
    }
}
