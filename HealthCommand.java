/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *
 * @author christian
 */
public class HealthCommand extends Command{
    private int maxHealth;
    private int currentHealth;
    public HealthCommand(){
        this.maxHealth = GameState.Instance().getMaxHealth();
        this.currentHealth = GameState.Instance().getHealth();
    }
    @Override
    String execute() {
        if((currentHealth<maxHealth)&&(currentHealth>maxHealth*.80)){
            return "tis but a scratch";
        }else if((currentHealth<maxHealth)&&(currentHealth>maxHealth*.60)){
            return "blood trinkles down your arm leaving dropplets on the floor";
        }else if((currentHealth<maxHealth)&&(currentHealth>maxHealth*.40)){
            return "your arms and legs feel heavy. moving is painful. why not rest a little?";
        }else if((currentHealth<maxHealth)&&(currentHealth>maxHealth*.20)){
            return "your wounds wont stop bleeding. your vision is fuzzy";
        }else if(currentHealth<maxHealth*.20){
            return "you see a hooded skeleton reach out for you. you are about to die";
        }else{
            return"you are fit as a fiddle";
        }
        
        
    }
    
}
