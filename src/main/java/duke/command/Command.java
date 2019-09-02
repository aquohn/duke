package duke.command;

import duke.DukeContext;
import duke.exception.DukeException;

public abstract class Command {
    public abstract void execute(DukeContext ctx) throws DukeException;
    public void parse(String inputStr) throws DukeException {}
}
