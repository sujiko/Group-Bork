/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *this class holds a follower if there is one for the bork game.
 * @author qureshi225
 */
public class Follower {
    private int health;
    public boolean isFollowing= false;
    private Item offItem;
    private Item onItem;
    private static String name;
    private static Follower onlyInstance;
    private int ATKpower;
    

      Follower(String name) {
        this.name = name;
        this.health= (int) (GameState.Instance().getMaxHealth()*.75);
        this.ATKpower= (int)(GameState.Instance().getStrength()*.75);
    }

    public static synchronized Follower Instance() {
        if (onlyInstance == null) {
            onlyInstance= new Follower(name);
        }
        return onlyInstance;
    }
  /** 
  * gives the current health of the follower
  *@return returns the current health of the follower as a int. 
  */
  public int getHealth(){
    return this.health;
  }

  public int getStrength(){
      return this.ATKpower;
  }
  
  
}
