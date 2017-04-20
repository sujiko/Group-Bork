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
public class Shopkeeper extends Monster{

    private ArrayList<Item> toSell = new ArrayList<Item>();
    private String monName;
    private int life = 9001;
    private int attkPWR;
    boolean hostile = false;
    private ArrayList<Item> lootItems = new ArrayList<Item>();
    /**
     *
     * @param name
     */
    public Shopkeeper(String name){
        super(name);
        this.monName=name;
    }

    /**
     * this method removes items from the shopkeeper inventory
     *
     * @param i this is the item that you are adding to the inventory
     */
    public void removeItem(Item i) {
        toSell.remove(i);
    }

    /**
     * this method will add items to the shopkeeper inventory
     *
     * @param i the item to be added
     */
    public void addItem(Item i) {
        toSell.add(i);
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

    /**
     * this method will tell you everything the shopkeeper is currently selling.
     *
     * @param currentInventorySeed the interger seed of for the currently
     * displayed inventory.
     */
    public void printSelling(int currentInventorySeed) {
        Random rng = new Random(currentInventorySeed);
        int itemOne = rng.nextInt(toSell.size());
        int itemTwo = rng.nextInt(toSell.size());
        int itemThree = rng.nextInt(toSell.size());
        int itemFour = rng.nextInt(toSell.size());
        System.out.println("I'm selling: " + toSell.get(itemOne).getPrimaryName() + ", " + toSell.get(itemTwo).getPrimaryName() + ", "
                + toSell.get(itemThree).getPrimaryName() + ", " + toSell.get(itemFour).getPrimaryName() + ".");

    }
        /**
     * tells you the monsters name
     * @return monsters name
     */
    public String getMonName(){
        return this.monName;
    }
    /**
     * tells you the monsters attack power
     * @return monsters attack power
     */
    public int getAttack(){
        return this.attkPWR;
    }
    /**
     * this will be used to scale the monsters attack according to the users level
     */
    @Override
    public void setAttack(){
        this.attkPWR = GameState.Instance().getStrength() /4;
    }
    /**
     * this will return the monsters life
     * @return monsters amount of health
     */
    public int getLife(){
        return this.life;
    }
    /**
     * allows monster to take damage
     * @param dmg 
     */
    public void takeDMG(int dmg){
        this.life -=dmg;
    }
    /**
     * this will be used to scale the monsters health according to the users level
     */
    @Override
    public void setLife(){
        this.life=9001; 
    }
    /**
     * this will be used to get the loot the monster holds when it is killed
     * an arraylist of loot that will be dropped when the monsters health hits 0
     */
    public void getLoot(){
        if (lootItems.isEmpty()){
        }else
            for(Item item:lootItems){
            GameState.Instance().addToInventory(item);
        }
        
    }
    
    
    @Override
    public void genLoot(){
        ArrayList<Item> possibleLoot =GameState.Instance().getDungeon().getItemsList();
        int gen = (int)(Math.random()*3)+1;
        for(int x = 1; x<gen; x++){
            int lootItem = (int)(Math.random()* possibleLoot.size()-1);
            this.lootItems.add(possibleLoot.get(lootItem));
        }
    }
    /**
     * this will be used to manually set the monsters hostility to true.
     */
    public void setHostileT(){
        this.hostile=true;
    }
    public boolean getHostility(){
        return this.hostile;
    }
    
}
