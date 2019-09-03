package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeFatalException;
import duke.task.Storage;
import duke.command.Ui;

public class Duke {
    private DukeContext ctx;

    private Duke(String filePath) {
        Ui ui = new Ui(); //UI construction is safe, send welcome first
        ui.printWelcome();
        try {
            ctx = new DukeContext(new Storage(filePath), ui);
        } catch (DukeFatalException excp) {
            excp.killProgram(ui);
        }
        ctx.ui.printHello();
    }

    private void run() {
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
