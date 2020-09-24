package duke.command;


import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.data.task.Task;
import duke.data.TaskList;

import java.nio.file.Path;

public class AddCommand extends Command {

    @Override
    public void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException {
        Task taskToAdd = Parser.getTask(userInput);
        tasks.addTask(taskToAdd);
        Storage.updateFile(tasks, Path.of(Storage.fileName));
    }
}
