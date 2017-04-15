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
    
    public Event parse(String eventString, Item item) {
               String[] event= eventString.split("\\(");
               String action= event[0].toLowerCase();
               String actedUpon=null;
               if(event.length==2){
                   actedUpon=event[1].substring(0, event[1].length()-1);
               }
               switch (action){
                   case "disappear":
                       return new DisappearEvent(item);
                   case "transform":
                       return new TransformEvent(actedUpon,item);
                   case "wound":
                       return new WoundEvent(actedUpon,item);
                   case "score":
                       return new ScoreEvent(actedUpon);
                   case "die":
                       return new DieEvent();
                   case "teleport":
                       return new TeleportEvent(); 
                   case "win":
                       return new WinEvent();
                   default:
                       System.out.println("There was an error with the EventFactory Class... Exiting.");
                       System.exit(54);
                       return null;
               }      
    }
}
