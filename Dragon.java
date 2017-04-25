/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

import java.util.ArrayList;

/**
 *  Initializes singleton "Dragon" which extends the class monster
 * 
 * @author Elliot
 */
public class Dragon extends Monster{
    private static Shopkeeper onlyInstance;
    public static String monName;
    private final int life = 1;
    private final int attkPWR=0;
    private Room currentRoom;
    private boolean dead = false;
    private ArrayList<Item> lootItems = new ArrayList<Item>();
    Dragon(String name) {
        super(name);
        this.monName = "chibiUsa";
    }

    public static synchronized Shopkeeper Instance() {
        if (onlyInstance == null) {
            onlyInstance = new Shopkeeper(monName);
        }
        return onlyInstance;
    }
    
    public void setRoom(Room room) {
        this.currentRoom = room;
    }
    @Override
    public void genLoot(){
    }
    @Override
    public void getLoot(){
        died();
    }
    public Room getRoom() {
        return this.currentRoom;
    }
    public boolean dead() {
        return this.dead;
    }

    public void died() {
        this.dead = true;
        DieEvent a = new DieEvent();
        a.execute();
        System.out.println("You killed the dragon. you were supposed to save it. you lose");
    }
}
