package duke.exception;

public class DukeException extends Exception {

   public DukeException(String msg) {
       super("Oops! :( " + msg); //yeah this is pretty useless for now
   }
}
