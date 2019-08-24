import java.lang.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // TODO: create HashMap of (string, function) pairs for commands

    private static final String CMD_LIST = "list";
    private static final String CMD_BYE = "bye";

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        
        String[] hiArr = {"Hello, I'm Duke!", "What can I do for you?"};
        String[] byeArr = {"Bye. Hope to see you again soon!"}

        say(hiArr);
        
        ArrayList<Task> taskList = new ArrayList<Task>();
        String[] sayArr = new String[1];
        Scanner scanIn = new Scanner(System.in);
        while (true) {
          sayArr[0] = scanIn.nextLine();
          switch (sayArr[0]) {
          case CMD_LIST:
              listTasks(taskList); //these names ordinarily make more sense than they appear to here
              break;
          case CMD_BYE:
              say(byeArr);
              break;
          default:
              taskList.add(sayArr[0]);
        }
    }

    private static void say(String[] msg) {
      String line = "    ____________________________________________________________";
      String indent = "    ";
      System.out.println(line);
      for (int i = 0; i < msg.length; ++i) {
        System.out.println(indent + msg[i]);
      }
      System.out.println(line);
      System.out.println("");
    }

    private static void listTasks(ArrayList<Task> taskList) {
        String[] sayArr = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); ++i) {
            sayArr[i] = taskList.get(i);
        }
    }
}
