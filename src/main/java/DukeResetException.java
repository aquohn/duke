public class DukeResetException extends DukeException {

   public DukeResetException(String msg) {
       super(msg + " You can back up the file, and/or reset it!"); 
   }
}
