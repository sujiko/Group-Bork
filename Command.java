/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package borkv3;


/**
 * @param nothing
 * @return a string from the execute command
 * @throws nothing
 * @author qureshi225
 */
abstract class Command {
    /**
     * 
     * @return a string describing what you did or where you went
     */
    abstract String execute();
}

