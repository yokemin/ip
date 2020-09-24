package duke.data.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    public static final String TICK_SYMBOL = "\u2713";
    public static final String CROSS_SYMBOL = "\u2718";
    protected String description;
    protected boolean isDone;
    protected LocalDate date;
    protected LocalTime time;

    // Constructor
    public Task(String description, LocalDate date, LocalTime time) {
        this.description = description;
        this.isDone = false;
        this.date = date;
        this.time = time;
    }

    // Getters
    public String getStatusIcon() {
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDateString() {
        return getDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String getTimeString() {
        if (getTime() == null){
            return "";
        } else {
            return " " + getTime().toString();
        }
    }
}