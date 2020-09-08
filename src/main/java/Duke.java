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
    public static Task[] arrayOfTasks = new Task[100]; // Assume no more than 100 tasks
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
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        // if-else loop for different inputs from user
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        handleErrorUserInputs(userInput);
        // Clean text input, handle errors
        while (invalidInput) {
            userInput = scan.nextLine();
            handleErrorUserInputs(userInput);
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
            userInput = scan.nextLine();
            handleErrorUserInputs(userInput);
            while (invalidInput) {
                userInput = scan.nextLine();
                handleErrorUserInputs(userInput);
            }
        }

    }


    public static void addTaskToArray(String userInput) {
        if (userInput.contains(KEYWORD_DEADLINE)) {
            addDeadlineToArray(userInput);
        } else if (userInput.contains(KEYWORD_EVENT)) {
            addEventToArray(userInput);
        } else if (userInput.contains(KEYWORD_TODO)){
            addTodoToArray(userInput);
        }
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + arrayOfTasks[taskCount]);
        System.out.println("Now you have " + (taskCount+1) + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void addTodoToArray(String userInput) {
        int descriptionIndex = userInput.indexOf("todo") + ADD_INDEX_TO_TODO;
        arrayOfTasks[taskCount] = new Todo(userInput.substring(descriptionIndex));
    }

    public static void addEventToArray(String userInput) {
        int descriptionIndex = userInput.indexOf("event") + ADD_INDEX_TO_EVENT;
        int byIndex = userInput.indexOf("/at");
        arrayOfTasks[taskCount] = new Event(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
    }

    public static void addDeadlineToArray(String userInput) {
        int descriptionIndex = userInput.indexOf("deadline") + ADD_INDEX_TO_DEADLINE;
        int byIndex = userInput.indexOf("/by");
        arrayOfTasks[taskCount] = new Deadline(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
    }

    // marks tasks as done
    public static void markTaskAsDone(String userInput) {
        userInput = userInput.replaceAll("[^0-9]", "");
        int taskNo = Integer.parseInt(userInput);
        // handle error where task no is out of range
        if (taskNo > taskCount) {
            System.out.println("Task number is out of range.");
            System.out.println(HORIZONTAL_LINE);
        } else {
            arrayOfTasks[taskNo - 1].markAsDone();
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(arrayOfTasks[taskNo-1]);
            System.out.println(HORIZONTAL_LINE);
        }
    }

    // prints items on current arrayOfTasks
    public static void viewTasks(Task[] arrayOfTasks) {
        // handle error case where no tasks in array
        if (taskCount == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= taskCount; i++) {
                System.out.println(i + ". " + arrayOfTasks[i-1]);
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

    public static void handleErrorUserInputs(String userInput) {
        userInput = userInput.toLowerCase().trim();

        // check if todo description is empty
        if (userInput.contains(KEYWORD_TODO)) {
            if (userInput.substring(ADD_INDEX_TO_TODO - 1).trim().isEmpty()) {
                invalidInput = true;
                System.out.println("☹ OOPS!!! The description of a " + KEYWORD_TODO + " cannot be empty.");
                System.out.println(HORIZONTAL_LINE);
            } else {
                invalidInput = false;
            }
            // check if event description is empty
        } else if (userInput.contains(KEYWORD_EVENT)) {
            if (userInput.substring(ADD_INDEX_TO_EVENT - 1).trim().isEmpty()
                    || !(userInput.contains("/at"))
                    || userInput.substring(userInput.indexOf("/at") + 3).trim().isEmpty()) {
                invalidInput = true;
                System.out.println("☹ OOPS!!! The description of a " + KEYWORD_EVENT + " cannot be empty or is incomplete (/at).");
                System.out.println(HORIZONTAL_LINE);
            } else {
                invalidInput = false;
            }
            // check if deadline description is empty
        } else if (userInput.contains(KEYWORD_DEADLINE)) {
            if (userInput.substring(ADD_INDEX_TO_DEADLINE - 1).trim().isEmpty()
                    || !(userInput.contains("/by"))
                    || userInput.substring(userInput.indexOf("/by") + 3).trim().isEmpty() ) {
                invalidInput = true;
                System.out.println("☹ OOPS!!! The description of a " + KEYWORD_DEADLINE + " cannot be empty or is incomplete (/by).");
                System.out.println(HORIZONTAL_LINE);
            } else {
                invalidInput = false;
            }
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
}
