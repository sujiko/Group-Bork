/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 * the price command will allow a player to see the price of an item in a
 * shopkeepers inventory
 *
 * @author qureshi225
 */
public class PriceCommand extends Command {

    String itemName;
    Item item;

    public PriceCommand(String itemName) {
        this.itemName = itemName;

    }

    /**
     * this will execute the command interaction between a shopkeeper and player
     *
     * @return the string output to the player.
     */
    public String execute() {
        Shopkeeper shop = Shopkeeper.Instance();
        GameState in = GameState.Instance();
        if (in.getAdvenurersCurrentRoom() == shop.getRoom()) {
            if (!itemName.equals("")) {
                item = GameState.Instance().getDungeon().getItem(itemName);
                if(shop.getInventory().contains(item)){
                    String price;
                    price= Integer.toString(shop.price(item));
                    return "The "+ itemName+ " is "+ price +" Zennys.";  
                }else{
                  return "The shop does not carry "+ itemName; 
                }
            } else {
                return "Price of what?";
            }
        } else {
            return "You are not in the same room as the shop!!!";
        }
    }
}
