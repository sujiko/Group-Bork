/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

/**
 * TakeCommand is an Extension of the Command class and specializes on adding items to your inventory
 * @author qureshi225
 */
public class TakeCommand extends Command {
    String itemName;
    /**
     * TakeCommand takes the name of the itemName and sets it in the variable itemName
     * @param itemName 
     */
    public TakeCommand(String itemName){
        this.itemName=itemName;   
    }
    /**
     * @return a string that tells you if you have taken the item or
     * if it isn't in the room
     */
    public String execute(){
        if(itemName.equals("")){
            return "Take what?";
        }
        try{
        GameState  state= GameState.Instance();
        if(state.getItemFromInventoryNamed(itemName)!= null){
            return "You already have the "+itemName;
        }else if(state.getItemInVicinityNamed(itemName)!= null){
        state.addToInventory(state.getItemInVicinityNamed(itemName));
        return itemName+" taken.";
        }else{
            return "There is no "+itemName+" here.";
        }
        }catch (Exception e){
        return "You cannot take that!";
        }
    }
}
