package duke.storage;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String UPDATE_FILE_ERROR = "Something went wrong when updating file.";
    private static final String LOAD_FILE_ERROR = "Something went wrong when loading file.";
    private static final String NO_FILE_ERROR = "File not found, new duke.txt file will be created.";
    public static final String DATE_FORMAT_OF_FILE = "MMM dd yyyy";
    private static final int LENGTH_OF_DATE = 11;
    public static final String TIME_FORMAT_OF_FILE = "HH:mm";
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

    public static void addTaskFromFile(String fileInput, String taskIcon, ArrayList<Task> arrayOfTasks) throws DukeException {
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

    private static void addDeadlineFromFile(String fileInput, ArrayList<Task> arrayOfTasks) throws DukeException {
        int byIndex = fileInput.indexOf("(by:");
        String byDescription = fileInput.substring(byIndex + 5, fileInput.indexOf(")"));
        LocalDate date = formatDate(byDescription);
        LocalTime time = formatTime(byDescription);
        Deadline deadlineAdded = new Deadline(fileInput.substring(7, byIndex), date, time);
        arrayOfTasks.add(deadlineAdded);
        if (fileInput.contains("\u2713")) {
            arrayOfTasks.get(arrayOfTasks.size() - 1).markAsDone();
        }
    }

    private static LocalDate formatDate(String description) {
        LocalDate date;
        description = description.substring(0, LENGTH_OF_DATE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_OF_FILE);
        date = LocalDate.parse(description, formatter);
        return date;
    }

    private static LocalTime formatTime(String description) {
        LocalTime time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT_OF_FILE);
        try {
            description = description.substring(LENGTH_OF_DATE + 1);
            time = LocalTime.parse(description, formatter);
        } catch (StringIndexOutOfBoundsException e) {
            time = null;
        }
        return time;
    }

    private static void addEventFromFile(String fileInput, ArrayList<Task> arrayOfTasks) {
        int byIndex = fileInput.indexOf("(at:");
        Event eventAdded = new Event(fileInput.substring(7, byIndex), fileInput.substring(byIndex + 5, fileInput.indexOf(")")));
        arrayOfTasks.add(eventAdded);
        if (fileInput.contains(Task.TICK_SYMBOL)) {
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
