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
public class MovementCommand extends Command {
    String dir;

    public MovementCommand(String dir) {
        this.dir = dir;
    }

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
