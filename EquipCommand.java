/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

/**
 *Euip equips an item to the player, for use in combat.
 * @author qureshi225
 */
public class EquipCommand extends Command {
    String itemName;

    public EquipCommand(String itemName){
        this.itemName=itemName;
    }
    /**
    *runs the equip commandString
    *@return the string the command prints
    */
    public String execute(){
        if(itemName.equals("")){
            return "Equip what?";
        }
        try{
        GameState  state= GameState.Instance();
        if(state.getItemFromInventoryNamed(itemName)!= null){
            return "You equipped the "+itemName;
        }/*else if(state.getItemInVicinityNamed(itemName)!= null){
        state.addToInventory(state.getItemInVicinityNamed(itemName));
        return itemName+" taken.";
        }else{
            return "There is no "+itemName+" here.";
        }
        */
        }catch (Exception e){
        return "You cannot take that!";
        }
    }
}
