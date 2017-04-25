/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *this class will handle calculating how much damage you, your follower, and monsters will take
 * @author christian
 */
public class Combat {
     private int userHP;
     private int userATK;
     private int monHP;
     private int monATK;
     private int monMaxHP;
     private GameState State;
     private String monName;
     private int followerHP;
     private int followerATK;
    public Combat(){
        this.State=GameState.Instance();
        this.userHP=State.getHealth();
        this.userATK= State.getStrength();
        this.monHP= State.getAdvenurersCurrentRoom().getMonster().getLife();
        this.monATK=State.getAdvenurersCurrentRoom().getMonster().getAttack();
        this.monName= State.getAdvenurersCurrentRoom().getMonster().getMonName();
        this.monMaxHP=State.getAdvenurersCurrentRoom().getMonster().getMax();
        this.followerHP = Follower.Instance().getHealth();
        this.followerATK = Follower.Instance().getStrength();
    }
    /**
     * will calculate how much damage the monster inflicts on the user
     * @return damage to user
     */
    public String monVuser(){
        String resp ="";
        int chance = (int)(Math.random()*99)+1;
        if(chance>80){
            int dmg = this.monATK *2;
            resp= "The "+this.monName+" inflicted "+dmg+" on you";
            State.minusHealth(dmg);
        }else if((chance<80)&& (chance>50)){
            int dmg = this.monATK;
            resp= "The "+this.monName+" inflicted "+dmg+" on you";
            State.minusHealth(dmg);
        }else if(chance<50){
            int dmg = this.monATK/2;
            resp= "The "+this.monName+" inflicted "+dmg+" on you";
            State.minusHealth(dmg);
        }
        return resp;
    }
    
    /**
     * will calculate how much damage the monster inflicts on the follower
     * @return damage to follower
     */
    public String monVfollower(){
        String resp="";
        int chance = (int)(Math.random()*99)+1;
        if(chance>80){
            int dmg = this.monATK *2;
            resp= "The "+this.monName+" inflicted "+dmg+" on your follower";
            Follower.Instance().takeDmg(dmg);
        }else if((chance<80)&& (chance>50)){
            int dmg = this.monATK;
            resp= "The "+this.monName+" inflicted "+dmg+" on your follower";
            Follower.Instance().takeDmg(dmg);
        }else if(chance<50){
            int dmg = this.monATK/2;
            resp= "The "+this.monName+" inflicted "+dmg+" on your follower";
            Follower.Instance().takeDmg(dmg);
        }
        return resp;
    }
    
    /**
     * will calculate how much damage you do towards the monster
     * @return damage to monster
     */
    public String userVmon(){
        String resp= "";
        if(State.getAdvenurersCurrentRoom().getMonster()==null){
            return "the monster has already been killed by your follower";
        }else{
        int chance = (int)(Math.random()*99)+1;
        if(chance>80){
            int dmg = this.userATK *2;
            this.monHP -= dmg;
            State.getAdvenurersCurrentRoom().getMonster().takeDMG(dmg);
            if(this.monHP<1){
                State.getAdvenurersCurrentRoom().removeMonster();
                resp="You killed the "+this.monName+" the loot is in your inventory \n";
            }else{
                resp= "The "+this.monName +" has "+this.monHP +"HP left \n";
            }
        }else if((chance<80)&& (chance>20)){
            int dmg = this.userATK;
            this.monHP -= dmg;
            State.getAdvenurersCurrentRoom().getMonster().takeDMG(dmg);
            if(this.monHP<1){
                State.getAdvenurersCurrentRoom().removeMonster();  
                resp="You killed the "+this.monName+" the loot is in your inventory \n";
            }else{
                resp= "The "+this.monName +" has "+this.monHP +"HP left \n";
            }
        }else if(chance<20){
            int dmg = this.userATK/2;
            this.monHP -=dmg;
            State.getAdvenurersCurrentRoom().getMonster().takeDMG(dmg);
            if(this.monHP<1){
                State.getAdvenurersCurrentRoom().removeMonster();
                resp="You killed the "+this.monName+" the loot is in your inventory \n";
            }else{
                resp= "The "+this.monName +" has "+this.monHP +"HP left \n";
            }       
        }}
        return resp;
    }
    
    /**
     * will calculate how much damage your follower inflicts on the monster
     * @return damage to monster
     */
    public String followerVmon(){
        String resp="";
        int chance = (int)(Math.random()*99)+1;
        if(chance>80){
            int dmg = this.userATK *2;
            this.monHP -= dmg;
            State.getAdvenurersCurrentRoom().getMonster().takeDMG(dmg);
            if(this.monHP<1){
                State.getAdvenurersCurrentRoom().removeMonster();
                resp="Your follower killed the "+this.monName+" the loot is in your inventory \n";
            }else{
                resp= "The "+this.monName +" has "+this.monHP +"HP left \n";
            }
        }else if((chance<80)&& (chance>20)){
            int dmg = this.userATK;
            this.monHP -= dmg;
            State.getAdvenurersCurrentRoom().getMonster().takeDMG(dmg);
            if(this.monHP<1){
                State.getAdvenurersCurrentRoom().removeMonster();  
                resp="Your follower killed the "+this.monName+" the loot is in your inventory \n";
            }else{
                resp= "The "+this.monName +" has "+this.monHP +"HP left \n";
            }
        }else if(chance<20){
            int dmg = this.userATK/2;
            this.monHP -=dmg;
            State.getAdvenurersCurrentRoom().getMonster().takeDMG(dmg);
            if(this.monHP<1){
                State.getAdvenurersCurrentRoom().removeMonster();
                resp="Your follower killed the "+this.monName+" the loot is in your inventory \n";
            }else{
                resp= "The "+this.monName +" has "+this.monHP +"HP left \n";
            }       
        }
        return resp;
    }
    
    public String magicVmon(){
        String resp ="You cast Firebolt \n";
        if(State.getAdvenurersCurrentRoom().getMonster()==null){
            return "the monster has already been killed by your follower";
        }else{
        int random= (int)(Math.random()*1);
        if(random ==0){
            int dmg = (int)(this.monMaxHP*.5);
            this.monHP -=dmg;
            State.getAdvenurersCurrentRoom().getMonster().takeDMG(dmg);
            if(this.monHP<1){
                State.getAdvenurersCurrentRoom().removeMonster();
                resp+="You killed the "+this.monName+" the loot is in your inventory \n";
            }else{
                resp+= "The "+this.monName +" has "+this.monHP +"HP left \n";
            }   
        }else if(random ==1){
            int dmg = (int)(this.monMaxHP*.75);
            this.monHP -=dmg;
            State.getAdvenurersCurrentRoom().getMonster().takeDMG(dmg);
            if(this.monHP<1){
                State.getAdvenurersCurrentRoom().removeMonster();
                resp+="You killed the "+this.monName+" the loot is in your inventory \n";
            }else{
                resp+= "The "+this.monName +" has "+this.monHP +"HP left \n";
            }     
        }}
        return resp;    
    }
}
