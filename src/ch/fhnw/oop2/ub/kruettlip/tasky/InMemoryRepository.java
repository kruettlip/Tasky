package ch.fhnw.oop2.ub.kruettlip.tasky;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryRepository implements IRepository<Task> {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getAll() {
        return tasks;
    }

    public Task get(UUID id) {
        for (Task task : tasks) {
            if (task.getId().equals(id))
                return task;
        }
        return null;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void update(Task task) {
        int index = tasks.indexOf(task);
        if (index >= 0) {
            tasks.set(index, task);
        }
    }

    public void delete(Task task) {
        tasks.remove(task);
    }    
}
