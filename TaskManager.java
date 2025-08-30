import java.io.*;
import java.util.*;

public class TaskManager {
    private static final String FILE_NAME = "tasks.txt";

    // Utility to load tasks
    private static List<String> loadTasks() {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            // file may not exist yet → ignore
        }
        return tasks;
    }

    // Utility to save tasks
    private static void saveTasks(List<String> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String t : tasks) {
                bw.write(t);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // ====== Features (to be implemented by each member) ======
    public static void addTask(String task) {
        throw new UnsupportedOperationException("addTask not implemented yet");
    }

    public static void showTasks() {
        throw new UnsupportedOperationException("showTasks not implemented yet");
    }

    public static void deleteTask(int index) {
        throw new UnsupportedOperationException("deleteTask not implemented yet");
    }

    // ====== Main entry ======
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("  java TaskManager add <task>");
            System.out.println("  java TaskManager show");
            System.out.println("  java TaskManager delete <index>");
            return;
        }

        String cmd = args[0];
        switch (cmd) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Please provide a task description.");
                } else {
                    addTask(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
                }
                break;
            case "show":
                showTasks();
                break;
            case "delete":
                if (args.length < 2) {
                    System.out.println("Please provide index to delete.");
                } else {
                    try {
                        int idx = Integer.parseInt(args[1]);
                        deleteTask(idx);
                    } catch (NumberFormatException e) {
                        System.out.println("Index must be a number.");
                    }
                }
                break;
            default:
                System.out.println("Unknown command: " + cmd);
        }
    }
}
