/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *
 * @author Qures
 */
public class FollowEvent  extends Event{
    
    public void execute(){
        Follower f=Follower.Instance();
      if(f.isFollowing){
          f.isFollowing=false;
      }else{
          f.isFollowing=true;
      }
    }
}
