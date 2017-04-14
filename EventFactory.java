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
public class EventFactory {

    private static EventFactory onlyInstance;

    private EventFactory() {

    }
    /**
     * @return creates an instance of the command factory if one hasn't been created yet
     */
    public static synchronized EventFactory getInstance() {
        if (onlyInstance == null) {
            onlyInstance = new EventFactory();
        }
        return onlyInstance;
    }
    
    public Event parse(String eventString) {
               String[] event= eventString.split("(");
               String action= event[0];
               if(event.length==2){
                   String actedUpon=event[1].substring(0, event[1].length()-1);
               }
               switch (event[0]){
                   
               }
                       
    }
}
