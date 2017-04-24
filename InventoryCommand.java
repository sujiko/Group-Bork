/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 * runs Commands to check your inventory
 * @author qureshi225
 */
public class InventoryCommand extends Command {
    /**
     * just a constructor to create an inventory command
     */
    public InventoryCommand() {

    }
    /**
     * runs to check if you are carrying anything and if you are, what the items are.
     * @return returns a message telling you what items you have if you have any. Idf you don't it will tell you that
     */
    public String execute() {
        GameState state = GameState.Instance();
        if (state.getInventoryNames().isEmpty()) {
            return "You are not carrying anything!";
        } else {
            String itemsInInventory = "";
            for (String i : state.getInventoryNames()) {
                itemsInInventory += i+" ";
            }
            return"You are carrying: "+ itemsInInventory;
        }
    }
}
