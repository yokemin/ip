package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.parser.Parser;

import java.nio.file.Path;
import java.util.ArrayList;

import static duke.Duke.HORIZONTAL_LINE;

public class FindCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException {
        String keyword = Parser.getKeyword(userInput);
        ArrayList<Task> relatedTasks = tasks.findTasks(keyword);
        printRelatedTasks(relatedTasks);
    }

    private void printRelatedTasks(ArrayList<Task> relatedTasks) {
        System.out.println("Here are the matching tasks in your list:");
        int i = 1;
        for (Task t : relatedTasks) {
            System.out.println(i + ": " + t.toString());
            i++;
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
