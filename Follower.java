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
    private int maxHp;
    public boolean isFollowing= false;
    private static String name;
    private static Follower onlyInstance;
    private int ATKpower;
    

      Follower(String name) {
        this.name ="Casper";
        this.health= (int) (GameState.Instance().getMaxHealth()*.75);
        this.maxHp= (int)(GameState.Instance().getMaxHealth()*.75);
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
  public void takeDmg(int dmg){
      this.health -=dmg;
      if(this.health<0){
          this.health=0;
          this.isFollowing=false;
          System.out.println(this.name +"has died and is no longer following you");
      }
  }
  public void recoverHP(){
      int reco = (int)(this.maxHp*.25);
      this.health += reco;
      if(this.health>this.maxHp){
          this.health= this.maxHp;
      }
  }
}
