import java.lang.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // TODO: create HashMap of (string, function) pairs for commands

    private static final String CMD_LIST = "list";
    private static final String CMD_BYE = "bye";
    private static final String CMD_DONE = "done";
    private static final String CMD_TODO = "todo";
    private static final String CMD_EVENT = "event";
    private static final String CMD_DLINE = "deadline";

    private static final String KW_AT = "/at";
    private static final String KW_BY = "/by";
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
        String[] hiArr = {"Hello, I'm Duke!", "What can I do for you?"};
        String[] byeArr = {"Bye. Hope to see you again soon!"};
        String[] errArr = {"I can't do that!"};
        String[] addArr = {"Got it, I've added this task:", "", ""};
        String[] doneArr = {"Nice! I've marked this task as done:", ""};

        say(hiArr);
        
        ArrayList<Task> taskList = new ArrayList<Task>();
        String inputStr;
        String[] sayArr = new String[1];
        Scanner scanIn = new Scanner(System.in);
        while (true) {
          Boolean isValid = false;
          inputStr = scanIn.nextLine();
          String[] argArr = inputStr.split(" ");

          switch (argArr[0]) {
          case CMD_LIST:
              listTasks(taskList); //these names ordinarily make more sense than they appear to here
              break;

          case CMD_BYE:
              say(byeArr);
              System.exit(0);
              break;

          case CMD_DONE:
              if (argArr[1].matches("\\d+")) { //if second arg is an integer
                 int idx = Integer.parseInt(argArr[1]) - 1;
                 if (idx < taskList.size()) {
                    Task currTask = taskList.get(idx);
                    currTask.markDone();
                    doneArr[1] = "  " + currTask.toString();
                    say(doneArr);
                    isValid = true;
                 }
              }
              break;

          case CMD_TODO:
              taskList.add(new ToDo(inputStr.substr(CMD_TODO)));
              addArr[1] = "  " + taskList.get(taskList.size() - 1).toString();
              addArr[2] = "Now you have " + Integer.parseInt(taskList.size()) + " tasks in the list."

          case CMD_EVENT:
              String[] splitArr = inputStr.split(KW_AT, 2);
              if (splitArr[2].length() == 0) {
                  break;
              }
              isValid = true;
              // remove command name from task name
              taskList.add(new Event(splitArr[0].substr(CMD_EVENT.length()), splitArr[1]));
              addArr[1] = "  " + taskList.get(taskList.size() - 1).toString();
              addArr[2] = "Now you have " + Integer.parseInt(taskList.size()) + " tasks in the list."
              break;
          
          case CMD_DLINE:
              String[] splitArr = inputStr.split(KW_BY, 2);
              if (splitArr[2].length() == 0) {
                  break;
              }
              isValid = true;
              // remove command name from task name
              taskList.add(new Deadline(splitArr[0].substr(CMD_DLINE.length()), splitArr[1]));
              addArr[1] = "  " + taskList.get(taskList.size() - 1).toString();
              addArr[2] = "Now you have " + Integer.parseInt(taskList.size()) + " tasks in the list."
              break;
          }

          if (!isValid) {
              say(errArr);
          }
        }
    }

    private static void say(String[] msgArr) {
      String line = "    ____________________________________________________________";
      String indent = "    ";
      System.out.println(line);
      for (int i = 0; i < msgArr.length; ++i) {
        System.out.println(indent + msgArr[i]);
      }
      System.out.println(line);
      System.out.println("");
    }

    private static void listTasks(ArrayList<Task> taskList) {
        String[] sayArr = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); ++i) {
            Task currTask = taskList.get(i);
            sayArr[i] = Integer.toString(i + 1) + "." + currTask.toString();
        }
        say(sayArr);
    }
}
