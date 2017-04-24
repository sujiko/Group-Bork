/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The Class Dungeon creates an instance of a dungeon for gamestate, but also
 * handles the creation of a dungeon from a .bork or .save file.
 *
 * @author qureshi225
 */
public class Dungeon {

    private String name;
    private Hashtable<String, Room> rooms = new Hashtable<String, Room>();
    private Hashtable<String, Item> items = new Hashtable<String, Item>();
    ArrayList<String> itemCommands = new ArrayList<>();
    private Room entry;
    private String fileName;

    public Dungeon(String fileName, boolean initState) {
        BufferedReader buffer = null;
        try {
            this.fileName = fileName;
            FileReader fileReader = new FileReader(fileName);
            buffer = new BufferedReader(fileReader);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(54);
        }
        try {
            String currentLine = buffer.readLine();
            while (!currentLine.equals("===")) {
                this.name = currentLine;
                buffer.readLine();
                currentLine = buffer.readLine();
            }
            buffer.readLine();
        } catch (Exception e) {
            System.out.println("this file is not formatted correctly!");
            System.exit(54);
        }
        try {
            String currentLine = "";
            buffer.mark(1);
            while (!currentLine.equals("===")) {
                buffer.reset();
                Item itemToAdd = new Item(buffer);
                items.put(itemToAdd.getPrimaryName(), itemToAdd);
                itemCommands.addAll(itemToAdd.getKeys());
                currentLine = buffer.readLine();

            }
        } catch (Exception e) {

        }
        try {
            buffer.readLine();
            this.entry = new Room(buffer, this, initState);
            this.add(entry);
        } catch (Exception e) {
        }
        try {
            String currentLine = "";
            while (!currentLine.equals("===")) {
                Room roomToAdd = new Room(buffer, this, initState);
                this.add(roomToAdd);
            }
            buffer.readLine();
        } catch (Exception e) {

        }
        try {
            String currentLine = buffer.readLine();
            while (!currentLine.equals("===")) {
                Exit toAdd = new Exit(buffer, this);
                currentLine = buffer.readLine();
            }
        } catch (Exception e) {
        }
        try {
            buffer.readLine();
        } catch (Exception e) {
            System.out.println("Warning: this file doesn't have a shop.");
        }
        try {
            String currentLine = buffer.readLine();
            Shopkeeper shop = new Shopkeeper(currentLine);
            currentLine = buffer.readLine();
            Shopkeeper.Instance().setRoom(currentLine);
            buffer.readLine();
            currentLine= buffer.readLine();
            while(!currentLine.equals("---")){
                String[] split=currentLine.split(":");
                Shopkeeper.Instance().addItem(this.getItem(split[0]), split[1]);
            }
        } catch (Exception e) {
            
        }

    }

    public Dungeon(Room entry, String name) {
        this.entry = entry;
        this.name = name;
    }

    /**
     * Gets the Entry to the dungeon.
     *
     * @return the room that is the entry
     */
    public Room getEntry() {
        return entry;
    }

    /**
     * Gets the Name of the dungeon.
     *
     * @return the String name of the dungeon
     */
    public String getName() {
        return name;
    }

    /**
     * adds a room to the dungeons hashtable so that the dungeon may use it.
     *
     * @param room the room you are adding to the hashtable.
     */
    public void add(Room room) {
        rooms.put(room.getTitle(), room);
    }

    /**
     * this gets the room attached to the String title from the hashtable.
     *
     * @param roomTitle the room to find
     * @return the room from the hashtable
     */
    public Room getRoom(String roomTitle) {
        return rooms.get(roomTitle);
    }

    /**
     * writes to the save
     *
     * @param writer the buffered writer being passed from gamestate.
     */
    public void storeState(BufferedWriter writer) {
        try {
            writer.write("Save Data\n");
            writer.write("Dungeon File: " + fileName + "\n");
            writer.write("Rooms states:\n");
            for (Room room : rooms.values()) {
                room.storeState(writer);
            }
            writer.write("===\n");
            writer.write("ShopKeeper inventory:");
            for ( int i= 0 ; i< Shopkeeper.Instance().getInventory().size(); i++){
                if (i== Shopkeeper.Instance().getInventory().size()-1){
                    writer.write(Shopkeeper.Instance().getInventory().get(i).getPrimaryName());
                    break;
                }
                writer.write(Shopkeeper.Instance().getInventory().get(i).getPrimaryName()+",");
            }
        } catch (Exception e) {

        }

    }

    /**
     * reads from the save state to properly create the game back up to the
     * proper point
     *
     * @param buffer is passed from gamestate to read the file
     *
     */
    public void restoreState(BufferedReader buffer) {
        try {

            String line = buffer.readLine();
            while (!line.equals("===")) {
                String title = line.substring(0, line.length() - 1);
                Room room = this.getRoom(title);
                room.restoreState(buffer, this);
                line = buffer.readLine();
            }
            /*
            String line= buffer.readLine();
            while(!line.equals("---")){
            System.out.println(line);
            String title= line.substring(0, line.length()-1);
            System.out.println(title);
            Room room= this.getRoom(title);
            System.out.println(room.getTitle());
            room.restoreState(buffer);
            line=buffer.readLine();
            }
             */
        } catch (Exception e) {
            System.out.println("This is not formatted correctly!");
            System.exit(54);
        }
    }

    /**
     * adds the item to the items hashtable
     *
     * @param item adds the item to the hashtable
     */
    public void addItem(Item item) {
        items.put(item.getPrimaryName(), item);
    }

    /**
     * gets the item connected to the string to the hashtable
     *
     * @param primaryName the name of the item to get
     * @return the item from the hashtable
     */
    public Item getItem(String primaryName) {
        return items.get(primaryName);
    }

    public ArrayList getRooms() {
        ArrayList<Room> roomlist = new ArrayList<>(rooms.values());
        return roomlist;
    }

    public ArrayList getItemsList() {
        ArrayList<Item> loot = new ArrayList<>(items.values());
        return loot;
    }
}
