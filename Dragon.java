/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupBork;

/**
 *  Initializes singleton "Dragon" which extends the class monster
 * 
 * @author Elliot
 */
public class Dragon extends Monster
{
    private static final Dragon theDragon = new Dragon("Dragon");

    private Dragon(String name) 
    {
        super(name);
    }
}
