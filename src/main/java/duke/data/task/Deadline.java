package duke.data.task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description, date, time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString() + getTimeString() + ")";
    }
}