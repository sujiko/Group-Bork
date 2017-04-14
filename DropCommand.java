/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 * DropCommand is an extension of the Command class and specializes in dropping an item in your inventory
 * @author qureshi225
 */
public class DropCommand extends Command {
    String itemName;
    /**
     * DropCommand takes in the itemName and assigns it to the variable itemName
     * @param itemName 
     */
    public DropCommand(String itemName){
        this.itemName=itemName;
    }
    /**
     * @return a String that tells you whether you dropped the item or if you
     * had it in your inventory
     */
    public String execute(){
        if(itemName.equals("")){
            return"Drop what?";
        }
        try{
            GameState state= GameState.Instance();
            if(state.getItemFromInventoryNamed(itemName)== null){
                return "You do not have "+itemName+" in your inventory!";
            }else{
                state.minusScore(state.getItemFromInventoryNamed(itemName).getScore());
                state.removeFrominventory(state.getItemFromInventoryNamed(itemName));
                return "Dropped "+itemName+".";
            }
        }catch(Exception e){
            return "You cannot drop that!";
        }
    }
}

