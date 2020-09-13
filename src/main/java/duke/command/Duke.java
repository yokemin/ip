package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final int ADD_INDEX_TO_TODO = 5;
    public static final int ADD_INDEX_TO_EVENT = 6;
    public static final int ADD_INDEX_TO_DEADLINE = 9;
    public static final String KEYWORD_TODO = "todo";
    public static final String KEYWORD_EVENT = "event";
    public static final String KEYWORD_DEADLINE = "deadline";
    public static final String KEYWORD_BYE = "bye";
    public static final String KEYWORD_LIST = "list";
    public static final String KEYWORD_DONE = "done";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static boolean invalidInput = false;
    public static final int MAX_NO_OF_TASKS = 100;
    private static ArrayList<Task> arrayOfTasks = new ArrayList<>();
    public static int taskCount = 0;


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greet user
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke.Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        // if-else loop for different inputs from user
        Scanner scan = new Scanner(System.in);
        String userInput;

        userInput = getUserInput(scan);

        // Clean text input, handle errors
        while (invalidInput) {
            userInput = getUserInput(scan);
        }

        while (sayBye(userInput) == 0) {
            if (userInput.contains(KEYWORD_LIST)) {
                viewTasks(arrayOfTasks);
            } else if (userInput.contains(KEYWORD_DONE)) {
                markTaskAsDone(userInput);
            } else {
                addTaskToArray(userInput);
                taskCount++;
            }
            userInput = getUserInput(scan);

            // Clean text input, handle errors
            while (invalidInput) {
                userInput = getUserInput(scan);
            }
        }

    }

    public static String getUserInput(Scanner scan) {
        String userInput;
        userInput = scan.nextLine();
        try {
            handleErrorUserInputs(userInput);
        } catch (TaskException e) {
            invalidInput = true;
            System.out.println("☹ OOPS!!! The description of a " + KEYWORD_TODO + " cannot be empty.");
            System.out.println(HORIZONTAL_LINE);
        } catch (EventException e) {
            invalidInput = true;
            System.out.println("☹ OOPS!!! The description of a " + KEYWORD_EVENT + " cannot be empty or is incomplete (/at).");
            System.out.println(HORIZONTAL_LINE);
        } catch (DeadlineException e) {
            invalidInput = true;
            System.out.println("☹ OOPS!!! The description of a " + KEYWORD_DEADLINE + " cannot be empty or is incomplete (/by).");
            System.out.println(HORIZONTAL_LINE);
        }
        return userInput;
    }


    public static void addTaskToArray(String userInput) {
        Task taskAdded = null;
        if (userInput.contains(KEYWORD_DEADLINE)) {
            taskAdded = addDeadlineToArray(userInput);
        } else if (userInput.contains(KEYWORD_EVENT)) {
            taskAdded = addEventToArray(userInput);
        } else if (userInput.contains(KEYWORD_TODO)){
            taskAdded = addTodoToArray(userInput);
        }
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + taskAdded);
        System.out.println("Now you have " + arrayOfTasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static Todo addTodoToArray(String userInput) {
        int descriptionIndex = userInput.indexOf("todo") + ADD_INDEX_TO_TODO;
        // add Todo to arraylist
        Todo todoAdded = new Todo(userInput.substring(descriptionIndex));
        arrayOfTasks.add(todoAdded);
        return todoAdded;
    }

    public static Event addEventToArray(String userInput) {
        int descriptionIndex = userInput.indexOf("event") + ADD_INDEX_TO_EVENT;
        int byIndex = userInput.indexOf("/at");
        // add Event to arraylist
        Event eventAdded = new Event(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
        arrayOfTasks.add(eventAdded);
        return eventAdded;
    }

    public static Deadline addDeadlineToArray(String userInput) {
        int descriptionIndex = userInput.indexOf("deadline") + ADD_INDEX_TO_DEADLINE;
        int byIndex = userInput.indexOf("/by");
        // add Deadline to arraylist
        Deadline deadlineAdded = new Deadline(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
        arrayOfTasks.add(deadlineAdded);
        return deadlineAdded;
    }

    // marks tasks as done
    public static void markTaskAsDone(String userInput) {
        userInput = userInput.replaceAll("[^0-9]", "");
        try {
            int taskNo = Integer.parseInt(userInput);
            // handle error where task no is out of range
            if (taskNo > taskCount) {
                System.out.println("You only have " + taskCount + " task(s)!");
            } else {
                arrayOfTasks.get(taskNo - 1).markAsDone();
                System.out.println("Nice! I have marked this task as done:" + arrayOfTasks.get(taskNo - 1));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input! Input format should have an integer e.g. done 2");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Input! Integer cannot be 0!");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    // prints items on current arrayOfTasks
    public static void viewTasks(ArrayList<Task> arrayOfTasks) {
        // handle error case where no tasks in array
        if (arrayOfTasks.size() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= arrayOfTasks.size(); i++) {
                System.out.println(i + ". " + arrayOfTasks.get(i - 1));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    // ends program when reader says 'bye'
    public static int sayBye(String byeInput) {
        if (byeInput.toLowerCase().contains(KEYWORD_BYE)) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL_LINE);
            return 1;
        } else {
            return 0;
        }
    }

    public static void handleErrorUserInputs(String userInput) throws TaskException, EventException, DeadlineException {
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
                || userInput.contains(KEYWORD_DONE)) {
            invalidInput = false;
            // incorrect input
        } else {
            invalidInput = true;
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void validateDeadline(String userInput) throws DeadlineException {
        if (userInput.substring(ADD_INDEX_TO_DEADLINE - 1).trim().isEmpty()
                || !(userInput.contains("/by"))
                || userInput.substring(userInput.indexOf("/by") + 3).trim().isEmpty() ) {
            throw new DeadlineException();
        } else {
            invalidInput = false;
        }
    }

    public static void validateEvent(String userInput) throws EventException {
        if (userInput.substring(ADD_INDEX_TO_EVENT - 1).trim().isEmpty()
                || !(userInput.contains("/at"))
                || userInput.substring(userInput.indexOf("/at") + 3).trim().isEmpty()) {
            throw new EventException();
        } else {
            invalidInput = false;
        }
    }

    public static void validateTodo(String userInput) throws TaskException {
        if (userInput.substring(ADD_INDEX_TO_TODO - 1).trim().isEmpty()) {
            throw new TaskException();
        } else {
            invalidInput = false;
        }
    }
}
