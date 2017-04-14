/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *takes user input and helps parse and output it.
 * @author qureshi225
 */
import java.util.Scanner;
import static java.lang.System.out;

public class Interpreter {

    /**
     * the main takes care of the messages outputted to the user and taking in input from the user.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("what is the filename of the dungeon you want to play? or what is the save you want to load?");
        GameState state= GameState.Instance();
        String fileName= input.nextLine();
        if(fileName.endsWith(".sav")){
            state.restore(fileName);
        }else if(fileName.endsWith(".bork")){
         Dungeon thisDungeon = new Dungeon(fileName,true);
         state.initialize(thisDungeon);
        }
            



        System.out.println("Welcome to " + state.getDungeon().getName());
        System.out.println(state.getAdvenurersCurrentRoom().getTitle());
        System.out.println(state.getAdvenurersCurrentRoom().describe());
        while (state.running) {
            String whatTheyGave = promptUser(input);
            if (whatTheyGave.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing!");
                state.running=false;
            }else{
            CommandFactory in = CommandFactory.getInstance();
            Command toGo=in.parse(whatTheyGave);
            System.out.println(toGo.execute());
            }
        }
            
    }
/**
*this method is what prompts the user for their input and then returns it to main.
*@param input
*           the method takes in a scanner so that it may communicate with the user
*@return the string the user inputted for parsing.
*/
    private static String promptUser(Scanner input) {
        boolean haveTheyQuit = false;
        while (!haveTheyQuit) {
            System.out.println("What do you want to do?");
            String given= input.nextLine();
            if ( given.toLowerCase().equals("q") || given.toLowerCase().equals("quit")){
                return "quit";
            }
            return given;

        }
        return "";
    }

}
