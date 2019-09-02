package duke.command;

import duke.exception.DukeException;

import java.util.HashMap;

public class Parser {

   private HashMap<String, Command> commandMap = new HashMap<String, Command>();

   public Parser() {
      for (CMD cmd : CMD.values()) {
         commandMap.put(cmd.toString(), cmd.getCommand());
      }
   }

   public Command parse(String inputStr) throws DukeException {
      inputStr = inputStr.replace("\t", "    "); //sanitise input
      int firstSpaceIdx = inputStr.indexOf(20); //index of first space
      String cmdStr = (firstSpaceIdx == -1) ? inputStr : inputStr.substring(firstSpaceIdx + 1);
      Command cmd = commandMap.get(cmdStr);
      if (cmd == null) {
         throw new DukeException("I'm sorry, but I don't know what that means!");
      }
      inputStr = inputStr.substring(cmdStr.length() + 2).strip();
      //trim command and first space after it from input if needed
      // TODO: if possible, disambiguate using functions
      if (cmd instanceof ArgCommand) {
         // stripping not required otherwise
         inputStr = inputStr.substring(cmdStr.length() + 2).strip();
      }
      cmd.parse(inputStr);
      return cmd;
   }
}
