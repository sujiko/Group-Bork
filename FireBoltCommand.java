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
public class FireBoltCommand extends Command{
    private int mana;
    private int maxMana;
    
    
    public FireBoltCommand(){
        this.mana=GameState.Instance().getMana();
        this.maxMana=GameState.Instance().getMaxMana();
                
    }

    @Override
    String execute() {
        int needed = (int)(this.maxMana*.33);
        if(this.mana >needed){
            GameState.Instance().useMana(needed);
            Combat a = new Combat();
            String resp =a.magicVmon();
            return resp;
        }else{
            return "You do not have enough mana to cast Firebolt.";
        }
    }
}
