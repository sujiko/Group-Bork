/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;
       
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
    public AttackCommand(){
    }
    
    /**
     * when run the execute will check to see if the monster is in the current room.
     * if a monster is in the room it will calculate your damage to the monster and then
     * tell you if you killed it or not and what rewards you get
     * @return 
     */
    public String execute(){
        Combat a = new Combat();
        String resp="";
        if(Follower.Instance().isFollowing==true){
            resp +=a.followerVmon();
            int chance = (int)(Math.random()*1);
            if(chance ==0){
                resp += a.monVfollower()+"\n"+a.userVmon();
            }else{
                resp += a.monVuser()+"\n"+a.userVmon();
            }
        }else{
            resp += a.monVuser()+"\n"+a.userVmon();
        }
        return resp;
        
    }
}
