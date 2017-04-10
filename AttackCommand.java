/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupborkv1.Group-Bork;

/**
 * the attack command will allow the player to attack monsters
 * @author christian
 */
public class AttackCommand extends Command{
    String monsterName;
    /**
     * when run the AttackCommand will assign the name of the monster to the variable monsterName
     * @param noun 
     */
    public AttackCommand(String noun){
        this.monsterName=noun;
    }
    
    /**
     * when run the execute will check to see if the monster is in the current room.
     * if a monster is in the room it will calculate your damage to the monster and then
     * tell you if you killed it or not and what rewards you get
     * @return 
     */
    public String execute(){
        return"";
    }
}
