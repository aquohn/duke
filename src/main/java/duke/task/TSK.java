package duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public enum TSK {
    TODO("T") {
       public Task getTask(String[] taskArr) throws IndexOutOfBoundsException {
           return new ToDoTask(taskArr[2]);
       }
    },
    EVENT("E") {
        public Task getTask(String[] taskArr) throws DateTimeParseException, IndexOutOfBoundsException {
            LocalDateTime datetime = LocalDateTime.parse(taskArr[3], TimedTask.getDataFormatter());
            return new EventTask(taskArr[2], datetime);
        }
    },
    DLINE("D") {
        public Task getTask(String[] taskArr) throws DateTimeParseException, IndexOutOfBoundsException {
            LocalDateTime datetime = LocalDateTime.parse(taskArr[3], TimedTask.getDataFormatter());
            return new DeadlineTask(taskArr[2], datetime);
        }
    };

    private final String taskChar;

    TSK (final String _taskChar) {
        taskChar = _taskChar;
    }

    @Override
    public String toString() {
        return taskChar;
    }

    public abstract Task getTask(String[] taskArr);
}
