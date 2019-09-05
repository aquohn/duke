package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeFatalException;
import duke.task.Storage;
import duke.command.Ui;

public class Duke {
    private DukeContext ctx; //holds the tasklist, ui and storage classes

    private Duke(String filePath) {
        Ui ui = new Ui(); //UI construction is safe, send welcome first
        ui.printWelcome();
        try {
            //construct tasklist from storage and ui
            ctx = new DukeContext(new Storage(filePath), ui);
        } catch (DukeFatalException excp) {
            excp.killProgram(ui); //standard exit on fatal exception
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
