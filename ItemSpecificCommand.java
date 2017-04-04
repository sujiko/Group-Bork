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
public class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;

    public ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }

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
