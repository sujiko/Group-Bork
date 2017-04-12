/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group.bork;

/**
 *the buy command will allow a player to buy a shopkeepers inventory
 * @author qureshi225
 */
public class BuyCommand extends Command {
    String itemName;
    public BuyCommand(String itemName){
        this.itemName=itemName;
    }
    /**
    * this will execute the command interaction between a shopkeeper and player
    *@return the string output to the player.
    */
    public String execute(){
       return "";
     }
}
