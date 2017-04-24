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
public class DieEvent extends Event{
    
    public void execute(){
        GameState state = GameState.Instance();
        state.running=false;
    }
    
}
