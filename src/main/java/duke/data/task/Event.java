package duke.data.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description, null, null);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
