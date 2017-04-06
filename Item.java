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
 *this class takes care of all the items the game takes in
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
/**
*checks to see if the item in question goes by the name that is trying to be used.
*@param name
*          the string you are trying to compare the item name too 
*@return by default returns true unless there are other names an item can go by
*/
    public boolean goesBy(String name) {
        return true;
    }
/**
* the name the item is connected to in the hashtable
*@return the string that is the name of the item.
*/
    public String getPrimaryName() {
        return primaryName;
    }
/**
*this gets the message based on the verb contained int he hashtable
*@param verb
*           the verb given to search for the message of
*@return the messaged connected to the verb
*/
    public String getMessageForVerb(String verb) {
        return messages.getOrDefault(verb, null);
    }
/**
*gives the interget weight of the item(the so called score)
*@return the weight of the object
*/
    public int getWeight() {
        return this.weight;
    }
    /**
    * gets the list of verbs out of the hashtable for messages
    *@return a set of the verbs.
    */
    public Set<String> getKeys(){
        return messages.keySet();
    }
/**
* the unused tostring of the item.
*/
    public String toString() {
        return "";
    }

}
