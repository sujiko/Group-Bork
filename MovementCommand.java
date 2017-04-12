/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group.bork;

/**
 * this class allows you to move to different rooms if possible
 * @author qureshi225
 */
public class MovementCommand extends Command {
    String dir;
    /**
     * assigns the direction you want to go to the variable dir
     * @param dir 
     */
    public MovementCommand(String dir) {
        this.dir = dir;
    }
    /**
     * @return returns a message telling you what room you are in if you could move in that direction
     */
    public String execute() {
        GameState state = GameState.Instance();
        Room current = state.getAdvenurersCurrentRoom();
        Room dest = current.leaveBy(dir);
        if (dest != null) {
            state.setAdventurersCurrentRoom(dest);
            return state.getAdvenurersCurrentRoom().describe();
        }
        return "You can't go that Way!";
    }
}
