/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

import java.util.ArrayList;

/**
 *
 * @author christian
 */
public class TeleportEvent extends Event{

    public void TeleportEvent(){
        
    }
    
    
    @Override
    void execute() {
        ArrayList<Room> rooms = GameState.Instance().getDungeon().getRooms();
        int limit = rooms.size();
        int next = (int)(Math.random() * limit);
        Room room = rooms.get(next);
        GameState.Instance().setAdventurersCurrentRoom(room);
        System.out.println(GameState.Instance().getAdvenurersCurrentRoom().getTitle() +"\n");
        System.out.println(GameState.Instance().getAdvenurersCurrentRoom().describe()+ "\n");
        
    }
    
    
    
}
