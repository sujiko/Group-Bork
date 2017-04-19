/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

import java.util.ArrayList;

/**
 * this class will create monsters that you can fight with
 * @author christian
 */
public class Monster {
    private String monName;
    private int life;
    private int attkPWR;
    private ArrayList<Item> lootItems;
    boolean hostile = true;
    /**
     * the monster when created will have a name,some health, an attack power
     * so it can fight you and a list of items it can drop when dead
     * @param name
     * @param loot 
     */
    public Monster(String name){
        //this.lootItems=loot;
        this.monName=name;
    }
    
    /**
     * tells you the monsters name
     * @return monsters name
     */
    public String getMonName(){
        return this.monName;
    }
    /**
     * tells you the monsters attack power
     * @return monsters attack power
     */
    public int getAttack(){
        return this.attkPWR;
    }
    /**
     * this will be used to scale the monsters attack according to the users level
     */
    public void setAttack(){
        this.attkPWR = GameState.Instance().getStrength() /4;
    }
    /**
     * this will return the monsters life
     * @return monsters amount of health
     */
    public int getLife(){
        return this.life;
    }
    /**
     * allows monster to take damage
     * @param dmg 
     */
    public void takeDMG(int dmg){
        this.life -=dmg;
    }
    /**
     * this will be used to scale the monsters health according to the users level
     */
    public void setLife(){
        int gamerLife = GameState.Instance().getHealth();
        switch((int)(Math.random()*1)){
            case 0:
                this.life = gamerLife + 5;
                break;
            case 1:
                this.life = gamerLife -5;
                break;
        }       
    }
    /**
     * this will be used to get the loot the monster holds when it is killed
     * an arraylist of loot that will be dropped when the monsters health hits 0
     */
    public void getLoot(){
        for(Item item:lootItems){
            GameState.Instance().addToInventory(item);
        }
    }
    /**
     * this will be used to manually set the monsters hostility to true.
     */
    public void setHostileT(){
        this.hostile=true;
    }
    /**
     * this will be used to set monster hostility to false
     */
    public void setHostileF(){
        this.hostile=false;
    }
    public boolean getHostility(){
        return this.hostile;
    }
    
    
    
    
}
