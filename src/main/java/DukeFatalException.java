public class DukeFatalException extends DukeException {

   public DukeFatalException(String msg) {
       super(msg + " Exiting Duke now..."); 
   }
}
