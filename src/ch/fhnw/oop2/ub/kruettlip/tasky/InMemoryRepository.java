package ch.fhnw.oop2.ub.kruettlip.tasky;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemoryRepository implements IRepository<Task> {
    private Map<UUID, Task> tasks = new HashMap<>();

    public List<Task> getAll() {
        return tasks.values().stream().collect(toList());
    }

    public Task get(UUID id) {
        return tasks.get(id);
    }

    public void add(Task task) {
        UUID id = UUID.randomUUID();
        task.setId(id);
        tasks.put(id, task);
    }

    public void update(Task task) {
        tasks.put(task.getId(), task);
    }

    public void delete(Task task) {
        tasks.remove(task.getId());
    }    
}
