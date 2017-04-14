/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * this class takes care of all the items the game takes in
 *
 * @author qureshi225
 */
public class Item {

    private String primaryName;
    private int score;
    private Hashtable<String, String> messages = new Hashtable<String, String>();
    private Hashtable<String, String[]> extraCommands = new Hashtable<String, String[]>();

    public Item(BufferedReader buffer) {
        try {
            String currentLine = buffer.readLine();
            primaryName = currentLine;
            score = Integer.parseInt(buffer.readLine());
            currentLine = buffer.readLine();
            while (!currentLine.equals("---")) {
                String[] split = currentLine.split(":");
                if (split[0].contains("[")){
                    String[] extra = split[0].split("\\[");
                    if (extra.length == 2) {
                        String commandsString = extra[1].substring(0, extra[1].length() - 2);
                        String[] commands = commandsString.split(",");
                        extraCommands.put(extra[0], commands);
                    }
                    messages.put(extra[0], split[1]);
                } else {
                    messages.put(split[0], split[1]);
                }
            currentLine = buffer.readLine();
            }
        buffer.mark(1);

    } catch (Exception e ) {
            System.out.println("This file isn't formatted correctly!!!!");
        System.exit(54);
    }
}

/**
 * checks to see if the item in question goes by the name that is trying to be
 * used.
 *
 * @param name the string you are trying to compare the item name too
 * @return by default returns true unless there are other names an item can go
 * by
 */
public boolean goesBy(String name) {
        return true;
    }

    /**
     * the name the item is connected to in the hashtable
     *
     * @return the string that is the name of the item.
     */
    public String getPrimaryName() {
        return primaryName;
    }

    /**
     * this gets the message based on the verb contained int he hashtable and
     * calls on the any extra commands the verb
     *
     * @param verb the verb given to search for the message of
     * @return the messaged connected to the verb
     */
    public String getMessageForVerb(String verb) {
        EventFactory in = EventFactory.getInstance();
        if (extraCommands.containsKey(verb)) {
            for (int i = 0; i < extraCommands.get(verb).length; i++) {
                String todo = extraCommands.get(verb)[i];
                Event done = in.parse(todo, this);
                done.execute();
            }
        }
        return messages.getOrDefault(verb, null);
    }

    /**
     * gives the integer weight of the item(the so called score)
     *
     * @return the weight of the object
     */
    public int getScore() {
        return this.score;
    }

    /**
     * gets the list of verbs out of the hashtable for messages
     *
     * @return a set of the verbs.
     */
    public Set<String> getKeys() {
        return messages.keySet();
    }

    /**
     * the unused tostring of the item.
     */
    public String toString() {
        return "";
    }

}
