package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

import static duke.Duke.HORIZONTAL_LINE;
import static duke.command.Parser.*;

public class TaskList {

    public static final int ADD_INDEX_TO_TODO = 5;
    public static final int ADD_INDEX_TO_EVENT = 6;
    public static final int ADD_INDEX_TO_DEADLINE = 9;
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

    public void executeUserCommand(String userInput) {

        if (userInput.contains(KEYWORD_LIST)) {
            viewTasks();
        } else if (userInput.contains(KEYWORD_DONE)) {
            markTaskAsDone(userInput);
        } else if (userInput.contains(KEYWORD_DELETE)) {
            deleteTask(userInput);
        } else if (!userInput.contains(KEYWORD_BYE)) {
            addTaskToArray(userInput);
        }

    }

    // prints items on current arrayOfTasks
    public void viewTasks() {
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

    private void addTaskToArray(String userInput) {
        Task taskAdded = null;
        if (userInput.contains(KEYWORD_DEADLINE)) {
            taskAdded = addDeadline(userInput);
        } else if (userInput.contains(KEYWORD_EVENT)) {
            taskAdded = addEvent(userInput);
        } else if (userInput.contains(KEYWORD_TODO)){
            taskAdded = addTodo(userInput);
        }
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + taskAdded);
        System.out.println("Now you have " + arrayOfTasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private Todo addTodo(String userInput) {
        int descriptionIndex = userInput.indexOf("todo") + ADD_INDEX_TO_TODO;
        // add Todo to arraylist
        Todo todoAdded = new Todo(userInput.substring(descriptionIndex));
        arrayOfTasks.add(todoAdded);
        return todoAdded;
    }

    private Event addEvent(String userInput) {
        int descriptionIndex = userInput.indexOf("event") + ADD_INDEX_TO_EVENT;
        int byIndex = userInput.indexOf("/at");
        // add Event to arraylist
        Event eventAdded = new Event(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
        arrayOfTasks.add(eventAdded);
        return eventAdded;
    }

    private Deadline addDeadline(String userInput) {
        int descriptionIndex = userInput.indexOf("deadline") + ADD_INDEX_TO_DEADLINE;
        int byIndex = userInput.indexOf("/by");
        // add Deadline to arraylist
        Deadline deadlineAdded = new Deadline(userInput.substring(descriptionIndex, byIndex-1), userInput.substring(byIndex + 4));
        arrayOfTasks.add(deadlineAdded);
        return deadlineAdded;
    }

    // marks tasks as done
    private void markTaskAsDone(String userInput) {
        userInput = userInput.replaceAll("[^0-9]", "");
        try {
            int taskNo = Integer.parseInt(userInput);
            // handle error where task no is out of range
            if (taskNo > arrayOfTasks.size()) {
                System.out.println("You only have " + arrayOfTasks.size() + " task(s)!");
            } else {
                arrayOfTasks.get(taskNo - 1).markAsDone();
                System.out.println("Nice! I have marked this task as done:" + arrayOfTasks.get(taskNo - 1));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input! Input format should have an integer e.g. done 2");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Input! Integer cannot be 0!");
        } finally {
            System.out.println(HORIZONTAL_LINE);
        }
    }

    // Delete tasks
    private void deleteTask(String userInput) {
        userInput = userInput.replaceAll("[^0-9]", "");
        try {
            int taskNo = Integer.parseInt(userInput);
            // handle error where task no is out of range
            if (taskNo > arrayOfTasks.size()) {
                System.out.println("You only have " + arrayOfTasks.size() + " task(s)!");
            } else {
                System.out.println("Noted. I've removed this task: " + System.lineSeparator() + arrayOfTasks.get(taskNo - 1));
                arrayOfTasks.remove(taskNo - 1);
                System.out.println("Now you have " + arrayOfTasks.size() + " tasks in the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input! Input format should have an integer e.g. done 2");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Input! Integer cannot be 0!");
        } finally {
            System.out.println(HORIZONTAL_LINE);
        }
    }

}
