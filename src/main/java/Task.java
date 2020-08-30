public class Task {
    protected String description;
    protected boolean isDone;

    // Constructors
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Getters
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    // prints items on current arrayOfTasks
    public static void viewTasks(Task[] arrayOfTasks, int itemCount) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i <= itemCount; i++) {
            System.out.println(i + ". " + arrayOfTasks[i-1]);
        }
        System.out.println("Now you have " + itemCount + " tasks on the list.");
        System.out.println(Duke.horizontalLine);
    }
}