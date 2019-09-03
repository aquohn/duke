package duke.task;

import duke.command.Ui;
import duke.exception.DukeException;
import duke.exception.DukeFatalException;
import duke.exception.DukeResetException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    // TSV files will have one entry per line, tabs disallowed in input

    private ArrayList<Task> taskArrList;

    public TaskList(Storage storage) throws DukeResetException, DukeFatalException {
        ArrayList<Task> fileTaskArrList = storage.parseTaskFile();
        taskArrList = fileTaskArrList;
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
        StringBuilder taskFileBuilder = new StringBuilder();
        for (int i = 0; i < taskArrList.size(); ++i) {
            taskFileBuilder.append(taskArrList.get(i).toData()).append(System.lineSeparator());
        }
        return taskFileBuilder.toString();
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
                    + newTask.toString() + System.lineSeparator();
            taskArrList.add(newTask);
            int taskCount = taskArrList.size();
            addStr += "  " + taskArrList.get(taskCount - 1).toString() + System.lineSeparator();
            String taskCountStr = taskCount + ((taskCount == 1) ? " task" : " tasks");
            addStr += "Now you have " + taskCountStr + " in the list.";
            return addStr;
    }
}
