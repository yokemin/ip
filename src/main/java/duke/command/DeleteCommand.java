package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

import java.nio.file.Path;

public class DeleteCommand extends Command {

    @Override
    public void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException {
        int taskNo = Parser.getTaskNo(userInput);
        tasks.deleteTask(taskNo);
        Storage.updateFile(tasks, Path.of(Storage.fileName));
    }
}
