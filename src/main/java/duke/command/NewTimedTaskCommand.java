package duke.command;

import duke.exception.DukeException;
import duke.task.TimedTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public abstract class NewTimedTaskCommand extends MultiArgCommand {

    protected LocalDateTime datetime;

    @Override
    public void parse(String inputStr) throws DukeException {
        super.parse(inputStr);
        if (argv[0].length() == 0) {
            argv = null;
            throw new DukeException("The task description cannot be empty!");
        }

        try {
            datetime = LocalDateTime.parse(argv[1], TimedTask.getDataFormatter());
        } catch (
                DateTimeParseException excp) {
            throw new DukeException("Date and time must be given as, e.g. "
                    + LocalDateTime.now().format(TimedTask.getDataFormatter()) + ".");
        }
    }
}
