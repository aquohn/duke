public class DukeException extends Exception {

   public DukeException(String msg) {
       super(msg); //yeah this is pretty useless for now

       /*if (taskClass == null) { //generic exception for unknown command
           super("Oops! :( I'm sorry, but I don't know what that means!");
       } else if (taskClass == Event.class) {
           super("Oops! :( Invalid event! Events need a description and"
                   + "must specify when they will be /at.");
       } else if (taskClass == ToDo.class) {
           super("Oops! :( Invalid todo! Todo items need to have a description!");
       } else if (taskClass == Deadline.class) {
           super("Oops! :( Invalid deadline! Deadlines need a description and"
                   + "must specify when they are due /by.");
       } else {
           super("OH NO! :( An unknown exception has occurred!" 
                   + "Please contact me at john_khoo@u.nus.edu with the details!");
       }*/
   }
}
