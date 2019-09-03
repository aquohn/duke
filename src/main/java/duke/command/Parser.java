package duke.command;

import duke.exception.DukeException;

import java.util.HashMap;

class Parser {

   private final HashMap<String, Command> commandMap = new HashMap<String, Command>();

   public Parser() {
      for (CMD cmd : CMD.values()) {
         commandMap.put(cmd.toString(), cmd.getCommand());
      }
   }

   public Command parse(String inputStr) throws DukeException {
      inputStr = inputStr.replace("\t", "    "); //sanitise input
      int firstSpaceIdx = inputStr.indexOf(" "); //index of first space
      String cmdStr = (firstSpaceIdx == -1) ? inputStr : inputStr.substring(0, firstSpaceIdx);
      Command cmd = commandMap.get(cmdStr);
      if (cmd == null) {
         throw new DukeException("I'm sorry, but I don't know what that means!");
      }
      //trim command and first space after it from input if needed
      // TODO: if possible, disambiguate using functions
      if (cmd instanceof ArgCommand) {
         // stripping not required otherwise
         inputStr = inputStr.substring(cmdStr.length()).strip();
      }
      cmd.parse(inputStr);
      return cmd;
   }
}
