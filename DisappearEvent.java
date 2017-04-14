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
public class DisappearEvent extends Event {
    private Item item;
    public DisappearEvent(Item item){
        this.item=item;
    }

    void execute() {
        GameState.Instance().isGone(item);
        if(GameState.Instance().getInventoryNames().contains(item.getPrimaryName())){
            GameState.Instance().removeFrominventory(item);
        }else if(GameState.Instance().getAdvenurersCurrentRoom().contains(item)){
            GameState.Instance().getAdvenurersCurrentRoom().remove(item);
        }
    }
}
