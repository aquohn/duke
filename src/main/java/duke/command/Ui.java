package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeResetException;

import java.util.Scanner;

public class Ui {
    private final Parser parser;
    private final Scanner scanIn;

    /**
     * Constructs a new Ui object, with a new Parser and Scanner for System.in
     * @see Parser
     */
    public Ui() {
        parser = new Parser();
        scanIn = new Scanner(System.in);
    }

    /**
     * Print logo and welcome message.
     */
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
    }


    /**
     * Print hello message to indicate that setup is completed and Duke can receive user input.
     */
    public void printHello() {
        print("Hello, I'm Duke!\nWhat can I do for you?");
    }

    /**
     * Get the next line of input from the user.
     * @return The next line of input from the user.
     */
    public String readLine() {
        return scanIn.nextLine();
    }

    /**
     * Sanitise input and use the Parse to extract the requested command, which will be loaded with parameters
     * extracted from the user's arguments.
     * @return The command specified by the user, with arguments parsed.
     * @throws DukeException If Parser fails to find a matching command or the arguments do not meet the command's
     * requirements.
     */
    public Command parseCommand() throws DukeException {
        String inputStr = readLine();
        inputStr = inputStr.replaceAll("\t", "    "); //sanitise input
        return parser.parse(inputStr);
    }

    /**
     * Print a message, indented and bracketed between two lines. Newlines will have indents added after them.
     * @param msg Message to be printed.
     */
    public void print(String msg) {
        String line = "    ________________________________________________________________________________";
        String indentline = System.lineSeparator() + "    ";
        System.out.println(line);
        msg = msg.replaceAll("(\\r\\n|\\n|\\r)", indentline);
        System.out.println("    " + msg);
        System.out.println(line + System.lineSeparator());
    }

    //Ui should not be used any more after this
    /**
     * Close the scanner and print a goodbye message.
     */
    public void closeUi() {
        scanIn.close();
        print("Bye. Hope to see you again soon!");
    }

    //Leaving this here for future expansion (red text, etc.)
    /**
     * Print the error message from an exception.
     * @param excp Exception whose message we want to print.
     */
    public void printError(DukeException excp) {
        print(excp.getMessage());
    }
}
