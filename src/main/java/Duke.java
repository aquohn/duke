import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.io.File;
import java.io.FileWriter;
import java.time.DateTimeException;

public class Duke {

    // TODO: create HashMap of (string, function) pairs for commands

    // TODO: convert constants to enums

    public static final String CMD_LIST = "list";
    public static final String CMD_BYE = "bye";
    public static final String CMD_DONE = "done";
    public static final String CMD_TODO = "todo";
    public static final String CMD_EVENT = "event";
    public static final String CMD_DLINE = "deadline";

    public static final String KW_AT = "/at";
    public static final String KW_BY = "/by";

    // TODO: add help command

    public static void main(String[] args) {
        //print welcome message
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\nHello from\n" + logo);

        //standard messages
        String[] hiArr = {"Hello, I'm Duke!", "What can I do for you?"};
        String[] byeArr = {"Bye. Hope to see you again soon!"};

        say(hiArr);

        TaskList taskList; // TODO: convert this into a class member, and make Duke non-static
        String inputStr;
        String[] sayArr = new String[1];
        String[] splitArr;
        Scanner scanIn = new Scanner(System.in);

        try {
            taskList = new TaskList(false);
        } catch (DukeResetException excp) {
            String resetStr;
            String[] errArr = {excp.getMessage(), "Do you want to reset your Duke data now,"
                + " to continue using Duke? (y/n)"};
            say(errArr);
            while (true) { //wait for user to respond
                resetStr = scanIn.nextLine();
                if (resetStr.length() > 0) {
                    resetStr = resetStr.substring(0,1); //extract first char
                    if (resetStr.equalsIgnoreCase("y")) {
                        taskList = new TaskList(true);
                        say(new String[] {"Your data has been reset!"});
                        break;
                    } else if (resetStr.equalsIgnoreCase("n")) {
                        scanIn.close();
                        say(new String[] {"Exiting Duke..."});
                        System.exit(0);
                    }
                }
            }
        } catch (DukeFatalException excp) {
            taskList = null; //just to keep the compiler happy
            String[] errArr = {excp.getMessage()};
            say(errArr);
            System.exit(0);
        }

        while (true) {
            Boolean isValid = true;
            inputStr = scanIn.nextLine();
            inputStr.replace("\t", "    "); //sanitise input
            String[] argArr = inputStr.split(" ");

            try {
                switch (argArr[0]) {
                    case CMD_LIST:
                        say(taskList.listTasks()); //these names ordinarily make more sense than they appear to here
                        break;

                    case CMD_BYE:
                        taskList.writeTaskFile();
                        scanIn.close();
                        say(byeArr);
                        System.exit(0);
                        break;

                    case CMD_DONE:
                        if (argArr.length < 2) {
                            throw new DukeException("You didn't tell me which entry to mark as done!");
                        }
                        say(taskList.markDone(argArr[1]));
                        break;

                    case CMD_TODO:
                        inputStr = inputStr.substring(CMD_TODO.length());
                        say(taskList.addTask(ToDo.class, inputStr, null));
                        break;

                    case CMD_EVENT:
                        inputStr = inputStr.substring(CMD_EVENT.length());
                        say(taskList.addTask(Event.class, inputStr, KW_AT));
                        break;

                    case CMD_DLINE:
                        inputStr = inputStr.substring(CMD_DLINE.length());
                        say(taskList.addTask(Deadline.class, inputStr, KW_BY));
                        break;

                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means!");
                }
            } catch (DukeFatalException excp) {
                String[] errArr = {excp.getMessage()};
                say(errArr);
                System.exit(0);
            } catch (DukeException excp) {
                String[] errArr = {excp.getMessage()};
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
        System.out.println(line + System.lineSeparator());
    }
}
