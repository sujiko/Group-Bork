/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;

/**
 * returns a message that lets you know that the system doesn't understand your command
 * @author qureshi225
 */
public class UnknownCommand extends Command {
    String bogusCommand;
    /**
     * assigns your command the system doesn't recognize to the variable bogusCommand
     * @param bc 
     */
    public UnknownCommand(String bc){
        bogusCommand=bc;
    }
    /**
     * runs when the system doesn't understand your command
     * @return returns a message that lets you know that the system doesn't understand your command
     */
    public String execute(){
        return "You can't do that..... but you tried! A for effort.";
    }
}
