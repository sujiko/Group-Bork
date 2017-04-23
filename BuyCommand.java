/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *the buy command will allow a player to buy a shopkeepers inventory
 * @author qureshi225
 */
public class BuyCommand extends Command {
    String itemName;
    Item i;
    public BuyCommand(String itemName){
        GameState in= GameState.Instance();
        this.itemName=itemName;
        i=in.getDungeon().getItem(itemName);
    }
    /**
    * this will execute the command interaction between a shopkeeper and player
    *@return the string output to the player.
    */
    public String execute(){
        GameState in=GameState.Instance();
        if (in.getZennys()>= Shopkeeper.Instance().price(i)){
            Shopkeeper.Instance().removeItem(i);
            in.addToInventory(i);
            return "Thanks for buying the "+ itemName;
        }else{
           return "You don't have enough Zennys friendo!";
        }
     }
}
