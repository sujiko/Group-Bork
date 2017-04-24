/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *
 * @author Chris
 */
public class TeleportCommand extends Command {
    private int mana;
    private int maxMana;
    public TeleportCommand(){
        this.mana=GameState.Instance().getMana();    
        this.maxMana = GameState.Instance().getMaxMana();
    }

    @Override
    String execute() {
        if(this.mana == this.maxMana){
            GameState.Instance().useMana(this.maxMana);
            TeleportEvent a = new TeleportEvent();
            a.execute();
            return "";
        }else{
            return "You do not have enough mana to cast a teleport spell";
        }
    }
    
}
