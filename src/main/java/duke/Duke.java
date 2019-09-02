package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeFatalException;
import duke.task.Storage;
import duke.command.Ui;

public class Duke {
    private DukeContext ctx;

    public Duke(String filePath) {
        try {
            ctx = new DukeContext(new Storage(filePath), new Ui());
        } catch (DukeFatalException excp) {
            excp.killProgram(new Ui());
        }
    }

    public void run() {
        while (true) {
            try {
                Command c = ctx.ui.parseCommand();
                c.execute(ctx);
            } catch (DukeException excp) {
                ctx.ui.printError(excp);
            }
        }
    }

    public static void main(String[] argv) {
        new Duke("data/tasks.tsv").run();
    }
}
