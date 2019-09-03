package duke.command;

import duke.DukeContext;
import duke.exception.DukeException;

public class FindCommand extends ArgCommand {

    @Override
    public void execute(DukeContext ctx) {
       ctx.ui.print(ctx.taskList.find(arg));
    }
}
