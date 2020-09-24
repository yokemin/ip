package duke.parser;


import duke.command.*;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


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
    public static final int ADD_INDEX_TO_TODO = 5;
    public static final int ADD_INDEX_TO_EVENT = 6;
    public static final int ADD_INDEX_TO_DEADLINE = 9;
    public static final String TASK_NUM_INPUT_ERROR = "Invalid Input! Input format should have an integer!";
    public static final String TASK_NUM_ZERO_ERROR = "Invalid Input! Integer cannot be 0!";
    public static final String UNKNOWN_COMMAND_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String DATETIME_FORMAT_ERROR = "Date and Time format should be \"YYYY-MM-DD HR:MIN\" (Time is optional)";
    public static final String INPUT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String INPUT_TIME_FORMAT = "HH:mm";
    public static final int LENGTH_OF_DATE = 10;
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
            throw new DukeException(UNKNOWN_COMMAND_ERROR);
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

    public static Command parseCommand(String userInput) throws DukeException {
        if (userInput.contains(KEYWORD_LIST)) {
            return new ListCommand();
        } else if (userInput.contains(KEYWORD_DONE)) {
            return new DoneCommand();
        } else if (userInput.contains(KEYWORD_DELETE)) {
            return new DeleteCommand();
        } else if (userInput.contains(KEYWORD_BYE)) {
            return new ByeCommand();
        } else if (!userInput.contains(KEYWORD_BYE)) {
            return new AddCommand();
        } else {
            throw new DukeException("Command Error!");
        }
    }

    public static Task getTask(String userInput) throws DukeException {
        Task taskToAdd = null;
        if (userInput.contains(KEYWORD_DEADLINE)) {
            taskToAdd = getDeadline(userInput);
        } else if (userInput.contains(KEYWORD_EVENT)) {
            taskToAdd = getEvent(userInput);
        } else if (userInput.contains(KEYWORD_TODO)){
            taskToAdd = getTodo(userInput);
        }
        return taskToAdd;
    }

    private static Todo getTodo(String userInput) {
        int descriptionIndex = userInput.indexOf("todo") + ADD_INDEX_TO_TODO;
        // Create Todo
        return new Todo(userInput.substring(descriptionIndex));
    }

    private static Event getEvent(String userInput) {
        int descriptionIndex = userInput.indexOf("event") + ADD_INDEX_TO_EVENT;
        int byIndex = userInput.indexOf("/at");
        // Create Event
        return new Event(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
    }

    private static Deadline getDeadline(String userInput) throws DukeException {
        int descriptionIndex = userInput.indexOf("deadline") + ADD_INDEX_TO_DEADLINE;
        int byIndex = userInput.indexOf("/by");
        // Create Deadline
        String byDescription = userInput.substring(byIndex + 4);
        LocalDate date = parseDate(byDescription);
        LocalTime time = parseTime(byDescription);
        try {
            return new Deadline(userInput.substring(descriptionIndex, byIndex-1), date, time);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(DEADLINE_ERROR);
        }
    }

    public static int getTaskNo(String userInput) throws DukeException {
        userInput = userInput.replaceAll("[^0-9]", "");
        int taskNo;
        try {
            taskNo = Integer.parseInt(userInput);
            if (taskNo == 0) {
                throw new DukeException(TASK_NUM_ZERO_ERROR);
            }
        } catch (NumberFormatException e) {
            throw new DukeException(TASK_NUM_INPUT_ERROR);
        }
        return taskNo;
    }

    public static LocalDate parseDate(String description) throws DukeException{
        //create dates from strings
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
        try {
            description = description.substring(0, LENGTH_OF_DATE);
            date = LocalDate.parse(description, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException(DATETIME_FORMAT_ERROR);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(DATETIME_FORMAT_ERROR);
        }
        return date;
    }

    public static LocalTime parseTime(String description) throws DukeException{
        //create time from strings
        LocalTime time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_TIME_FORMAT);
        try {
            int timeIndex = description.indexOf(" ");
            description = description.substring(timeIndex + 1);
            time = LocalTime.parse(description, formatter);
        } catch (DateTimeParseException e) {
            time = null;;
        } catch (StringIndexOutOfBoundsException e) {
            time = null;
        }
        return time;
    }

}
