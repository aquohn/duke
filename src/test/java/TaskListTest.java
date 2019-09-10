import duke.exception.DukeException;
import duke.task.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    private TaskList taskList = new TaskList();

    @AfterEach
    public void reset() {
        taskList = new TaskList();
    }

    @Test
    public void taskList_addTasks() {
        ToDoTask todo = new ToDoTask("JUnit tests");
        LocalDateTime t = LocalDateTime.parse("12/09/19 1400", TimedTask.getDataFormatter());
        EventTask event = new EventTask("tutorial", t);
        DeadlineTask deadline = new DeadlineTask("submission", t);

        try {
            assertTrue(taskList.addTask(todo).contains("1 task"));
            assertTrue(taskList.addTask(event).contains("2 tasks"));
            assertTrue(taskList.addTask(deadline).contains("3 tasks"));
        } catch (AssertionError excp) {
            fail("Total number of tasks added is not 3!");
        }

        try {
            String taskListStr = taskList.listTasks();
            String expectedTaskListStr = "1.[T][] JUnit tests"
                    + System.lineSeparator() + "2.[E][?] tutorial (at: Thu, 12 Sep 2019 2:00 PM)"
                    + System.lineSeparator() + "3.[D][?] submission (at: Thu, 12 Sep 2019 2:00 PM)";
            assertEquals(taskListStr, expectedTaskListStr); //catch and explain failure?
        } catch (DukeException excp) {
            fail("No tasks in the list!");
        } catch (AssertionError excp) {

        }
    }

}
