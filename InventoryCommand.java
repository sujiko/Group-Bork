/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

/**
 *
 * @author qureshi225
 */
public class InventoryCommand extends Command {

    public InventoryCommand() {

    }

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
