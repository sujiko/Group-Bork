/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

import java.util.Scanner;

/**
 *
 * @author qureshi225
 */
public class SaveCommand extends Command {
    String saveFilename;
    
    public SaveCommand(String f){
        saveFilename=f;
    }
    public String execute() {
        GameState state = GameState.Instance();
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to name your save? This will be stored as a .sav file.");
        String saveName = in.nextLine();
        state.store(saveName);
        return "You have Saved your game.";
    }
    
}
 