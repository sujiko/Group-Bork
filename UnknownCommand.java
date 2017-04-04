/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

/**
 *
 * @author qureshi225
 */
public class UnknownCommand extends Command {
    String bogusCommand;
    
    public UnknownCommand(String bc){
        bogusCommand=bc;
    }
    public String execute(){
        return "You can't do that..... but you tried! A for effort.";
    }
}
