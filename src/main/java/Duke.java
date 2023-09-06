import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;

public class Duke {

    private static final List<Task> tasks = new ArrayList<Task>();

    public static void listTasks() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t\t%d. %s\n", i + 1, tasks.get(i));
        }
    }

    public static void greetings() {
        String logo = "                                                  \n" +
                "                                                     \n" +
                "         $$\\  $$$$$$\\   $$$$$$\\   $$$$$$\\  $$\\   $$\\ \n" +
                "         \\__|$$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |  $$ |\n" +
                "         $$\\ $$$$$$$$ |$$ |  \\__|$$ |  \\__|$$ |  $$ |\n" +
                "         $$ |$$   ____|$$ |      $$ |      $$ |  $$ |\n" +
                "         $$ |\\$$$$$$$\\ $$ |      $$ |      \\$$$$$$$ |\n" +
                "         $$ | \\_______|\\__|      \\__|       \\____$$ |\n" +
                "   $$\\   $$ |                              $$\\   $$ |\n" +
                "   \\$$$$$$  |                              \\$$$$$$  |\n" +
                "    \\______/                                \\______/ \n" +
                "   \n";
        System.out.println(logo);
        System.out.println("\tHi I'm Jerry !");
        System.out.println("\tWhat can I do for you ?\n");
    }


    public static void confirmTaskAdded(Task newTask) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newTask.toString());
        System.out.printf("\tNow you have %d tasks in the list.\n", tasks.size());
    }

    public static void handleMarkTask(String userInput) throws IndexOutOfBoundsException {
        int index = Integer.parseInt(userInput.split(" ")[1]);
        if (index < 0 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Please enter a valid index.");
        }
        tasks.get(index - 1).markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.printf("\t\t [%s] %s\n", tasks.get(index - 1).getStatusIcon(), tasks.get(index - 1).getDescription());
    }

    public static void handleUnmarkTask(String userInput) throws IndexOutOfBoundsException {
        int index = Integer.parseInt(userInput.split(" ")[1]);
        if (index < 0 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Please enter a valid index.");
        }
        tasks.get(index - 1).markAsUndone();
        System.out.println("\tOk! I've marked this task as not done yet:");
        System.out.printf("\t\t [%s] %s\n", tasks.get(index - 1).getStatusIcon(), tasks.get(index - 1).getDescription());
    }

    public static void main(String[] args) {
        greetings();
        Scanner input = new Scanner(System.in);
        String userInput;
        String firstWord;

        do {
            userInput = input.nextLine();
            firstWord = userInput.split(" ")[0];
            switch (firstWord) {
            case "list":
                listTasks();
                break;
            case "mark":
                try {
                    handleMarkTask(userInput);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    handleUnmarkTask(userInput);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "todo":
                try {
                    Todo newTask = Todo.fromString(userInput);
                    tasks.add(newTask);
                    confirmTaskAdded(newTask);
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "deadline":
                try {
                    Deadline newTask = Deadline.fromString(userInput);
                    tasks.add(newTask);
                    confirmTaskAdded(newTask);
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "event":
                try {
                    Event newTask = Event.fromString(userInput);
                    tasks.add(newTask);
                    confirmTaskAdded(newTask);
                } catch (ParseException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default: {
                System.out.println("\t'No command recognized");
                break;
            }
            }
        }
        while (!userInput.equals("bye"));
        System.out.println("\tBye hope to see you again\n");
    }
}
