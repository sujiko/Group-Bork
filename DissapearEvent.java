/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *
 * @author christian
 */
public class DissapearEvent extends Event {
    private Item item;
    public DissapearEvent(Item item){
        this.item=item;
    }
    @Override
    void execute() {
        if(GameState.Instance().getInventoryNames().contains(item)){
            GameState.Instance().removeFrominventory(item);
        }else if(GameState.Instance().getAdvenurersCurrentRoom().contains(item)){
            GameState.Instance().getAdvenurersCurrentRoom().remove(item);
        }
    }
}
