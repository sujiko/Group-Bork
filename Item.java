/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 *
 * @author qureshi225
 */
public class Item {

    private String primaryName;
    private int weight;
    private Hashtable<String, String> messages = new Hashtable<String, String>();

    public Item(BufferedReader buffer) {
        try {
            String currentLine = buffer.readLine();
            primaryName = currentLine;
            weight = Integer.parseInt(buffer.readLine());
            currentLine = buffer.readLine();
            while (!currentLine.equals("---")) {
                String[] split = currentLine.split(":");
                messages.put(split[0], split[1]);
                currentLine = buffer.readLine();
            }
            buffer.mark(1);

        } catch (Exception e) {

        }
    }

    public boolean goesBy(String name) {
        return true;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public String getMessageForVerb(String verb) {
        return messages.getOrDefault(verb, null);
    }

    public int getWeight() {
        return this.weight;
    }
    public Set<String> getKeys(){
        return messages.keySet();
    }

    public String toString() {
        return "";
    }

}
