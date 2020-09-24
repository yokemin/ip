package duke.command;


import static duke.Duke.HORIZONTAL_LINE;
import static duke.command.TaskList.*;

public class Parser {

    private static final String TODO_ERROR = "☹ OOPS!!! The description of a todo cannot be empty.";
    private static final String EVENT_ERROR = "☹ OOPS!!! The description of an event cannot be empty or is incomplete (/at).";
    private static final String DEADLINE_ERROR = "☹ OOPS!!! The description of a deadline cannot be empty or is incomplete (/by).";
    public static final String KEYWORD_TODO = "todo";
    public static final String KEYWORD_EVENT = "event";
    public static final String KEYWORD_DEADLINE = "deadline";
    public static final String KEYWORD_BYE = "bye";
    public static final String KEYWORD_LIST = "list";
    public static final String KEYWORD_DONE = "done";
    public static final String KEYWORD_DELETE = "delete";
    public static boolean invalidInput = false;


    public static void handleErrorUserInputs(String userInput) throws DukeException {
        userInput = userInput.toLowerCase().trim();

        // check if todo description is empty
        if (userInput.contains(KEYWORD_TODO) && !(userInput.contains(KEYWORD_EVENT)) && !(userInput.contains(KEYWORD_DEADLINE))) {
            validateTodo(userInput);
            // check if event description is empty
        } else if (userInput.contains(KEYWORD_EVENT) && !(userInput.contains(KEYWORD_TODO)) && !(userInput.contains(KEYWORD_DEADLINE))) {
            validateEvent(userInput);
            // check if deadline description is empty
        } else if (userInput.contains(KEYWORD_DEADLINE) && !(userInput.contains(KEYWORD_TODO)) && !(userInput.contains(KEYWORD_EVENT))) {
            validateDeadline(userInput);
            // check if keyword were used in userInput
        } else if (userInput.contains(KEYWORD_LIST)
                || userInput.contains(KEYWORD_BYE)
                || userInput.contains(KEYWORD_DONE)
                || userInput.contains(KEYWORD_DELETE)) {
            invalidInput = false;
            // incorrect input
        } else {
            invalidInput = true;
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(HORIZONTAL_LINE);
        }
    }

    private static void validateDeadline(String userInput) throws DukeException {
        if (userInput.substring(ADD_INDEX_TO_DEADLINE - 1).trim().isEmpty()
                || !(userInput.contains("/by"))
                || userInput.substring(userInput.indexOf("/by") + 3).trim().isEmpty() ) {
            throw new DukeException(DEADLINE_ERROR);
        } else {
            invalidInput = false;
        }
    }

    private static void validateEvent(String userInput) throws DukeException {
        if (userInput.substring(ADD_INDEX_TO_EVENT - 1).trim().isEmpty()
                || !(userInput.contains("/at"))
                || userInput.substring(userInput.indexOf("/at") + 3).trim().isEmpty()) {
            throw new DukeException(EVENT_ERROR);
        } else {
            invalidInput = false;
        }
    }

    private static void validateTodo(String userInput) throws DukeException {
        if (userInput.substring(ADD_INDEX_TO_TODO - 1).trim().isEmpty()) {
            throw new DukeException(TODO_ERROR);
        } else {
            invalidInput = false;
        }
    }

}
