package aquohn.duke.command;

import aquohn.duke.exception.DukeException;

public class Ui {

    private static String line = "    ________________________________________________________________________________";
    private static String indent = "    ";
    private static final String KW_AT = "/at";
    private static final String KW_BY = "/by";
    private Parser parser;

    public Ui() {
        parser = new Parser();
    }

    public void printWelcome() {
        //print welcome message
        String logoSpace = "                                  ";
        String titleSpace = "                                        ";
        String logo = logoSpace + " ____        _        \n"
                + logoSpace + "|  _ \\ _   _| | _____ \n"
                + logoSpace + "| | | | | | | |/ / _ \\\n"
                + logoSpace + "| |_| | |_| |   <  __/\n"
                + logoSpace + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\n" + titleSpace + "Hello from\n" + logo);
        print("Hello, I'm Duke!\nWhat can I do for you?")
    }

    public void printGoodbye() {
        print("Bye. Hope to see you again soon!");
    }

    public void printError(DukeException excp) {
        print(excp.getMessage());
    }

    private void print(String msg) {
        System.out.println(line);
        msg.replaceAll("(\\r\\n|\\r|\\n)", indent);
        System.out.println(msg);
        System.out.println(line + System.lineSeparator());
    }
}
