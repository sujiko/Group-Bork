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
public class DropCommand extends Command {
    String itemName;
    public DropCommand(String itemName){
        this.itemName=itemName;
    }
    public String execute(){
        if(itemName.equals("")){
            return"Drop what?";
        }
        try{
            GameState state= GameState.Instance();
            if(state.getItemFromInventoryNamed(itemName)== null){
                return "You do not have "+itemName+" in your inventory!";
            }else{
                state.removeFrominventory(state.getItemFromInventoryNamed(itemName));
                return "Dropped "+itemName+".";
            }
        }catch(Exception e){
            return "You cannot drop that!";
        }
    }
}

