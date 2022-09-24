package org.example;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Person currentPerson;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        currentPerson = new Person();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
        Quiz enemy;

        Item item = new Item("objeto lendário");
        Action action = new Action(currentPerson, person -> currentPerson.addItem(item));
        // create Quizzes
        enemy = new Quiz("Uma perigosa criatura surge das sombras carregando um objeto brilhante no pescoço");
        enemy.addOption("1. Atacar a criatura", true, action);
        enemy.addOption("2. Fugir", false);
        enemy.addOption("3. Esconder-se", false);

        // create the rooms
        outside = new Room("no saguão escuro com objetos jogados ao chão", enemy);
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Obrigado por jogar Adventure and Chaos. Tchau.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem vindo ao Adventure and Chaos!");
        System.out.println("Adventure and Chaos é um incrível novo jogo de aventura.");
        System.out.println("Digite '" + "help" + "' se você precisa de ajuda.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        AtomicBoolean wantToQuit = new AtomicBoolean(false);

        if(command.isUnknown()) {
            System.out.println("Eu não sei o que você quis dizer...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if ("help".equals(commandWord)) {
            printHelp();
        }
        else if ("quiz".equals(commandWord)) {
            runQuiz(command);
        }
        else if ("go".equals(commandWord)) {
            goRoom(command);
        }
        else if ("items".equals(commandWord)) {
            currentPerson.displayItens();
        }
        else if ("quit".equals(commandWord)) {
            wantToQuit.set(quit(command));
        }
        // else command not recognised.
        return wantToQuit.get();
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Você está perdido. Você esta sozinho. Você vagueia");
        System.out.println("Em um castelo abandonado.");
        System.out.println();
        System.out.println("Seus comandos são:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ir aonde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Não há porta");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private void runQuiz(Command command)
    {
        if(!currentRoom.hasQuiz()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Não há um quiz nesta sala para responder");
            return;
        }

        Quiz quiz = currentRoom.quiz;
        quiz.start();
    }
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if (command.hasSecondWord()) {
            System.out.println("Sair o que?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}
