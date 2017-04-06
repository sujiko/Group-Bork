/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBorkv1.borkv3;

import java.util.ArrayList;

/**
 * this class will create monsters that you can fight with
 * @author christian
 */
public class Monster {
    private String monName;
    private int life;
    private int attkPWR;
    private ArrayList lootItems;
    /**
     * the monster when created will have a name,some health, an attack power
     * so it can fight you and a list of items it can drop when dead
     * @param name
     * @param attk
     * @param loot 
     * @param health
     */
    public Monster(String name,int health,int attk, ArrayList loot){
        this.attkPWR=attk;
        this.lootItems= loot;
        this.monName=name;
        this.life = health;
    }
    /**
     * when the monster attacks this method will calculate how much damage you take
     * @return a string telling you how much damage you took
     */
    public String MonAttack(){
        return "";
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
    }
    /**
     * 
     * @return monsters amount of health
     */
    public int getLife(){
        return this.life;
    }
    /**
     * this will be used to scale the monsters health according to the users level
     */
    public void setLife(){
    }
    /**
     * this will be used to get the loot the monster holds when it is killed
     * @return an arraylist of loot that will be dropped when the monsters health hits 0
     */
    public ArrayList getLoot(){
        return this.lootItems;
    }
            
    
    
    
}
