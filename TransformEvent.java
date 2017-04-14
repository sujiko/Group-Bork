/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *
 * @author Qures
 */
public class TransformEvent extends Event {
    private Item item;
    private String actedUpon;
    
    public TransformEvent(String actedUpon, Item i){
        this.item=i;
        this.actedUpon= actedUpon;
    }
    
    public void execute(){
        GameState in= GameState.Instance();

           if(in.getInventoryNames().contains(item.getPrimaryName())){
            in.minusScore(in.getItemFromInventoryNamed(actedUpon).getScore());
            in.removeFrominventory(item);
            in.addToInventory(in.getDungeon().getItem(actedUpon)); 
            in.addScore(in.getItemFromInventoryNamed(actedUpon).getScore());
        }else if(in.getAdvenurersCurrentRoom().contains(item)){
            in.getAdvenurersCurrentRoom().remove(item);
            in.getAdvenurersCurrentRoom().add(in.getDungeon().getItem(actedUpon));
        }
     
    }
}
