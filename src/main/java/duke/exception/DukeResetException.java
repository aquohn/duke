package duke.exception;

public class DukeResetException extends DukeException {

   public DukeResetException(String msg) {
       super(msg + " You can back up the file, and/or reset it!"  + System.lineSeparator()
               + "Do you want to reset your Duke data now," + " to continue using Duke? (y/n)");
   }
}
