package ch.fhnw.oop2.ub.kruettlip.tasky;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Tasky {
    private IRepository<Task> repository = new InMemoryRepository();
    private Task selectedTask = null;
    private Map<String, Command> commands = new HashMap<>();
    private Map<String, Comparator<Task>> orderingFunctions = new HashMap<>();
    private Scanner scanner;
    private boolean quit = false;

    public Tasky() {
        commands.put("list", this::listTasks);
        commands.put("filter", this::filterTasks);
        commands.put("add", this::createTask);
        commands.put("update", this::modifyTask);
        commands.put("delete", this::deleteTask);
        commands.put("help", this::showHelp);
        commands.put("clear", this::clear);
        commands.put("exit", this::quit);
        orderingFunctions.put("t", (t1, t2) -> t1.getTitle().compareTo(t2.getTitle()));
        orderingFunctions.put("d", (t1, t2) -> t1.getDescription().compareTo(t2.getDescription()));
        orderingFunctions.put("z", (t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate()));
        orderingFunctions.put("s", (t1, t2) -> t1.getState().compareTo(t2.getState()));
    }

    public static void main(String[] args) {
        new Tasky().loop();
    }

    private void loop() {
        printHeader();
        scanner = new Scanner(System.in);
        while (!quit) {
            System.out.print("> ");
            String input = scanner.nextLine().toLowerCase();
            if (commands.keySet().contains(input)) {
                commands.get(input).execute();
            } else {
                System.out.println("- Unsupported command! -");
                showHelp();
            }
        }
    }

    private void printHeader() {
        System.out.println("------------");
        System.out.println("| Tasky CLI |");
        System.out.println("------------");
    }

    private void listTasks() {
        List<Task> tasks = repository.getAll();
        if (tasks.isEmpty()){
            System.out.println(" - No tasks added yet -");
            System.out.println();
            return;
        }
        sortTasks(tasks);
        printTasks(tasks);
    }

    private void printTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.println();
    }

    private void sortTasks(List<Task> tasks) {
        System.out.print("Order by (t=title, d=description, z=date, s=state): ");
        String orderBy = scanner.nextLine();
        System.out.print("Reverse order? [y/N] ");
        String reverseOrderString = scanner.nextLine();
        boolean reverseOrder = !reverseOrderString.isEmpty() || reverseOrderString.toLowerCase().equals("y");
        if (orderingFunctions.containsKey(orderBy)){
            Comparator<Task> orderingFunction = reverseOrder ? orderingFunctions.get(orderBy).reversed()
                : orderingFunctions.get(orderBy);
            tasks.sort(orderingFunction);
        }
    }

    private void filterTasks() {
        System.out.print("Filter by state. ("+ String.join("|", TaskState.names()) +"): ");
        String filterCriteria = scanner.nextLine();
        List<Task> tasks = repository.getAll().stream()
        .filter(t -> t.getState().name().toLowerCase().equals(filterCriteria.toLowerCase()))
        .collect(Collectors.toList());
        printTasks(tasks);
    }

    private void createTask() {
        Task newTask = new Task(requestTitle(), requestDescription(), requestDueDate(), requestState());
        repository.add(newTask);
        System.out.println("Added: " + newTask.toString());
        System.out.println();
    }

    private void modifyTask() {
        selectTask();
        selectedTask.setTitle(requestTitle(selectedTask.getTitle()));
        selectedTask.setDescription(requestDescription(selectedTask.getDescription()));
        selectedTask.setDueDate(requestDueDate(selectedTask.getDueDateString()));
        selectedTask.setState(requestState(selectedTask.getState().name()));
        repository.update(selectedTask);
        System.out.println("Updated: " + selectedTask.toString());
        System.out.println();
    }

    private void selectTask() {
        List<Task> tasks = repository.getAll();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(String.format("(%d) ", i));
            System.out.println(tasks.get(i));
        }
        System.out.print("Select task: ");
        int taskIndex = Integer.parseInt(scanner.nextLine());
        selectedTask = tasks.get(taskIndex);
    }

    private void deleteTask() {
        selectTask();
        repository.delete(selectedTask);
        System.out.println("Deleted: " + selectedTask.toString());
        System.out.println();
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        printHeader();
    }

    private void quit() {
        quit = true;
    }

    private void showHelp() {
        List<String> commandStrings = commands.keySet().stream().collect(Collectors.toList());
        Collections.sort(commandStrings);
        System.out.println("Supported commands: " + String.join(" | ", commandStrings));
        System.out.println();
    }

    private String requestTitle() {
        System.out.print("Title: ");
        return scanner.nextLine();
    }

    private String requestTitle(String currentTitle) {
        System.out.print(String.format("Title [%s]: ", currentTitle));
        String input = scanner.nextLine();
        return input.isEmpty() ? currentTitle : input;
    }

    private String requestDescription() {
        System.out.print("Description: ");
        return scanner.nextLine();
    }

    private String requestDescription(String currentDescription) {
        System.out.print(String.format("Description [%s]: ", currentDescription));
        String input = scanner.nextLine();
        return input.isEmpty() ? currentDescription : input;
    }

    private String requestDueDate() {
        System.out.print("Due Date (YYYY-MM-DD): ");
        return scanner.nextLine();
    }

    private String requestDueDate(String currentDueDate) {
        System.out.print(String.format("Due Date (YYYY-MM-DD) [%s]: ", currentDueDate));
        String input = scanner.nextLine();
        return input.isEmpty() ? currentDueDate : input;
    }

    private String requestState() {
        System.out.print("State (" + String.join("|", TaskState.names()) + "): ");
        return scanner.nextLine();
    }

    private String requestState(String currentState) {
        System.out.print(String.format("State (" + String.join("|", TaskState.names()) + ") [%s]: ", currentState));
        String input = scanner.nextLine();
        return input.isEmpty() ? currentState : input;
    }
}