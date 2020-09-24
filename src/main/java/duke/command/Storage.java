package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String UPDATE_FILE_ERROR = "Something went wrong when updating file.";
    private static final String LOAD_FILE_ERROR = "Something went wrong when loading file.";
    private static final String NO_FILE_ERROR = "File not found, new duke.txt file will be created.";
    public static String fileName;

    public Storage(String filePath) {
        fileName = filePath;
    }

    public ArrayList<Task> load() throws DukeException {

        ArrayList<Task> arrayOfTasks = new ArrayList<>();

        Scanner s;
        try {
            s = new Scanner(Path.of(fileName));
        } catch (NoSuchFileException e) {
            throw new DukeException(NO_FILE_ERROR);
        } catch (IOException e) {
            throw new DukeException(LOAD_FILE_ERROR);
        }
        while (s.hasNext()) {
            String fileInput = s.nextLine();
            String taskIcon = fileInput.substring(0, 3);
            addTaskFromFile(fileInput, taskIcon, arrayOfTasks);
        }

        return arrayOfTasks;
    }

    // Update file according to changes to list of tasks
    public static void updateFile(TaskList tasks, Path filePath) throws DukeException {
        try {
            FileWriter fw = new FileWriter(String.valueOf(filePath));
            for (Task t : tasks.getArrayOfTasks()) {
                fw.write(t.toString() + System.lineSeparator());
            }
            fw.write("There are a total of " + tasks.getNoOfTasks() + " tasks in the list.");
            fw.close();
        } catch (IOException e) {
            throw new DukeException(UPDATE_FILE_ERROR);
        }
    }

    public static void addTaskFromFile(String fileInput, String taskIcon, ArrayList<Task> arrayOfTasks) {
        switch (taskIcon) {
        case "[T]":
            addTodoFromFile(fileInput, arrayOfTasks);
            break;
        case "[E]":
            addEventFromFile(fileInput, arrayOfTasks);
            break;
        case "[D]":
            addDeadlineFromFile(fileInput, arrayOfTasks);
            break;
        default:
            break;
        }
    }

    private static void addDeadlineFromFile(String fileInput, ArrayList<Task> arrayOfTasks) {
        int byIndex = fileInput.indexOf("(by:");
        Deadline deadlineAdded = new Deadline(fileInput.substring(7, byIndex), fileInput.substring(byIndex + 5, fileInput.indexOf(")")));
        arrayOfTasks.add(deadlineAdded);
        if (fileInput.contains("\u2713")) {
            arrayOfTasks.get(arrayOfTasks.size() - 1).markAsDone();
        }
    }

    private static void addEventFromFile(String fileInput, ArrayList<Task> arrayOfTasks) {
        int byIndex = fileInput.indexOf("(at:");
        Event eventAdded = new Event(fileInput.substring(7, byIndex), fileInput.substring(byIndex + 5, fileInput.indexOf(")")));
        arrayOfTasks.add(eventAdded);
        if (fileInput.contains("\u2713")) {
            arrayOfTasks.get(arrayOfTasks.size() - 1).markAsDone();
        }
    }

    private static void addTodoFromFile(String fileInput, ArrayList<Task> arrayOfTasks) {
        Todo todoAdded = new Todo(fileInput.substring(7));
        arrayOfTasks.add(todoAdded);
        if (fileInput.contains("\u2713")) {
            arrayOfTasks.get(arrayOfTasks.size() - 1).markAsDone();
        }
    }

}
