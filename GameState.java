/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import static java.lang.System.out;
import java.util.ArrayList;

/**
 * this keeps track of the players gamestate in bork, it also handles reading in
 * and out save sates
 *
 * @author qureshi225
 */
public class GameState {

    private static GameState onlyInstance;
    private Room currentRoom;
    private Dungeon currentDungeon;
    private boolean initState = true;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private int score;
    private int health;
    private int maxHealth;
    public  boolean running=true;
    private ArrayList<Item> outOfGame= new ArrayList<Item>();

    private GameState() {
    }

    /**
     * this creates and stores only one instance of the gamestate class
     */
    public static synchronized GameState Instance() {
        if (onlyInstance == null) {
            onlyInstance = new GameState();
        }
        return onlyInstance;
    }

    /**
     * creates the dungeon
     *
     * @param dungeon creates the dungeon that is to be stored int he gamestate
     */
    public void initialize(Dungeon dungeon) {
        currentDungeon = dungeon;
        currentRoom = currentDungeon.getEntry();
        genInitialHealth();
    }

    /**
     * this gets the current room an adventurer is in.
     *
     * @return the room that the adventurer is linked to standing in.
     */
    public Room getAdvenurersCurrentRoom() {
        return currentRoom;
    }

    /**
     * sets which room the adventurer is in
     *
     * @param room give this command the room the adventurere is moving into or
     * currently in.
     */
    public void setAdventurersCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * this returns the dungeon object.
     *
     * @return the dungeon
     */
    public Dungeon getDungeon() {
        return currentDungeon;
    }

    /**
     * gets a list of all the the items in the players inventory.
     *
     * @return returns the string names of the items int he players invetory.
     */
    public ArrayList<String> getInventoryNames() {
        ArrayList<String> toReturn = new ArrayList<String>();
        for (Item i : inventory) {
            toReturn.add(i.getPrimaryName());
        }
        return toReturn;
    }

    /**
     * this adds an item to the players inventory
     *
     * @param i the item to add to the inventory
     */
    public void addToInventory(Item i) {
        inventory.add(i);
        this.currentRoom.remove(i);
    }

    /**
     * this removes an item to the players inventory
     *
     * @param i the item to remove to the inventory
     */
    public void removeFrominventory(Item i) {
        inventory.remove(i);
        this.currentRoom.add(i);
    }

    /**
     * this gets an item to the players inventory or from a players surroundings
     *
     * @param name the item to find
     * @return the item, if it is found.
     */
    public Item getItemInVicinityNamed(String name) {
        if (this.getItemFromInventoryNamed(name) != null) {
            return this.getItemFromInventoryNamed(name);
        }
        return this.currentRoom.getItemNamed(name);
    }

    /**
     * this gets an item to the players inventory
     *
     * @param name the item to find
     * @return the item, if it is found.
     */
    public Item getItemFromInventoryNamed(String name) {
        for (Item i : inventory) {
            if (i.getPrimaryName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    /**
     * creates the savestate for a player
     *
     * @param saveName takes a name for the players file to be saved as and
     * creates a save from it.
     */
    public void store(String saveName) {
        BufferedWriter writer = null;
        try {
            String fileName = "";
            if (saveName.endsWith(".sav")) {
                fileName = saveName;
            } else {
                fileName = saveName + ".sav";
            }
            FileWriter write = new FileWriter(fileName);
            writer = new BufferedWriter(write);
        } catch (Exception e) {
        }
        try {
            writer.write("Bork V3.0\n");
            currentDungeon.storeState(writer);
            writer.write("Adventurer: \n");
            writer.write("Current Room: " + onlyInstance.getAdvenurersCurrentRoom().getTitle() + "\n");
            String itemsInInventory = "";
            for (Item i : inventory) {
                itemsInInventory += i.getPrimaryName() + ",";
            }
            itemsInInventory = itemsInInventory.substring(0, itemsInInventory.length() - 1);
            writer.write("Inventory: " + itemsInInventory + "\n");
            String itemsOutOfGame="";
            for(Item j: outOfGame){
                itemsOutOfGame+= j.getPrimaryName()+",";
            }
            itemsOutOfGame= itemsOutOfGame.substring(0,itemsOutOfGame.length()-1);
            writer.write("Items that no longer exist: " + itemsOutOfGame+ "\n");
            writer.close();
        } catch (Exception e) {

        }

    }

    /**
     * restores the gamestate from a save file for the player
     *
     * @param fileName takes a file name and reads the files to create a bork
     * game back to how the player had it.
     */
    public void restore(String fileName) {
        BufferedReader buffer = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            buffer = new BufferedReader(fileReader);
        } catch (Exception e) {
            System.out.println("this isn't a proper .sav file.");
            System.exit(54);
        }
        try {
            buffer.readLine();
            buffer.readLine();
            String[] split = buffer.readLine().split(":");
            String line = split[1].trim();
            initState = false;
            currentDungeon = new Dungeon(line, initState);
            line = buffer.readLine();
            //while(!line.equals("===")){
            currentDungeon.restoreState(buffer);
            buffer.readLine();
            line = buffer.readLine();
            split = line.split(":");
            line = split[1].trim();
            currentRoom = currentDungeon.getRoom(line);
            line = buffer.readLine();
            split = line.split(":");
            line = split[1].trim();
            String[] loadItems = line.split(",");
            for (String i : loadItems) {
                inventory.add(currentDungeon.getItem(i));
            }
            line = buffer.readLine();
            split = line.split(":");
            line = split[1].trim();
            String[] destroyItems = line.split(",");
            for(String i : destroyItems){
                Event getRid= new DisappearEvent(this.getDungeon().getItem(i));
                getRid.execute();
            }

        } catch (Exception e) {

        }

    }

    /* 
    * this gives the current score of the player
    *@return  the integer of the players score
     */
    public int getScore() {
        return this.score;
    }

    /* 
    * this gives the current health of the player
    *@return  the integer of the players health
     */
    public int getHealth() {
        return this.health;
    }
    /*
    * this returns the players initial health
    *@return the integer of the players inital health
    */
    public int getMaxHealth(){
        return this.maxHealth;
    }
    /*
    * this generates the characters initial health.
    *
    */
    public void genInitialHealth(){
        int Health = (int)(Math.random()*20)+10;
        this.maxHealth=Health;
        this.health=Health;
    }

    /* 
    * this adds to the players score
    *@param i
    *               the interget to be subtracted from the players score
     */
    public void addScore(int i) {
        this.score += i;
    }

    /* 
    * thissubtracts from the score of the player
    *@param i
     *              the interger to be subtacted from the players score
     */
    public void minusScore(int i) {
        this.score -= i;
    }
    
   /* 
    * this adds to the players health
    *@param i
    *               the interget to be subtracted from the players health
     */
    public void addHealth(int i) {
        this.health += i;
    }

    /* 
    * thissubtracts from the health of the player
    *@param i
     *              the interger to be subtacted from the players health
     */
    public void minusHealth(int i) {
        this.health -= i;
    }
    /* 
    * this method adds items that have been taken out of the game to the out of game arraylist
    *@param i
    *               The item to add
    */
public void isGone(Item i){
    this.outOfGame.add(i);
}
}
