package duke.data.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description, null, null);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
