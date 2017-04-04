/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

import borkv3.Room;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author qureshi225
 */
public class Dungeon {

    private String name;
    private Hashtable<String, Room> rooms = new Hashtable<String, Room>();
    private Hashtable<String,Item> items= new Hashtable<String,Item>();
    ArrayList<String> itemCommands= new ArrayList<>();
    private Room entry;
    private String fileName;

    public Dungeon(String fileName, boolean initState) {
        BufferedReader buffer = null;
        try {
            this.fileName=fileName;
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
        try{
            String currentLine="";
            buffer.mark(1);
            while(!currentLine.equals("===")){
                buffer.reset();
                Item itemToAdd= new Item(buffer);
                items.put(itemToAdd.getPrimaryName(),itemToAdd);
                itemCommands.addAll(itemToAdd.getKeys());
                currentLine=buffer.readLine();

            }
        } catch (Exception e){
            
        }
        try {
            buffer.readLine();
            this.entry = new Room(buffer,this,initState);
            this.add(entry);
        } catch (Exception e) {
        }
        try {
            String currentLine=""; 
            while (!currentLine.equals("===")) {
                Room roomToAdd = new Room(buffer,this,initState);
                this.add(roomToAdd);
            }
            buffer.readLine();
        } catch (Exception e) {

        }
        try{
            String currentLine = buffer.readLine();
            while (!currentLine.equals("===")) {
                Exit toAdd= new Exit(buffer,this);
                currentLine=buffer.readLine();
            }
        }catch(Exception e){
    }
    }

    public Dungeon(Room entry, String name) {
        this.entry = entry;
        this.name = name;
    }

    public Room getEntry() {
        return entry;
    }

    public String getName() {
        return name;
    }

    public void add(Room room) {
        rooms.put(room.getTitle(), room);
    }

    public Room getRoom(String roomTitle) {
        return rooms.get(roomTitle);
    }
    public void storeState(BufferedWriter writer){
        try{
        writer.write("Save Data\n");
        writer.write("Dungeon File: "+ fileName+"\n");
        writer.write("Rooms states:\n");
        for(Room room: rooms.values()){
            room.storeState(writer);
        }
        writer.write("===\n");
        }catch(Exception e){
            
        }

    }
    public void restoreState(BufferedReader buffer){
        try{
            
            String line= buffer.readLine();
            while(!line.equals("===")){
                String title= line.substring(0,line.length()-1);
                Room room=this.getRoom(title);
                room.restoreState(buffer,this);
                line=buffer.readLine();
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
        }catch(Exception e){
            System.out.println("This is not formatted correctly!");
            System.exit(54);
        }
    }
    public void addItem(Item item){
        items.put(item.getPrimaryName(),item);
    }
    public Item getItem(String primaryName){
        return items.get(primaryName);
    }


}
