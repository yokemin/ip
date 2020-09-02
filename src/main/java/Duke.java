import java.util.Scanner;

public class Duke {

    public static String horizontalLine = "____________________________________________________________";
    public static Task[] arrayOfTasks = new Task[100]; // Assume no more than 100 tasks
    public static int itemCount = 0;

    public static void addTaskToArray(String userInput, int itemCount) {
        if (userInput.contains("/by")) {
            addDeadlineToArray(userInput, itemCount);
        }
        else if (userInput.contains("/at")) {
            addEventToArray(userInput, itemCount);
        }
        else if (userInput.contains("todo")){
            addTodoToArray(userInput, itemCount);
        }
        else {
            arrayOfTasks[itemCount] = new Task(userInput);
        }
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + arrayOfTasks[itemCount]);
        System.out.println("Now you have " + (itemCount+1) + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    public static void addTodoToArray(String userInput, int itemCount) {
        int descriptionIndex = userInput.indexOf("todo") + 5;
        arrayOfTasks[itemCount] = new Todo(userInput.substring(descriptionIndex));
    }

    public static void addEventToArray(String userInput, int itemCount) {
        int descriptionIndex = userInput.indexOf("event") + 6;
        int byIndex = userInput.indexOf("/at");
        arrayOfTasks[itemCount] = new Event(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
    }

    public static void addDeadlineToArray(String userInput, int itemCount) {
        int descriptionIndex = userInput.indexOf("deadline") + 9;
        int byIndex = userInput.indexOf("/by");
        arrayOfTasks[itemCount] = new Deadline(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
    }

    // marks tasks as done
    public static void markTaskAsDone(String userInput) {
        userInput = userInput.replaceAll("[^0-9]", "");
        int taskNo = Integer.parseInt(userInput);
        arrayOfTasks[taskNo - 1].markAsDone();
        System.out.println("Nice! I have marked this task as done:");
        System.out.println(arrayOfTasks[taskNo-1]);
        System.out.println(horizontalLine);
    }

    // prints items on current arrayOfTasks
    public static void viewTasks(Task[] arrayOfTasks, int itemCount) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= itemCount; i++) {
            System.out.println(i + ". " + arrayOfTasks[i-1]);
        }
        System.out.println(horizontalLine);
    }

    // ends program when reader says 'bye'
    public static int sayBye(String byeInput) {
        if (byeInput.toLowerCase().contains("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontalLine);
            return 1;
        }
        else {
            return 0;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greet user
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        // if-else loop for different inputs from user
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        // Clean text input
        userInput = userInput.toLowerCase().trim();

        while (sayBye(userInput) == 0) {
            if (userInput.contains("list")) {
                viewTasks(arrayOfTasks, itemCount);
            }
            else if (userInput.contains("done")) {
                markTaskAsDone(userInput);
            }
            else {
                addTaskToArray(userInput, itemCount);
                itemCount++;
            }
            userInput = scan.nextLine();
        }

    }
}
