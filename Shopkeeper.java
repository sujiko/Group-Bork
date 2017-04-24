/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

/**
 * this class holds a shopkeeper if there is one for the bork game.
 *
 * @author qureshi225
 */
public class Shopkeeper extends Monster {

    private Hashtable<Item, String> toSellMessages = new Hashtable<Item, String>();
    private ArrayList<Item> toSell = new ArrayList<Item>();
    public static String monName;
    private int life = 9001;
    private int attkPWR;
    boolean hostile = false;
    private static Shopkeeper onlyInstance;
    private ArrayList<Item> lootItems = new ArrayList<Item>();
    private Room currentRoom;
    private ArrayList<Item> selling = new ArrayList<Item>();
    /**
     *
     * @param name
     */
    Shopkeeper(String name) {
        super(name);
        this.monName = name;
    }

    public static synchronized Shopkeeper Instance() {
        if (onlyInstance == null) {
            onlyInstance = new Shopkeeper(monName);
        }
        return onlyInstance;
    }

    /**
     * this method removes items from the shopkeeper inventory
     *
     * @param i this is the item that you are adding to the inventory
     */
    public void removeItem(Item i) {
        toSell.remove(i);
        toSellMessages.remove(i);
    }

    /**
     * this method will add items to the shopkeeper inventory
     *
     * @param i the item to be added
     */
    public void addItem(Item i, String m) {
        toSellMessages.put(i, m);
        toSell.add(i);
    }
    
    public String getName(){
        return this.monName;
    }

    /**
     * this method will givey ou the price of the item you ask the shopkeeper
     * for
     *
     * @param name name of the item you want the price of
     * @return the price of the item
     */
    public int price(Item i) {
        int price = i.getScore();
        return price;
    }

    public void setRoom(String room) {
        this.currentRoom = GameState.Instance().getDungeon().getRoom(room);
    }

    public Room getRoom() {
        return this.currentRoom;
    }

    /**
     * this method will tell you everything the shopkeeper is currently selling.
     *
     * @param currentInventorySeed the interger seed of for the currently
     * displayed inventory.
     */
    public void getSelling(int currentInventorySeed) {
        Random rng = new Random(currentInventorySeed);
        int itemOne = rng.nextInt(toSell.size());
        addToSelling(toSell.get(itemOne));
        int itemTwo = rng.nextInt(toSell.size());
        addToSelling(toSell.get(itemTwo));
        int itemThree = rng.nextInt(toSell.size());
        addToSelling(toSell.get(itemThree));
        int itemFour = rng.nextInt(toSell.size());
        addToSelling(toSell.get(itemFour));
    }

    public ArrayList getInventory() {
        return this.toSell;
    }

    public void addToSelling(Item i) {
        this.selling.add(i);
    }

    public void resetSelling() {
        this.selling.clear();
    }

    public void printSelling() {
        System.out.println("I'm selling: ");
        System.out.println(selling.get(0).getPrimaryName() + ": " + toSellMessages.get(selling.get(0)));
        System.out.println(selling.get(1).getPrimaryName() + ": " + toSellMessages.get(selling.get(1)));
        System.out.println(selling.get(2).getPrimaryName() + ": " + toSellMessages.get(selling.get(2)));
        System.out.println(selling.get(3).getPrimaryName() + ": " + toSellMessages.get(selling.get(3)));
    }
}
