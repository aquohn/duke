package duke.command;

import duke.DukeContext;
import duke.exception.DukeException;

public abstract class ArgCommand extends Command {
    protected String arg;
    protected String emptyArgMsg;

    public void parse(String inputStr) throws DukeException {
        arg = inputStr;
        if (arg.length() == 0) {
            throw new DukeException(emptyArgMsg);
        }
    }

    @Override
    public void execute(DukeContext ctx) throws DukeException {
        if (arg == null) {
            throw new DukeException("Command needs to parse argument first!");
        }
    }
}
