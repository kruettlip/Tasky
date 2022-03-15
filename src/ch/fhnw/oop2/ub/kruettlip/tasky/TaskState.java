package ch.fhnw.oop2.ub.kruettlip.tasky;

import java.util.Arrays;
import java.util.List;

public enum TaskState {
    Todo,
    Doing,
    Done;

    public static List<String> names() {
        return Arrays.asList(TaskState.values()).stream().map(s -> s.name()).toList();
    }

    public static TaskState fromString(String stateString) {
        for (TaskState state : TaskState.values()) {
            if (state.name().toLowerCase().equals(stateString.toLowerCase())) {
                return state;
            }
        }
        return null;
    }
}
