/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
/**
 * Creates and manages Room objects
 * @author qureshi225
 */
public class Room {

    private String title;
    private String desc = "";
    private boolean beenHere;
    private ArrayList<Exit> exitsTo = new ArrayList<Exit>();
    private ArrayList<Item> containingItems = new ArrayList<Item>();
    private Monster monster=null;
    private boolean monsterHere =false;

    /**
    * Constructor for Room
    * @param BufferedReader buffer, Dungeon dungeon, boolean initState
    */
    public Room(BufferedReader buffer, Dungeon dungeon, boolean initState) {
        try {
            String currentLine = buffer.readLine();
            if (!currentLine.equals("===")) {
                this.title = currentLine;
                currentLine = buffer.readLine();
                while (!currentLine.equals("---")) {

                    if (currentLine.startsWith("Contents:")) {
                        if (initState) {
                            String[] split = currentLine.split(":");
                            String[] itemsAdding = split[1].trim().split(",");
                            for (int i = 0; i < itemsAdding.length; i++) {
                                containingItems.add(dungeon.getItem(itemsAdding[i].trim()));
                            }
                        }
                        currentLine = buffer.readLine();
                    }
                    this.setDesc(currentLine + "\n");
                    currentLine = buffer.readLine();
                }
            } else {
            }
        } catch (Exception e) {

        }
    }
    
    /**
    * Alternate constructor for Room
    * @param String title
    */
    public Room(String title) {
        this.title = title;
        beenHere = false;
    }

    /**
    * Getter method for the name of the room
    * @return String title (name of room)
    */
    public String getTitle() {
        return title;
    }

    /**
    * Setter method for the room's description
    * @param String desc 
    */
    public void setDesc(String desc) {
        this.desc += desc;
    }

    /**
    * Prints the full description of the Room if the player has not been in the room previously
    * If the user has been to the room before, prints only the title
    */
    public String describe() {
        String fullDescription = "";
        for (int i = 0; i < exitsTo.size(); i++) {
            fullDescription += exitsTo.get(i).getDescription() + "\n";
        }
        for (int i = 0; i < containingItems.size(); i++) {
            fullDescription += "You see a " + containingItems.get(i).getPrimaryName() + ".\n";
        }
        if (!beenHere) {
            beenHere = true;
            fullDescription = this.desc + fullDescription;
            return this.title + "\n" + fullDescription;
        } else {
            return this.title + "\n" + fullDescription;
        }
    }

    /**
    * Checks if leaving in a given direction is possible, and if it is possible, returns the new room
    * If a direction cannot be moved in, prints whether there is an exit that needs to be unlocked
    * @param String dir
    */
    public Room leaveBy(String dir) {
        for (int i = 0; i < exitsTo.size(); i++) {
            Exit getExit = exitsTo.get(i);
            String directionGoing = getExit.getDir();
            if (dir.equals(directionGoing)) {
                return exitsTo.get(i).getDest();
            }
        }
        return null;
    }

    /**
    * Adds given exits to the ArrayList of exits for the given room
    * @param Exit exit
    */
    public void addExit(Exit exit) {
        exitsTo.add(exit);

    }

    /**
    * Saves the current state of the room
    * @param BufferedWriter writer
    */
    public void storeState(BufferedWriter writer) {
        try {
            if (beenHere) {
                writer.write(this.getTitle() + ":\n");
                writer.write("beenHere=true\n");
                String contains = "";
                for (Item i : containingItems) {
                    contains += i.getPrimaryName() + ",";
                }
                if (!contains.isEmpty()) {
                    contains = contains.substring(0, contains.length() - 1);
                    writer.write("Contents: " + contains + "\n");
                }
            } else {
                writer.write(this.getTitle() + ":\n");
                writer.write("beenHere=false\n");

                String contains = "";
                for (Item i : containingItems) {
                    contains += i.getPrimaryName() + ",";
                }
                if (!contains.isEmpty()) {
                    contains = contains.substring(0, contains.length() - 1);
                    writer.write("Contents: " + contains + "\n");
                }
            }
            writer.write("---\n");
        } catch (Exception e) {

        }
    }

    /**
    * Restores the previous state of the room
    * @param BufferedReader buffer, Dungeon dungeon
    */
    public void restoreState(BufferedReader buffer, Dungeon dungeon) {
        try {
            String line = buffer.readLine();
            if (line.equals("beenHere=true")) {
                beenHere = true;
            } else if (!line.equals("beenHere=false")) {
                System.out.println("THIS FILE ISN'T CORRECT.");
                System.exit(54);
            }
            line = buffer.readLine();
            if (line.startsWith("Contents:")) {
                String[] split = line.split(":");
                String[] itemsAdding = split[1].trim().split(",");
                for (int i = 0; i < itemsAdding.length; i++) {
                    containingItems.add(dungeon.getItem(itemsAdding[i].trim()));
                }
                line = buffer.readLine();
            }
        } catch (Exception e) {

        }
    }

    /**
    * Adds an item to the ArrayList of items that the room contains
    * @param Item i
    */
    public void add(Item i) {
        containingItems.add(i);
    }

    /**
    * Removes an item from the room and from the item ArrayList
    * @param Item i
    */
    public void remove(Item i) {
        containingItems.remove(i);
    }

    /**
    * Searches for an item by name in the items list and returns it if it contains that item
    * @param String n
    */
    public Item getItemNamed(String n) {
        if(!containingItems.isEmpty()){
        for (Item i : containingItems) {
            if (i.getPrimaryName().equals(n)) {
                return i;
            }
        }
        }
        return null;
    }
    public boolean contains(Item item){
        if(containingItems.contains(item)){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * this adds a monster to the room
    */
    public void addMonster(){
        if(hasMonster() == false){
            switch((int)(Math.random()*1)){
            case 0:
                break;
            case 1:
                int grab = (int)(Math.random()*5)+2;
                Monster[] temp =GameState.Instance().getMon();
                this.monster= temp[grab];
                this.monsterHere=true;
                monster.setLife();
                monster.setAttack();
                break;      
            }
        }
    }
    /**
     * when a monster dies this removes it from the room and adds the loot to your inventory
     */
    public void removeMonster(){
        this.monster.getLoot();
        this.monster=null;
        this.monsterHere=false;
    }
    /**
     * 
     * @return if room contains monster
     */
    public boolean hasMonster(){
        if(this.monsterHere==false){
            return false;
        }else{
            return true;
        }
    }
    /**
     * this returns monster
     * @return monster
     */
    public Monster getMonster(){
        return this.monster;
    }

}
