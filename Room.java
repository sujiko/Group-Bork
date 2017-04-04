/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;

/**
 *
 * @author qureshi225
 */
public class Room {

    private String title;
    private String desc = "";
    private boolean beenHere;
    private ArrayList<Exit> exitsTo = new ArrayList<Exit>();
    private ArrayList<Item> containingItems = new ArrayList<Item>();

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

    public Room(String title) {
        this.title = title;
        beenHere = false;
    }

    public String getTitle() {
        return title;
    }

    public void setDesc(String desc) {
        this.desc += desc;
    }

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

    public void addExit(Exit exit) {
        exitsTo.add(exit);

    }

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
                writer.write("beenHere= false\n");

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

    public void add(Item i) {
        containingItems.add(i);
    }

    public void remove(Item i) {
        containingItems.remove(i);
    }

    public Item getItemNamed(String n) {
        for (Item i : containingItems) {
            if (i.getPrimaryName().equals(n)) {
                return i;
            }
        }
        return null;
    }

}
