import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class TaskList {

    // TODO: look into throws keyword

    // TSV files will have one entry per line, tabs disallowed in input

    private ArrayList<Task> taskArrList;
    private File taskFile;

    public TaskList(boolean isReset) {
        taskFile = new File("../data/tasks.tsv");
        try {
            if (taskFile.exists()) {
                if (isReset) {
                    taskArrList = new ArrayList<Task>();
                    writeTaskFile();
                } else {
                    taskArrList = parseTaskFile(taskFile);
                }
            } else {
                taskArrList = new ArrayList<Task>();
                taskFile.createNewFile();
            }
        } catch (IOException excp) {
            throw new DukeFatalException("Unable to setup data file, try checking your permissions?");
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
                writeTaskFile();
                String[] doneArr = {"Nice! I've marked this task as done:", ""};
                doneArr[1] = "  " + currTask.toString();
                return doneArr;
            } else {
                throw new DukeException("I don't have that entry in the list!");
            }
        } else {
            throw new DukeException("You need to tell me what the number of the entry is!");
        }
    }

    // TODO: in major need of refactoring, try to remove reflection/generics if possible and use polymorphism instead
    public <T extends Task> String[] addTask(Class<T> taskClass, String inputStr, String keyword) {
        String[] addArr = {"Got it, I've added this task:", "", ""};
        String desc;

        // TODO: change constructors to take a string array
        Class[] oneString = {String.class};
        Class[] twoStrings = {String.class, String.class};
        T newTask;

        try {
            if (keyword != null) { //Task consists of two parts separated by a keyword
                String[] splitArr = inputStr.split(keyword, 2);

                if (splitArr.length < 2) {
                    throw generateInvalidTaskException(taskClass);
                } else {
                    Constructor taskConst = taskClass.getConstructor(twoStrings);
                    desc = splitArr[0];
                    if (desc.length() == 0) {
                        throw new DukeException("The task description cannot be empty!");
                    }
                    newTask = (T) taskClass.cast(taskConst.newInstance(desc, splitArr[1]));
                } 
            } else {
                Constructor taskConst = taskClass.getConstructor(oneString);
                if (inputStr.length() == 0) {
                    throw new DukeException("The task description cannot be empty!");
                }
                newTask = (T) taskClass.cast(taskConst.newInstance(inputStr));
            }
            taskArrList.add(newTask);

            writeTaskFile();
            int taskCount = taskArrList.size();
            addArr[1] = "  " + taskArrList.get(taskCount - 1).toString();
            String taskCountStr = Integer.toString(taskCount) + ((taskCount == 1) ? " task" : " tasks");
            addArr[2] = "Now you have " + taskCountStr + " in the list.";

        } catch (DukeException excp) {
            throw excp; //let Duke handle it
        } catch (Exception excp) { //catch exceptions from generics, should only trigger if installation corrupt
            throw new DukeException("Can't create new tasks! Your installation might be corrupt. Try reinstalling?");
        }
        return addArr;
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

    public void writeTaskFile() { // public because a separate close oepration would just call this
        // TODO: figure out some way of editing that doesn't involve rewriting everything each time
        // Maybe some kind of diff file?

        String taskFileStr = "";
        for (int i = 0; i < taskArrList.size(); ++i) {
            taskFileStr += taskArrList.get(i).toData() + System.lineSeparator();
        }
        try {
            FileWriter taskFileWr = new FileWriter(taskFile);
            taskFileWr.write(taskFileStr);
            taskFileWr.close();
        } catch (IOException excp) {
            throw new DukeFatalException("Unable to write data! Some data may have been lost,");
        }
    }

    // TODO: move this functionality to the classes
    private DukeException generateInvalidTaskException(Class taskClass) {
        if (taskClass == Event.class) {
            return new DukeException("Invalid event! Events must specify when they are /at.");
        } else if (taskClass == Deadline.class) {
            return new DukeException("Invalid deadline! Deadlines must specify when they are due /by.");
        } else {
            return new DukeException("I don't recognise that type of class!");
        }
    }
}
