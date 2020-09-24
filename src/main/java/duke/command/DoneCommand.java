package duke.command;

import java.nio.file.Path;

public class DoneCommand extends Command {

    @Override
    public void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException {
        int taskNo = Parser.getTaskNo(userInput);
        tasks.markTaskAsDone(taskNo);
        Storage.updateFile(tasks, Path.of(Storage.fileName));
    }
}
