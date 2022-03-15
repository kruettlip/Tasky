package ch.fhnw.oop2.ub.kruettlip.tasky.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import org.junit.Test;

import ch.fhnw.oop2.ub.kruettlip.tasky.IRepository;
import ch.fhnw.oop2.ub.kruettlip.tasky.InMemoryRepository;
import ch.fhnw.oop2.ub.kruettlip.tasky.Task;
import ch.fhnw.oop2.ub.kruettlip.tasky.TaskState;

public class RepositoryTests {

    @Test
    public void getAllTasks() {
        IRepository<Task> repository = new InMemoryRepository();
        Task task1 = new Task("TestTask1", "This is just a test.", "2022-03-14", "Todo");
        Task task2 = new Task("TestTask2", "This is just a test.", "2022-03-14", "Todo");
        repository.add(task1);
        repository.add(task2);
        
        Collection<Task> receivedTasks = repository.getAll();

        assertEquals(receivedTasks.size(), 2);
    }

    @Test
    public void getTask_Existing_Returns() {
        IRepository<Task> repository = new InMemoryRepository();
        Task createdTask = new Task("TestTask", "This is just a test.", "2022-03-14", "Todo");
        repository.add(createdTask);
        
        Task receivedTask = repository.get(createdTask.getId());

        assertEquals(receivedTask, createdTask);
    }

    @Test
    public void getTask_NotExisting_ReturnsNull() {
        IRepository<Task> repository = new InMemoryRepository();
        
        Task receivedTask = repository.get(UUID.randomUUID());

        assertEquals(receivedTask, null);
    }

    @Test
    public void addTask() {
        IRepository<Task> repository = new InMemoryRepository();
        Task newTask = new Task("TestTask", "This is just a test.", "2022-03-14", "Todo");

        assertEquals(repository.getAll().size(), 0);

        repository.add(newTask);

        assertEquals(repository.getAll().size(), 1);
    }

    @Test
    public void modifyTask() {
        IRepository<Task> repository = new InMemoryRepository();
        Task task = new Task("TestTask", "This is just a test.", "2022-03-14", "Todo");
        String newTitle = "NewTitle";
        String newDescription = "NewDescription";
        LocalDate newDate = LocalDate.of(2022, 3, 14);
        String newState = TaskState.Done.name();
        repository.add(task);
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        task.setDueDate("2022-03-14");
        task.setState(newState);

        repository.update(task);

        assertEquals(repository.get(task.getId()).getTitle(), newTitle);
        assertEquals(repository.get(task.getId()).getDescription(), newDescription);
        assertEquals(repository.get(task.getId()).getDueDate(), newDate);
        assertEquals(repository.get(task.getId()).getState().name(), newState);
    }

    @Test
    public void removeTask() {
        IRepository<Task> repository = new InMemoryRepository();
        Task task = new Task("TestTask", "This is just a test.", "2022-03-14", "Todo");
        repository.add(task);
        assertEquals(repository.getAll().size(), 1);

        repository.delete(task);

        assertEquals(repository.getAll().size(), 0);
    }
}
