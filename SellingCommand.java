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
   public class SellingCommand extends Command {


    public SellingCommand() {

    }
    
    public String execute(){
        if(GameState.Instance().getAdvenurersCurrentRoom()==Shopkeeper.Instance().getRoom()){
                    return Shopkeeper.Instance().printSelling();
        }else{
            return "You aren't even't close to the shop!";
        }

    }
}
