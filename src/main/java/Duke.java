import java.util.Scanner;

public class Duke {

    public static String horizontalLine = "____________________________________________________________";
    public static String[] listOfTasks = new String[100]; // Assume no more than 100 tasks
    public static int itemCount = 0;

    // tells reader what was added
    public static void echo(String userInput){
        System.out.println("added: " + userInput);
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

    // prints items on current list
    public static void viewList(String[] listOfTasks, int itemCount) {
        for (int i = 1; i <= itemCount; i++) {
            System.out.println(i + ". " + listOfTasks[i-1]);
        }
        System.out.println(horizontalLine);
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

        // Add, list and exit
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        while (sayBye(userInput) == 0) {
            if (userInput.toLowerCase().contains("list")) {
                viewList(listOfTasks, itemCount);
            }
            else {
                echo(userInput);
                listOfTasks[itemCount] = userInput; // add item to list
                itemCount++;
            }
            userInput = scan.nextLine();
        }

    }
}
