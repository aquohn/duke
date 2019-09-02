package duke.command;

import duke.DukeContext;
import duke.exception.DukeException;

public class ListCommand extends Command {

    @Override
    public void execute(DukeContext ctx) throws DukeException {
        ctx.ui.print(String.join(System.lineSeparator(), ctx.taskList.listTasks()));
    }
}
