import java.lang.*;
import java.util.Scanner;
import Task;

public class Duke {

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        
        String[] intro = {"Hello, I'm Duke!", "What can I do for you?"};

        say(intro);

        String[] sayArr = new String[1];
        Scanner scanIn = new Scanner(System.in);
        while (true) {
          sayArr[0] = scanIn.nextLine();
          say(input);
        }
    }

    private static void say(String[] msg) {
      String line = "    ____________________________________________________________";
      String indent = "    ";
      System.out.println(line);
      int lineCount = msg.length;
      for (int i = 0; i < lineCount; ++i) {
        System.out.println(indent + msg[i]);
      }
      System.out.println(line);
      System.out.println("");
    }
}
