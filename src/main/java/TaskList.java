import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TaskList {

    // TODO: create HashMap of (string, function) pairs for commands

    //TSV files will have one entry per line, tabs disallowed in input

    private static final String KW_AT = "/at";
    private static final String KW_BY = "/by";

    private ArrayList<Task> taskArrList;
    private File taskFile;
    private FileWriter taskFileWr;
    private String[] addArr = {"Got it, I've added this task:", "", ""};

    public TaskList(boolean isReset) {
        taskFile = new File("../data/tasks.tsv");
        if (!isReset && taskFile.exists()) {
            taskArrList = parseTaskFile(taskFile);
        } else {
            taskArrList = new ArrayList<Task>();
        }
        try {
            taskFileWr = new FileWriter(taskFile); //will be avoided via exception if file corrupted
        } catch (IOException excp) {
            throw new DukeFatalException("Unable to write to data file, try checking your permissions?");
        }
    }

    public String[] listTasks() {
        int taskCount = taskArrList.size();
        if (taskCount == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }
        String[] taskArr = new String[taskCount];
        for (int i = 0; i < taskCount; ++i) {
            Task currTask = taskArrList.get(i);
            taskArr[i] = Integer.toString(i + 1) + "." + currTask.toString();
        }
        return taskArr;
    }

    public String[] markDone(String idxStr) {
        if (idxStr.matches("\\d+")) { //if second arg is an integer
            int idx = Integer.parseInt(idxStr) - 1;
            if (idx < taskArrList.size()) {
                Task currTask = taskArrList.get(idx);
                currTask.markDone();
                String[] doneArr = {"Nice! I've marked this task as done:", ""};
                doneArr[1] = "  " + currTask.toString();

                return doneArr;
            } else {
                throw new DukeException("I don't have that entry in the list!");
            }
        } else {
            throw new DukeException("You didn't tell me which entry to mark as done!");
        }
    }

    private ArrayList<Task> parseTaskFile(File taskFile) {
        ArrayList<Task> taskArrList = new ArrayList<Task>();

        //message for when data corruption is detected in the file
        String corrupt = "Data file has been corrupted!";

        try {
            Scanner taskScanner = new Scanner(taskFile);
            while (taskScanner.hasNextLine()) {
                String[] taskArr = taskScanner.nextLine().split("\t");
                String taskType = taskArr[0];
                boolean isDone;

                //check if task is done
                String doneStr = taskArr[1].strip();
                if (doneStr == "1") {
                    isDone = true;
                } else if (doneStr == "0") {
                    isDone = false;
                } else {
                    throw new DukeResetException(corrupt);
                }

                //add tasks to taskArrList
                // TODO: look into ways to compact this
                if (taskType == "T") {
                    ToDo currToDo = new ToDo(taskArr[2]);
                    if (isDone) {
                        currToDo.markDone();
                    }
                    taskArrList.add(currToDo);
                } else if (taskType == "E") {
                    Event currEvent = new Event(taskArr[2], taskArr[3]);
                    if (isDone) {
                        currEvent.markDone();
                    }
                    taskArrList.add(currEvent);
                } else if (taskType == "D") {
                    Deadline currDeadline = new Deadline(taskArr[2], taskArr[3]);
                    if (isDone) {
                        currDeadline.markDone();
                    }
                    taskArrList.add(currDeadline);
                } else {
                    throw new DukeResetException(corrupt);
                }
            }
            taskScanner.close();
        } catch (IndexOutOfBoundsException excp) {
            throw new DukeResetException(corrupt);
        } catch (FileNotFoundException excp) {
            throw new DukeFatalException("Unable to find data file, try opening Duke again?");
        }

        return taskArrList;
    }

    public void close() {
        writeData();
    }

    private void writeData() {

    }

    // TODO: in major need of refactoring, try to remove reflection/generics if possible and use polymorphism instead
    private static <T extends Task> void addTask(ArrayList<Task> taskList, Class<T> taskClass, String inputStr, String keyword, String cmd) {
        String[] addArr = {"Got it, I've added this task:", "", ""};
        String desc;

        // TODO: change constructors to take a string array
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
                    newTask = (T) taskClass.cast(taskConst.newInstance(desc, splitArr[1]));
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

        } catch (DukeException excp) {
            throw excp; //let Duke handle it
        } catch (Exception excp) { //catch exceptions from generics, should only trigger if installation corrupt
            throw new DukeException("Can't create new tasks! Your installation might be corrupt. Try reinstalling?");
        }
    }

    // TODO: move this functionality to the classes
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

