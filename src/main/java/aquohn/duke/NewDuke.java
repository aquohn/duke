package aquohn.duke;

import aquohn.duke.exception.DukeException;
import aquohn.duke.task.Storage;
import aquohn.duke.task.TaskList;
import aquohn.duke.command.Ui;

public class NewDuke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage);
        } catch (DukeException excp) {
            ui.printError(excp);
            tasks = new TaskList();
        }
    }

    public void run() {
        //...
    }

    public static void main(String[] args) {
        new Duke("data/tasks.tsv").run();
    }
}
