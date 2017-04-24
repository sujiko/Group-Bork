/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 * allows the user to heal themselves 25% of their hp
 * @author Chris
 */
public class HealCommand extends Command{
    private int maxMana;
    private int mana;
    public HealCommand(){
        this.maxMana = GameState.Instance().getMaxMana();
        this.mana= GameState.Instance().getMana();
        
    }

    @Override
    String execute() {
        int needed =(int) (this.maxMana *.25);
        if(this.mana> needed){
            GameState.Instance().useMana(needed);
            GameState.Instance().addHealth(needed);
            return "you healed yourself for " + needed+ " point.";
        }else{
            return "you do not have enough mana to heal yourself.";
        }
        
    }
    
}
