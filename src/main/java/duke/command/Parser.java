package duke.command;

import duke.exception.DukeException;

import java.util.HashMap;

class Parser {

   private final HashMap<String, CMD> commandMap = new HashMap<String, CMD>();

   // NOTE: new instance of Command object is returned each time

   public Parser() {
       // generate a HashMap to allow fast lookup of command types
      for (CMD cmd : CMD.values()) {
         commandMap.put(cmd.toString(), cmd);
      }
   }

   public Command parse(String inputStr) throws DukeException {
      inputStr = inputStr.replace("\t", "    "); //sanitise input
      int firstSpaceIdx = inputStr.indexOf(" "); //index of first space
      String cmdStr = (firstSpaceIdx == -1) ? inputStr : inputStr.substring(0, firstSpaceIdx); //extract command name
      CMD cmd = commandMap.get(cmdStr);
      if (cmd == null) {
         throw new DukeException("I'm sorry, but I don't know what that means!");
      }
      Command command = cmd.getCommand();
      //trim command and first space after it from input if needed
      // TODO: if possible, disambiguate using functions
      if (command instanceof ArgCommand) {
         // stripping not required otherwise
         inputStr = inputStr.substring(cmdStr.length()).strip();
      }
      command.parse(inputStr);
      return command;
   }
}
