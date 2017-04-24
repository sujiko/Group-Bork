/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

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
   private String key;
   private boolean locked;
   
   /**
    * exit constructor from file
    * add whether or not an exit is locked and what the unlock condition is
    * @param buffer 
    * @param dungeon
    */
   public Exit(BufferedReader buffer, Dungeon dungeon){
        try{
        String currentLine="";
        if(!currentLine.equals("===")){
            String starting= buffer.readLine();
            String direction= buffer.readLine();
            String destination=buffer.readLine();
            this.locked = false;
            if(!currentLine.equals("---")){
                String keyItem = buffer.readLine();
                //this.locked = true;
                //this.key = keyItem;
            }
            this.src= dungeon.getRoom(starting);
            this.dest=dungeon.getRoom(destination);
            this.dir=direction;
            this.src.addExit(this);
            this.setDescription("You can go "+this.getDir()+ " to "+this.getDest().getTitle());
        }
   }catch (Exception e){
       
   }
   }
   
   /**
    * exit constructor from given values
    * @param dir 
    * @param src
    * @param dest
    */
   public Exit(String dir, Room src, Room dest){
    this.dir=dir;
    this.src=src;
    this.dest=dest;
   }
   /**
   * sets the descriptor for an exit 
   *@param desc
   *          String that is what the user wants to input as the description for the exit. 
   */
   public void setDescription(String desc){
    this.description=desc;
   }
   /**
   *gets the description that was set for an exit
   *@return the string desccription
   */
   public String getDescription(){
       return description;
   }
   /**
   *gets the direction the exit is in
   *@return the string of the direction
   */
   public String getDir(){
       return dir;
   }
   /**
   *gets the starting room
   *@return the room you started in
   */
   public Room getSrc(){
       return src;
   }
   /** 
   *gets the destination room
   *@return the room the exit leads to
   */
   public Room getDest(){
       return dest;
   }
   /**
    * returns whether or not an exit can be passed through
    * @return lock status
    */
   public boolean getLockState()
   {
       return locked;
   }
   
   /**
    * unlocks exit
    * @param keyItem
    */
   public void unlock(String keyItem)
   {
      if(this.key.equals(keyItem))
      {
         this.locked = false;
         System.out.println("The door unlocked!");
      }
      else
      {
         System.out.println("That didn't seem do do anything...");
      }
   }
}
