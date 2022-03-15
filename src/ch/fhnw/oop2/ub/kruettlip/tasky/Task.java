package ch.fhnw.oop2.ub.kruettlip.tasky;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Task {
    private UUID id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskState state;
    private static String dateFormat = "yyyy-MM-dd";
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

    public Task(String title, String description, String dueDateString, String stateString) {
        this.setTitle(title);
        this.setDescription(description);
        this.setDueDate(dueDateString);
        this.setState(stateString);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDueDateString() {
        return dueDate.format(Task.dateFormatter);
    }

    public void setDueDate(String dueDateString) {
        this.dueDate = LocalDate.parse(dueDateString, Task.dateFormatter);
    }

    public TaskState getState() {
        return state;
    }

    public void setState(String stateString) {
        this.state = TaskState.fromString(stateString);
    }

    public static String getDateFormat() {
        return dateFormat;
    }

    @Override
    public String toString() {
        return String.format("Task [%s] title=%s, desc=%s, dueDate=%s, state=%s", id.toString(), title, description, dateFormatter.format(dueDate), state.name());
    }
}
