/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group.bork;

/**
 * runs commands specific to items
 * @author qureshi225
 */
public class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
    /**
     * assigns verb and noun to their specific variables
     * @param verb
     * @param noun 
     */
    public ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }
    /**
     * checks if you have the noun or if it is in the same room as you and then checks if you
     * can verb the noun
     * @return message telling you if you did something with the noun or not
     */
    public String execute() {
        GameState state = GameState.Instance();
        if (state.getDungeon().itemCommands.contains(verb)) {
            if(noun.equals("")){
                return verb+" what?";
            }
            Item item = state.getItemInVicinityNamed(noun);
            if (item != null) {
                String toReturn = item.getMessageForVerb(verb);
                if (toReturn != null) {
                    return toReturn;
                }
                return "You can't " + verb + " the " + noun + ".";
            }
            return "There is no " + noun + ".";
        }else{
            return new UnknownCommand(verb).execute();
        }
    }
}
