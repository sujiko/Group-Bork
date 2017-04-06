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
    /**
     * assigns the name  of the file to the variable saveFilename
     * @param f 
     */
    public SaveCommand(String f){
        saveFilename=f;
    }
    /**
     * when run this command saves the game to the file named in the variable saveFilename.
     * @return returns a message letting you know your game has been saved
     */
    public String execute() {
        GameState state = GameState.Instance();
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to name your save? This will be stored as a .sav file.");
        String saveName = in.nextLine();
        state.store(saveName);
        return "You have Saved your game.";
    }
    
}
 