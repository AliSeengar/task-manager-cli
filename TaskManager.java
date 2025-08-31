import java.io.*;
import java.util.*;

public class TaskManager {
    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java TaskManager <command> [task]");
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Please provide a task description.");
                } else {
                    String task = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    addTask(task);
                }
                break;

            case "show":
                showTasks();
                break;

            //  delete for Sara to add later
            case "delete":
                System.out.println("Delete feature not implemented yet. (Sara’s task)");
                break;

            default:
                System.out.println("Unknown command: " + command);
        }
    }

    //  Ali: Add task
    public static void addTask(String task) {
        List<String> tasks = loadTasks();
        tasks.add(task);
        saveTasks(tasks);
        System.out.println("Added task: " + task);
    }

    //  Ali: Show tasks
    public static void showTasks() {
        List<String> tasks = loadTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    
    private static List<String> loadTasks() {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            // ignore if file not found (first run)
        }
        return tasks;
    }

    private static void saveTasks(List<String> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
