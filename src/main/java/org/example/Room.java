package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private final String description;
    private final String name;
    private final HashMap<String, Room> exits;        // stores exits of this room.

    public Quiz quiz;

    /**
     * Create a room described "name". Initially, it has
     * no exits. "name" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param name  The room's name.
     * @param description  THe room's description
     */
    public Room(String name, String description, Action ...actions)
    {
        this.description = description;
        this.name = name;
        exits = new HashMap<>();
    }

    /**
     * Create a room described "name". Initially, it has
     * no exits. "name" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param name  The room's name.
     * @param description  The room's description
     * @param quiz  The room's quiz interaction
     */
    public Room(String name, String description, Quiz quiz)
    {
        this.name = name;
        this.description = description;
        this.quiz = quiz;
        exits = new HashMap<>();
    }
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "Você está em " + name + ".\n" + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        StringBuilder returnString = new StringBuilder("Portas:");
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString.append(" ").append(exit);
        }
        return returnString.toString();
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }


    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public boolean hasQuiz() {
        return this.quiz != null;
    }
}

