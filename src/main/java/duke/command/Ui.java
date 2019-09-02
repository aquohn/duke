package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeFatalException;
import duke.exception.DukeResetException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static String line = "    ________________________________________________________________________________";
    private static String indentline = "    " + System.lineSeparator();
    private static final String KW_AT = "/at";
    private static final String KW_BY = "/by";
    private Parser parser;
    private Scanner scanIn;

    public Ui() {
        parser = new Parser();
        Scanner scanIn = new Scanner(System.in);
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
        print("Hello, I'm Duke!\nWhat can I do for you?");
    }

    public String readLine() {
        return scanIn.nextLine();
    }

    public Command parseCommand() throws DukeException {
        String inputStr = readLine();
        inputStr = inputStr.replaceAll("\t", "    "); //sanitise input
        return parser.parse(inputStr);
    }

    public void print(String msg) {
        System.out.println(line);
        msg = msg.replaceAll("(\\r\\n|\\r|\\n)", indentline);
        System.out.println(msg);
        System.out.println(line + System.lineSeparator());
    }

    public void closeUi() {
        scanIn.close();
        print("Bye. Hope to see you again soon!");
    }

    public void printError(DukeException excp) {
        print(excp.getMessage());
    }

    public void printError(DukeResetException excp) {
        print(excp.getMessage() + System.lineSeparator() + "Do you want to reset your Duke data now,"
                + " to continue using Duke? (y/n)");
    }


}
