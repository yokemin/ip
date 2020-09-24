package duke.data;

import duke.data.task.Task;

import java.util.ArrayList;

import static duke.Duke.HORIZONTAL_LINE;

public class TaskList {

    private final ArrayList<Task> arrayOfTasks;

    /**
     * Constructor for TaskList
     * Instantiates new <code>ArrayList</code> of <code>Tasks</code>
     */
    public TaskList() {
        arrayOfTasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList
     * Assigns value to attribute arrayOfTasks
     * @param arrayOfTasks ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> arrayOfTasks) {
        this.arrayOfTasks = arrayOfTasks;
    }

    /**
     * Gets ArrayList of Tasks in attribute arrayOfTasks
     * @return arrayOfTasks ArrayList of Tasks
     */
    public ArrayList<Task> getArrayOfTasks() {
        return arrayOfTasks;
    }

    /**
     * Gets size of ArrayList arrayOfTasks
     * @return integer number of tasks in arrayOfTasks
     */
    public int getNoOfTasks() {
        return arrayOfTasks.size();
    }

    /**
     * Adds Task object into arrayOfTasks
     * @param task Task object to be added
     */
    public void addTask(Task task) {
        arrayOfTasks.add(task);
        printTaskAdded(task);
    }

    /**
     * Returns Task object given its index
     * @param index index of target Task object
     * @return task Task object with of specified index
     */
    public Task getTaskFromIndex(int index) {
        return arrayOfTasks.get(index);
    }

    private void printTaskAdded(Task taskAdded) {
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + taskAdded);
        System.out.println("Now you have " + getNoOfTasks() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * prints items on current arrayOfTasks
     */
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

    /**
     * marks tasks as done
     * @param taskNo task number of task to be marked done
     */
    public void markTaskAsDone(int taskNo) {
        if (taskNo > getNoOfTasks()) {
            System.out.println("You only have " + getNoOfTasks() + " task(s)!");
            System.out.println(HORIZONTAL_LINE);
        } else {
            getTaskFromIndex(taskNo - 1).markAsDone();
            printTaskDone(taskNo);
        }
    }

    private void printTaskDone(int taskNo) {
        System.out.println("Nice! I have marked this task as done:" + getTaskFromIndex(taskNo - 1));
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Delete tasks
     * @param taskNo task number of task to be deleted
     */
    public void deleteTask(int taskNo) {
        if (taskNo > getNoOfTasks()) {
            System.out.println("You only have " + getNoOfTasks() + " task(s)!");
            System.out.println(HORIZONTAL_LINE);
        } else {
            printTaskDeleted(taskNo);
            arrayOfTasks.remove(taskNo - 1);
        }
    }

    private void printTaskDeleted(int taskNo) {
        System.out.println("Noted. I've removed this task: " + System.lineSeparator() + getTaskFromIndex(taskNo - 1));
        System.out.println("Now you have " + (getNoOfTasks() - 1) + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Given keyword, search for tasks with descriptions that contains the keyword
     * @param keyword String that is present in the task descriptions
     * @return relatedTasks ArrayList of tasks with descriptions that contains the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> relatedTasks = new ArrayList<>();
        for (Task t : arrayOfTasks) {
            if (t.toString().toLowerCase().contains(keyword)) {
                relatedTasks.add(t);
            }
        }
        return relatedTasks;
    }

}
