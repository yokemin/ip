package duke;

import duke.command.*;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.data.TaskList;
import duke.ui.Ui;

import java.nio.file.Path;

public class Duke {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        ui.greetUser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            tasks.viewTasks();
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean isBye = false;
        while (!isBye) {
            try {
                String userInput = Ui.getUserInput();
                Command command = Parser.parseCommand(userInput);
                command.executeCommand(userInput, tasks, Path.of(Storage.fileName));
                isBye = ui.sayBye(userInput);
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        new Duke("duke.txt").run();

    }
}
