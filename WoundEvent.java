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
    
    private String number;
    private Item item;
    public WoundEvent(String actedUpon, Item item){
        this.number=actedUpon;
        this.item = item;
        
    }
    @Override
    void execute() {
        GameState state=GameState.Instance();
            if(number.startsWith("-")){
                int negativeNumber=Integer.valueOf(number.substring(1, number.length()));
                state.addHealth(negativeNumber);
            }else{
                int positiveNumber=Integer.valueOf(number);
                state.minusHealth(positiveNumber);
            }
    }
    
}
