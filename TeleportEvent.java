/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

import java.util.Hashtable;
import java.util.Set;

/**
 *
 * @author christian
 */
public class TeleportEvent extends Event{

    public void TeleportEvent(){
        
    }
    
    
    @Override
    void execute() {
        Hashtable rooms = GameState.Instance().getDungeon().getRooms();
        Set keys = rooms.keySet();
        int limit = keys.size();
        int next = (int)(Math.random() * limit);
    }
    
    
    
}
