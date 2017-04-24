/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 * this class creates a command factory object relative to the command that is put in
 * @author qureshi225
 */
public class CommandFactory {

    private static CommandFactory onlyInstance;

    private CommandFactory() {

    }
    /**
     * @return creates an instance of the command factory if one hasn't been created yet
     */
    public static synchronized CommandFactory getInstance() {
        if (onlyInstance == null) {
            onlyInstance = new CommandFactory();
        }
        return onlyInstance;
    }
    /**
     * 
     * @param commandString
     * @return prints a string to the screen that tells what you did or where you went
     * based off of the command that you input to the system.
     */
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
            case "health":
            case "h":
                toDo = new HealthCommand();
                break;
            case "score":
                toDo = new ScoreCommand();
                break;
            case "buy":
                toDo= new BuyCommand(actedUpon);
                break;
            case "price":
                toDo= new PriceCommand(actedUpon);
                break;
            case "attack":
                toDo = new AttackCommand();
                break;
            case "heal":case "Heal":
                toDo = new HealCommand();
                break;
            case "Teleport":case"teleport":
                toDo = new TeleportCommand();
                break;
            case "verbose":case"Verbose":
                toDo = new VerboseCommand();
                break;
            default:
                if(!actedUpon.equals("")){
                toDo= new ItemSpecificCommand(actionCommand,actedUpon);   
                }else{
                    toDo = new UnknownCommand(commandString);
                }
        }

        return toDo;
    }
}
