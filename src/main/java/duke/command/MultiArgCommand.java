package duke.command;

import duke.exception.DukeException;

// For commands with multiple arguments split by a common delimiter

public abstract class MultiArgCommand extends ArgCommand {
    String[] argv;
    int argc;
    String delim;
    String invalidArgMsg;

    public void parse(String inputStr) throws DukeException {
        super.parse(inputStr);

        //remove excess whitespace between commands
        //primarily to prevent space-delimited commands from throwing errors if there are two spaces
        argv = arg.split("\\s*" + delim + "\\s*");
        if (argv.length != argc) {
            argv = null;
            throw new DukeException(invalidArgMsg);
        }
        for (int i = 0; i < argv.length; ++i) {
            argv[i] = argv[i].strip();
        }
    }
}
