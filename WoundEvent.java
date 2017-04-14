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
public class WoundEvent extends Event {
    
    private String actedUpon;
    private Item item;
    public WoundEvent(String actedUpon, Item item){
        this.actedUpon=actedUpon;
        this.item = item;
        
    }
    @Override
    void execute() {
    }
    
}
