package org.example;

import java.util.ArrayList;
import java.util.Iterator;

public class Quiz {
    private String question;
    private Parser parse;
    private ArrayList<String> options;

    public Quiz(String question) {
        this.question = question;
        this.parse = new Parser();
        this.options = new ArrayList<String>();
    }

    public void addOption(String option) {
        options.add(options.size(), option);
    }

    public void getDetails() {
        System.out.println(question);
    }


    public void start() {
        display();
        Command Command = this.parse.getCommand();
    }

    private void display() {
        getDetails();
        options.forEach((option) -> {
            System.out.println(option);
        });
    }
}
