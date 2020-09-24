package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.nio.file.Path;

public abstract class Command {

    public abstract void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException;

}
