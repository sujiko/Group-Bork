package GroupBork;

import java.util.ArrayList;

   /**
    * the unlock command will allow the player to open a previously inaccessible exit if they meet the correct conditions
    * @author Elliot Tucker
    */
    public class UnlockCommand extends Command{
        private ArrayList<Exit> exits = new ArrayList<Exit>();
        private ArrayList<String> inventory = new ArrayList<String>();

        
      public UnlockCommand()
      {
          this.exits = GameState.Instance().getAdvenurersCurrentRoom().getExits();
          this.inventory = GameState.Instance().getInventoryNames();
      }
      
      /**
      * execute checks if unlocking is possible given the circumstances
      * if unlocking is not possible, it prints a message saying that it cannot be done
      * if unlocking is possible, it makes the given exit accessible 
      */
      public String execute() {        
           for (int i = 0; i < exits.size(); i++) 
           {
            if(exits.get(i).getLockState())
            {
              for(int j = 0; j < inventory.size(); j++)
              {
                if(inventory.get(j).equals(exits.get(i).getKeyItem()))
                {
                    exits.get(i).unlock();
                    return "The door unlocked!";
                }
              }
              return "You need an item!";
            }
           }
           return "No exits to unlock.";
        }
      }