import java.lang.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Constructor;

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

          //TODO: investigate possibility of returning Object and casting within this context

          case CMD_TODO:
              if (addTask(taskList, ToDo.class, inputStr, null, CMD_TODO)) {
                  isValid = true;
              }
              break;

          case CMD_EVENT:
              if (addTask(taskList, Event.class, inputStr, KW_AT, CMD_EVENT)) {
                  isValid = true;
              }
              break;
          
          case CMD_DLINE:
              if (addTask(taskList, Deadline.class, inputStr, KW_BY, CMD_DLINE)) {
                  isValid = true;
              }
              break;
          }

          if (!isValid) {
              say(errArr);
          }
        }
    }
    
    // for Duke to say any arbitrary message
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

    private static Boolean addTask(ArrayList<Task> taskList, Class<T extends Task> taskClass, String inputStr, String keyword, String cmd) {
        String[] addArr = {"Got it, I've added this task:", "", ""};
        Class[] oneString = {String.class};
        Class[] twoStrings = {String.class, String.class};
        T newTask;

        if (keyword != null) { // Task type consists of two parts separated by a keyword
            String[] splitArr = inputStr.split(keyword, 2);
            if (splitArr.length < 2) {
                return false;
            } else {
                Constructor taskConst = taskClass.getConstructor(twoStrings);
                newTask = (T) taskClass.cast(taskConst.newInstance(splitArr[0].substring(cmd.length()), splitArr[1]));
            }
        } else {
            Constructor taskConst = taskClass.getConstructor(oneString);
            newTask = (T) taskClass.cast(taskConst.newInstance(inputStr.substring(cmd.length())));
        }
        taskList.add(newTask);
        addArr[1] = "  " + taskList.get(taskList.size() - 1).toString();
        addArr[2] = "Now you have " + Integer.toString(taskList.size()) + " tasks in the list.";
        say(addArr);
        return true;
    }
}
