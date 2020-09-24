package duke.command;

import java.nio.file.Path;

import static duke.command.Parser.*;
import static duke.command.Parser.KEYWORD_BYE;

public abstract class Command {

    public abstract void executeCommand(String userInput, TaskList tasks, Path filePath) throws DukeException;

}
