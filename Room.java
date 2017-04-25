/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;

/**
 * Creates and manages Room objects
 *
 * @author qureshi225
 */
public class Room {

    private String title;
    private String desc = "";
    private boolean beenHere;
    private ArrayList<Exit> exitsTo = new ArrayList<Exit>();
    private ArrayList<Item> containingItems = new ArrayList<Item>();
    private Monster monster = null;
    private boolean monsterHere = false;
    private Shopkeeper shop = Shopkeeper.Instance();
    private boolean containsShop = false;

    /**
     * Constructor for Room
     *
     * @param buffer
     * @param dungeon
     * @param initState
     */
    public Room(BufferedReader buffer, Dungeon dungeon, boolean initState) {
        try {
            buffer.reset();
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
                currentLine=buffer.readLine();
                }
            } else {
                currentLine=buffer.readLine();
            }
        } catch (Exception e) {

        }
    }

    /**
     * Alternate constructor for Room
     *
     * @param title
     */
    public Room(String title) {
        this.title = title;
        beenHere = false;
    }

    /**
     * Getter method for the name of the room
     *
     * @return String title (name of room)
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for the room's description
     *
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc += desc;
    }

    /**
     * Prints the full description of the Room if the player has not been in the
     * room previously If the user has been to the room before, prints only the
     * title
     *
     * @return description
     */
    public String describe() {
        String fullDescription = "";
        if (GameState.Instance().getVerbose() == true) {
            for (int i = 0; i < exitsTo.size(); i++) {
                fullDescription += exitsTo.get(i).getDescription() + "\n";
            }
        } 
            for (int i = 0; i < containingItems.size(); i++) {
                fullDescription += "You see a " + containingItems.get(i).getPrimaryName() + ".\n";
            
        }

        if (!beenHere) {
            beenHere = true;
            fullDescription = this.desc + fullDescription;
            if (this.containsShop) {
                fullDescription = fullDescription + "\n" + "You hear a booming voice echo through the room. \n It declares, ' Welcome young "
                        + "traveler to  " + Shopkeeper.Instance().getMonName() + "'s Shop!'";
                Random rng = new Random();
                int seed = rng.nextInt();
                Shopkeeper.Instance().getSelling(seed);
            }
            return this.title + "\n" + fullDescription;
        } else {
            if (this.containsShop) {
                fullDescription = fullDescription + "\n The voice booms through the room again. \n 'Welcome back young one, i have a new stock for you!'";
                Random rng = new Random();
                int seed = rng.nextInt();
                Shopkeeper.Instance().getSelling(seed);
            }
            return this.title + "\n" + fullDescription;
        }
    }

    /**
     * Checks if leaving in a given direction is possible, and if it is
     * possible, returns the new room If a direction cannot be moved in, prints
     * whether there is an exit that needs to be unlocked
     *
     * @param dir
     * @return new room
     */
    public Room leaveBy(String dir) {
        for (int i = 0; i < exitsTo.size(); i++) {
            Exit getExit = exitsTo.get(i);
            String directionGoing = getExit.getDir();
            if (dir.equals(directionGoing)) {
                if (getExit.getLockState()) {
                    System.out.println("Looks like you need a key to go this way.");
                    return null;
                } else {
                    return exitsTo.get(i).getDest();
                }
            }
        }
        return null;
    }

    /**
     * Adds given exits to the ArrayList of exits for the given room
     *
     * @param exit
     */
    public void addExit(Exit exit) {
        exitsTo.add(exit);

    }

    /**
     * Saves the current state of the room
     *
     * @param writer
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
     *
     * @param buffer
     * @param dungeon
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
     *
     * @param i
     */
    public void add(Item i) {
        containingItems.add(i);
    }

    /**
     * Removes an item from the room and from the item ArrayList
     *
     * @param i
     */
    public void remove(Item i) {
        containingItems.remove(i);
    }

    /**
     * Searches for an item by name in the items list and returns it if it
     * contains that item
     *
     * @param n
     * @return item
     */
    public Item getItemNamed(String n) {
        if (!containingItems.isEmpty()) {
            for (Item i : containingItems) {
                if (i.getPrimaryName().equals(n)) {
                    return i;
                }
            }
        }
        return null;
    }

    public boolean contains(Item item) {
        if (containingItems.contains(item)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean containsShopKeep() {
        return this.containsShop;
    }

    public void addShop() {
        this.containsShop = true;
        this.shop = Shopkeeper.Instance();
    }

    /**
     * this evaluates the games boolean danger and decided whether to add a
     * monster to a room or not.
     */
    public void addMonster() {
        if (GameState.Instance().getDanger() == true) {
            if (hasMonster() == false) {
                switch ((int) (Math.random() * 3)) {
                    case 0:
                    case 2:
                        break;
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                        int grab = (int) (Math.random() * 4);
                        Monster[] temp = GameState.Instance().getMon();
                        this.monster = temp[grab];
                        this.monsterHere = true;
                        monster.setLife();
                        monster.setAttack();
                        break;
                }
            }
        } else {

        }
    }

    /**
     * when a monster dies this removes it from the room and adds the loot to
     * your inventory
     */
    public void removeMonster() {
        this.monster.getLoot();
        this.monster = null;
        this.monsterHere = false;
    }

    /**
     *
     * @return if room contains monster
     */
    public boolean hasMonster() {
        if (this.monsterHere == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * this returns monster
     *
     * @return monster
     */
    public Monster getMonster() {
        return this.monster;
    }
    public void setMonster(){
        this.monsterHere=true;
        this.monster=Shopkeeper.Instance();
    }

}
