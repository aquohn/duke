package duke.exception;

import duke.command.Ui;

public class DukeFatalException extends DukeException {

   public DukeFatalException(String msg) {
       super(msg + " Exiting Duke now..."); 
   }

   public void killProgram(Ui ui) {
      ui.print(getMessage());
      System.exit(0);
   }
}
