package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

import java.nio.file.Path;

public class ListCommand extends Command {

    @Override
    public void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException {
        tasks.viewTasks();
        Storage.updateFile(tasks, Path.of(Storage.fileName));
    }
}
