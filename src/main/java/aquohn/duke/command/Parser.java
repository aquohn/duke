package aquohn.duke.command;

public class Parser {

   public Parser() {

   }

   public Command parse(String inputStr) {
      inputStr = inputStr.replace("\t", "    "); //sanitise input
      int firstSpaceIdx = inputStr.indexOf(20); //index of first space
      String cmdStr = (firstSpaceIdx == -1) ? inputStr : inputStr.substring(firstSpaceIdx);

      switch(cmdStr) {
      case Command.BYE:
      }
   }
}
