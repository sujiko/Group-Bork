/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *the price command will allow a player to see the price of an item in  a shopkeepers inventory
 * @author qureshi225
 */
public class PriceCommand extends Command {
    String itemName;
    public PriceCommand(String itemName){
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
