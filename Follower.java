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
    private boolean isFollowing= false;
    private Item offItem;
    private Item onItem;
    private static String name;
    private static Follower onlyInstance;
    

      Follower(String name) {
        this.name = name;
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
  /**
  * this method is to heal the follower
  *param i
  *       the int that the follower will heal by
  */
  
  /**
  *this method is to damage the follower
  *param i
  *        thhe int the follower will take damage by
  */
  
  
}