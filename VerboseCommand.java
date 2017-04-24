/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *verbose command will make it so the user can continually get a description
 *of the room from bork
 * @author qureshi225
 */
public class VerboseCommand extends Command {
    public VerboseCommand(){
    }
    
    public String execute(){
        GameState.Instance().changeVerbose();
        if(GameState.Instance().getVerbose()==true){
            return "Verbose mode is on";
        }else{
            return "Verbose mose is off";
        }
    }
}
