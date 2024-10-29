import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaskManager {
    private JFrame frame;
    private JTextField taskInput;
    private JTextArea taskDisplay;
    private ArrayList<String> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();

        frame = new JFrame("Task Manager");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // starts in center of screen
        frame.setResizable(false);  // stops resizing
        taskInput = new JTextField();
        JButton addBtn = new JButton("Add Task");
        JButton removeBtn = new JButton("Remove Task");
        taskDisplay = new JTextArea();
        taskDisplay.setEditable(false);
        JPanel inputs = new JPanel();
        inputs.setLayout(new GridLayout(1, 2));
        inputs.add(taskInput);
        inputs.add(addBtn);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 1));
        buttonPanel.add(removeBtn);
        frame.add(inputs, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(new JScrollPane(taskDisplay), BorderLayout.CENTER);

        addBtn.addActionListener(e -> addTask());
        removeBtn.addActionListener(e -> removeTask());

        frame.setVisible(true);
    }
    private void addTask() {
        String task = taskInput.getText();
        if (!task.isEmpty()) {
            tasks.add(task);
            taskInput.setText("");
            updateList();
        }
    }
    private void removeTask() {
        if (!tasks.isEmpty()) {
            String[] taskArray = tasks.toArray(new String[0]);
            String taskToRemove = (String) JOptionPane.showInputDialog(
                    frame,"Select the task to delete:", "Remove Task", JOptionPane.PLAIN_MESSAGE, null, taskArray, taskArray[0]
            );
            if (taskToRemove != null) {
                tasks.remove(taskToRemove);
                updateList();
            }
        }
    }
    private void updateList() {
        taskDisplay.setText("");
        for (int i = 0; i < tasks.size(); i++) {
            taskDisplay.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
    }
    public static void main(String[] args) {
        new TaskManager();
    }
}
