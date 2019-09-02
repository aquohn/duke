package duke.command;

import duke.DukeContext;
import duke.exception.DukeException;

public class DoneCommand extends ArgCommand {

    @Override
    public void execute(DukeContext ctx) throws DukeException {
        ctx.taskList.markDone(arg);
    }
}
