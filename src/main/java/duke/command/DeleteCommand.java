package duke.command;

import java.nio.file.Path;

public class DeleteCommand extends Command {

    @Override
    public void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException {
        int taskNo = Parser.getDeleteTaskNo(userInput);
        tasks.deleteTask(taskNo);
        Storage.updateFile(tasks, Path.of(Storage.fileName));
    }
}
