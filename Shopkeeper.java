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
    private int attkPWR=(int) GameState.Instance().getMaxHealth()/2;
    boolean hostile = false;
    private static Shopkeeper onlyInstance;
    private ArrayList<Item> lootItems = new ArrayList<Item>();
    private Room currentRoom;
    private ArrayList<Item> selling = new ArrayList<Item>();
    private boolean dead = false;

    /**
     *
     * @param name
     */
    Shopkeeper(String name) {
        super(name);
        this.monName = "Shopkeeper";
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
        selling.remove(i);
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

    public String getName() {
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

    public void setRoom(Room room) {
        this.currentRoom = room;
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
        if (toSell.size() >= 1) {
            int itemOne = rng.nextInt(toSell.size());
            addToSelling(toSell.get(itemOne));
            if (toSell.size() >= 2) {
                int itemTwo = rng.nextInt(toSell.size());
                while (itemTwo == itemOne) {
                    itemTwo = rng.nextInt(toSell.size());
                }
                addToSelling(toSell.get(itemTwo));
                if (toSell.size() >= 3) {
                    int itemThree = rng.nextInt(toSell.size());
                    while (itemTwo == itemThree || itemThree == itemOne) {
                        itemThree = rng.nextInt(toSell.size());
                    }
                    addToSelling(toSell.get(itemThree));
                    if (toSell.size() >= 4) {
                        int itemFour = rng.nextInt(toSell.size());
                        while (itemTwo == itemFour || itemFour == itemOne || itemThree == itemFour) {
                            itemFour = rng.nextInt(toSell.size());
                        }
                        addToSelling(toSell.get(itemFour));
                    }
                }
            }
        }
    }

    public ArrayList<Item> getInventory() {
        return this.toSell;
    }
    public void addToSelling(Item i) {
        this.selling.add(i);
    }

    public void resetSelling() {
        this.selling.clear();
    }

    public String printSelling() {
        String sell = "I'm selling:\n";
        for (int i = 0; i < selling.size(); i++) {
            sell += selling.get(i).getPrimaryName() + ": " + toSellMessages.get(selling.get(i)) + " It costs "
                    + selling.get(i).getScore() + " Zennys"+ "\n";
        }
        return sell;
    }

    public boolean dead() {
        return this.dead;
    }

    public void died() {
        this.dead = true;
    }
    @Override
    public void getLoot(){
        if(!this.getInventory().isEmpty()){
            for(Item item:this.getInventory()){
            GameState.Instance().addToInventory(item);
        }}
        GameState.Instance().addZennys((int)(Math.random()*this.life));
    }
}
