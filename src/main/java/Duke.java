import java.util.Scanner;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.io.File;
import java.io.FileWriter;

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
        String[] addArr = {"Got it, I've added this task:", "", ""};
        String[] doneArr = {"Nice! I've marked this task as done:", ""};

        say(hiArr);

        ArrayList<Task> taskList = new ArrayList<Task>();
        String inputStr;
        String[] sayArr = new String[1];
        String[] splitArr;
        Scanner scanIn = new Scanner(System.in);

        while (true) {
            Boolean isValid = true;
            inputStr = scanIn.nextLine();
            String[] argArr = inputStr.split(" ");

            try {
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
                        } else {
                            throw new DukeException("I don't have that entry in the list!");
                        }
                    } else {
                        throw new DukeException("You didn't tell me which entry to mark as done!");
                    }
                    break;

                case CMD_TODO:
                    addTask(taskList, ToDo.class, inputStr, null, CMD_TODO);
                    break;

                case CMD_EVENT:
                    addTask(taskList, Event.class, inputStr, KW_AT, CMD_EVENT);
                    break;

                case CMD_DLINE:
                    addTask(taskList, Deadline.class, inputStr, KW_BY, CMD_DLINE);
                    break;

                default:
                    throw new DukeException("I'm sorry, but I don't know what that means!");
                }
            } catch (DukeException excp) {
                String[] errArr = new String[1];
                errArr[0] = excp.getMessage();
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
        int taskCount = taskList.size();
        if (taskCount == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }
        String[] sayArr = new String[taskCount];
        for (int i = 0; i < taskCount; ++i) {
            Task currTask = taskList.get(i);
            sayArr[i] = Integer.toString(i + 1) + "." + currTask.toString();
        }
        say(sayArr);
    }

    private static <T extends Task> void addTask(ArrayList<Task> taskList, Class<T> taskClass, String inputStr, String keyword, String cmd) {
        String[] addArr = {"Got it, I've added this task:", "", ""};
        String desc;
        Class[] oneString = {String.class};
        Class[] twoStrings = {String.class, String.class};
        T newTask;

        try {
            if (keyword != null) { //Task consists of two parts separated by a keyword
                String[] splitArr = inputStr.split(keyword, 2);

                //NOTE: substring index OOB exceptions not caught, control flow excludes them
                if (splitArr.length < 2) {
                    throw generateInvalidTaskException(taskClass);
                } else {
                    Constructor taskConst = taskClass.getConstructor(twoStrings);
                    desc = splitArr[0].substring(cmd.length());
                    if (desc.length() == 0) {
                        throw new DukeException("The task description cannot be empty!");
                    }
                    newTask = (T) taskClass.cast(taskConst.newInstance(desc , splitArr[1]));
                } 
            } else {
                Constructor taskConst = taskClass.getConstructor(oneString);
                desc = inputStr.substring(cmd.length());
                if (desc.length() == 0) {
                    throw new DukeException("The task description cannot be empty!");
                }
                newTask = (T) taskClass.cast(taskConst.newInstance(desc));
            }
            taskList.add(newTask);
            
            int taskCount = taskList.size();
            addArr[1] = "  " + taskList.get(taskCount - 1).toString();
            String taskCountStr = Integer.toString(taskCount) + ((taskCount == 1) ? " task" : " tasks");
            addArr[2] = "Now you have " + taskCountStr + " in the list.";
            say(addArr);

        } catch (Exception excp) { //catch exceptions from generics, incidentally also catches Duke exceptions
            String[] errArr = new String[1];
            errArr[0] = excp.getMessage();
            say(errArr);
        }
    }

    private static DukeException generateInvalidTaskException(Class taskClass) {
        if (taskClass == Event.class) {
            return new DukeException("Invalid event! Events must specify when they are /at.");
        } else if (taskClass == Deadline.class) {
            return new DukeException("Invalid deadline! Deadlines must specify when they are due /by.");
        } else {
            return new DukeException("I don't recognise that type of class!");
        }
    }
}
