public class DukeException extends RuntimeException {

   public DukeException(String msg) {
       super("Oops! :( " + msg); //yeah this is pretty useless for now
   }
}
