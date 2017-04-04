/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import static java.lang.System.out;
import java.util.ArrayList;

/**
 *
 * @author qureshi225
 */
public class GameState {

    private static GameState onlyInstance;
    private Room currentRoom;
    private Dungeon currentDungeon;
    private boolean initState = true;
    private ArrayList<Item> inventory = new ArrayList<Item>();

    private GameState() {
    }

    public static synchronized GameState Instance() {
        if (onlyInstance == null) {
            onlyInstance = new GameState();
        }
        return onlyInstance;
    }

    public void initialize(Dungeon dungeon) {
        currentDungeon = dungeon;
        currentRoom = currentDungeon.getEntry();
    }

    public Room getAdvenurersCurrentRoom() {
        return currentRoom;
    }

    public void setAdventurersCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public Dungeon getDungeon() {
        return currentDungeon;
    }

    public ArrayList<String> getInventoryNames() {
        ArrayList<String> toReturn = new ArrayList<String>();
        for (Item i : inventory) {
            toReturn.add(i.getPrimaryName());
        }
        return toReturn;
    }

    public void addToInventory(Item i) {
        inventory.add(i);
        this.currentRoom.remove(i);
    }

    public void removeFrominventory(Item i) {
        inventory.remove(i);
        this.currentRoom.add(i);
    }

    public Item getItemInVicinityNamed(String name) {
        if (this.getItemFromInventoryNamed(name) != null) {
            return this.getItemFromInventoryNamed(name);
        }
        return this.currentRoom.getItemNamed(name);
    }

    public Item getItemFromInventoryNamed(String name) {
        for (Item i : inventory) {
            if (i.getPrimaryName().equals(name)) {
                return i;
            }
        }
        return null;
    }

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
            writer.close();
        } catch (Exception e) {

        }

    }

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
            String[] split= buffer.readLine().split(":");
            String line=split[1].trim();
            initState = false;
            currentDungeon = new Dungeon(line, initState);
            line = buffer.readLine();
            //while(!line.equals("===")){
            currentDungeon.restoreState(buffer);
            buffer.readLine();
            line=buffer.readLine();
            split= line.split(":");
            line=split[1].trim();
            currentRoom = currentDungeon.getRoom(line);
            line=buffer.readLine();
            split=line.split(":");
            line=split[1].trim();
            String[] loadItems =line.split(",");
            for (String i : loadItems) {
                inventory.add(currentDungeon.getItem(i));
            }

        } catch (Exception e) {

        }

    }

}
