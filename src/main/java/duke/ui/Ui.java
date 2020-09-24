package duke.ui;


import duke.exception.DukeException;
import duke.parser.Parser;

import java.util.Scanner;

import static duke.Duke.HORIZONTAL_LINE;
import static duke.parser.Parser.KEYWORD_BYE;

public class Ui {
    private static final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

    public void greetUser(){
        System.out.println("Hello from\n" + logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    // ends program when reader says 'bye'
    public boolean sayBye(String byeInput) {
        if (byeInput.toLowerCase().contains(KEYWORD_BYE)) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL_LINE);
            return true;
        } else {
            return false;
        }
    }

    public static String getUserInput() throws DukeException {

        // Get first user input
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        Parser.handleErrorUserInputs(userInput);

        // Subsequent user inputs
        while (Parser.invalidInput) {
            userInput = scan.nextLine();
            try {
                Parser.handleErrorUserInputs(userInput);
            } catch (DukeException e) {
                Parser.invalidInput = true;
            }
        }

        return userInput;
    }

    public Ui() {
    }

    public void showLoadingError(String message) {
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }


}
