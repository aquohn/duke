package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeFatalException;
import duke.exception.DukeResetException;

import java.util.ArrayList;

public class TaskList {

    // TSV files will have one entry per line, tabs disallowed in input

    private ArrayList<Task> taskArrList;

    public TaskList(Storage storage) throws DukeResetException, DukeFatalException {
        taskArrList = storage.parseTaskFile();
    }

    public TaskList() {
        taskArrList = new ArrayList<Task>();
    }

    public String[] listTasks() throws DukeException {
        int taskCount = taskArrList.size();
        if (taskCount == 0) {
            throw new DukeException("You don't have any tasks yet!");
        }
        String[] taskArr = new String[taskCount];
        for (int i = 0; i < taskCount; ++i) {
            Task currTask = taskArrList.get(i);
            taskArr[i] = (i + 1) + "." + currTask.toString();
        }
        return taskArr;
    }

    public String getFileStr() {
        StringBuilder fileStrBuilder = new StringBuilder();
        for (int i = 0; i < taskArrList.size(); ++i) {
            fileStrBuilder.append(taskArrList.get(i).toData()).append(System.lineSeparator());
        }
        return fileStrBuilder.toString();
    }

    public String markDone(String idxStr) throws DukeException {
        if (idxStr.matches("\\d+")) { //if second arg is an integer
            int idx = Integer.parseInt(idxStr) - 1;
            if (idx < taskArrList.size()) {
                Task currTask = taskArrList.get(idx);
                currTask.markDone();
                return currTask.toString();
            } else {
                throw new DukeException("I don't have that entry in the list!");
            }
        } else {
            throw new DukeException("You need to tell me what the number of the entry is!");
        }
    }

    public String addTask(Task newTask) {
            String addStr = "Got it, I've added this task:" + System.lineSeparator()
                    + "  " + newTask.toString() + System.lineSeparator();
            taskArrList.add(newTask);
            int taskCount = taskArrList.size();
            String taskCountStr = taskCount + ((taskCount == 1) ? " task" : " tasks");
            addStr += "Now you have " + taskCountStr + " in the list.";
            return addStr;
    }

    public String find(String searchTerm) {
        int i = 1;
        StringBuilder searchBuilder = new StringBuilder();
        searchBuilder.append("Here are the matching tasks in your list:");
        for (Task task : taskArrList) {
            if (task.getName().contains(searchTerm)) {
               searchBuilder.append(System.lineSeparator()).append(i + ".").append(task.toString());
               ++i;
            }
        }

        if (i == 1) {
            return "Can't find any matching tasks!";
        } else {
            return searchBuilder.toString();
        }
    }
}
