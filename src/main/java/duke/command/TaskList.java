package duke.command;

import duke.task.Task;

import java.util.ArrayList;

import static duke.Duke.HORIZONTAL_LINE;

public class TaskList {

    private final ArrayList<Task> arrayOfTasks;

    public TaskList() {
        arrayOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arrayOfTasks) {
        this.arrayOfTasks = arrayOfTasks;
    }

    public ArrayList<Task> getArrayOfTasks() {
        return arrayOfTasks;
    }

    public int getNoOfTasks() {
        return arrayOfTasks.size();
    }

    public void addTask(Task task) {
        arrayOfTasks.add(task);
        printTaskAdded(task);
    }

    public Task getTaskFromIndex(int index) {
        return arrayOfTasks.get(index);
    }

    private void printTaskAdded(Task taskAdded) {
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + taskAdded);
        System.out.println("Now you have " + getNoOfTasks() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    // prints items on current arrayOfTasks
    public void viewTasks() {
        // handle error case where no tasks in array
        if (getNoOfTasks() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= getNoOfTasks(); i++) {
                System.out.println(i + ". " + getArrayOfTasks().get(i - 1));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    // marks tasks as done
    public void markTaskAsDone(int taskNo) {
        if (taskNo > getNoOfTasks()) {
            System.out.println("You only have " + getNoOfTasks() + " task(s)!");
            System.out.println(HORIZONTAL_LINE);
        } else {
            getTaskFromIndex(taskNo - 1).markAsDone();
            printTaskDone(taskNo);
        }
    }

    public void printTaskDone(int taskNo) {
        System.out.println("Nice! I have marked this task as done:" + getTaskFromIndex(taskNo - 1));
        System.out.println(HORIZONTAL_LINE);
    }

    // Delete tasks
    public void deleteTask(int taskNo) {
        if (taskNo > arrayOfTasks.size()) {
            System.out.println("You only have " + arrayOfTasks.size() + " task(s)!");
            System.out.println(HORIZONTAL_LINE);
        } else {
            arrayOfTasks.remove(taskNo - 1);
            printTaskDeleted(taskNo);
        }
    }

    public void printTaskDeleted(int taskNo) {
        System.out.println("Noted. I've removed this task: " + System.lineSeparator() + getTaskFromIndex(taskNo - 1));
        System.out.println("Now you have " + arrayOfTasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

}
