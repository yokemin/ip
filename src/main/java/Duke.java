import java.util.Scanner;

public class Duke {

    public static String horizontalLine = "____________________________________________________________";

    public static void echo(String userInput){
        System.out.println(userInput);
        System.out.println(horizontalLine);
    }

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

        // Level 1. Greet, Echo, Exit
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        do {
            echo(userInput);
            userInput = scan.nextLine();
        } while (sayBye(userInput) == 0);

    }
}
