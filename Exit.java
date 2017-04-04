/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

import java.io.BufferedReader;

/**
 *
 * @author qureshi225
 */
public class Exit {
   private String dir;
   private Room src;
   private Room dest;
   private String description;
   public Exit(BufferedReader buffer, Dungeon dungeon){
        try{
        String currentLine="";
        if(!currentLine.equals("===")){
            String starting= buffer.readLine();
            String direction= buffer.readLine();
            String destination=buffer.readLine();
            this.src= dungeon.getRoom(starting);
            this.dest=dungeon.getRoom(destination);
            this.dir=direction;
            this.src.addExit(this);
            this.setDescription("You can go "+this.getDir()+ " to "+this.getDest().getTitle());
        }
   }catch (Exception e){
       
   }
   }
   public Exit(String dir, Room src, Room dest){
    this.dir=dir;
    this.src=src;
    this.dest=dest;
   }
   public void setDescription(String desc){
    this.description=desc;
   }
   public String getDescription(){
       return description;
   }
   public String getDir(){
       return dir;
   }
   public Room getSrc(){
       return src;
   }
   public Room getDest(){
       return dest;
   }
}
