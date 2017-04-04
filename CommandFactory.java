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
public class CommandFactory {

    private static CommandFactory onlyInstance;

    private CommandFactory() {

    }

    public static synchronized CommandFactory getInstance() {
        if (onlyInstance == null) {
            onlyInstance = new CommandFactory();
        }
        return onlyInstance;
    }

    public Command parse(String commandString) {
        Command toDo;
        String[] split= commandString.split(" ");
        String actionCommand=(split[0].toLowerCase());
        String actedUpon="";
        if(split.length==2){
        actedUpon= (split[1]);
        }
        switch (actionCommand) {
            case "u":
            case "up":
            case "d":
            case "down":
            case "w":
            case "west":
            case "e":
            case "east":
            case "s":
            case "south":
            case "n":
            case "north":
                toDo = new MovementCommand(actionCommand);
                break;
            case "save":
                toDo = new SaveCommand(actionCommand);
                break;
            case "take":
                toDo= new TakeCommand(actedUpon);
                break;
            case "drop":
                toDo= new DropCommand(actedUpon);
                break;
            case "inventory":
            case "i":
                toDo= new InventoryCommand();
                break;
            default:
                if(actedUpon!=""){
                toDo= new ItemSpecificCommand(actionCommand,actedUpon);   
                }else{
                    toDo = new UnknownCommand(commandString);
                }
        }

        return toDo;
    }
}
