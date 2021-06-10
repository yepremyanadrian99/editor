package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Scanner;

import main.java.enums.Command;

public class Controller {

    private static final String SAVE_TEXT = ":save";
    private static final String CLOSE_TEXT = ":close";

    private boolean isExitCommand = false;

    private final Scanner scanner = new Scanner(System.in);

    private final Editor editor = Editor.INSTANCE;

    public void start() throws IOException {
        while (!this.isExitCommand) {
            printAvailableCommands();
            executeCommand();
            System.out.println();
        }
    }

    private void printAvailableCommands() {
        List<Command> commands = this.editor.getAvailableCommands();
        for (int i = 0; i < commands.size(); ++i) {
            System.out.printf("%d.%s ", i, commands.get(i).getValue());
        }
        System.out.println();
    }

    private void executeCommand() throws IOException {
        int commandIndex = this.scanner.nextInt();
        this.scanner.nextLine(); // skip the leftover line.
        List<Command> availableCommands = this.editor.getAvailableCommands();
        Command command = availableCommands.get(commandIndex);
        try {
            switchCommand(command);
        } catch (FileAlreadyExistsException e) {
            System.out.printf("File with name: %s already exists\n", e.getFile());
        } catch (FileNotFoundException e) {
            System.out.printf("File with name: %s not found\n", e.getMessage());
        }
    }

    private void switchCommand(Command command) throws IOException {
        switch (command) {
            case OPEN:
                System.out.print("Enter filename: ");
                this.editor.open(this.scanner.next());
                break;
            case CREATE:
                System.out.print("Enter filename: ");
                this.editor.create(this.scanner.next());
                break;
            case READ:
                System.out.println("File content starts here");
                System.out.println(this.editor.read());
                System.out.println("File content ends here");
                break;
            case WRITE:
                System.out.println("Enter your text");
                System.out.printf("For saving write %s\n", SAVE_TEXT);
                System.out.printf("For closing without saving write %s\n", CLOSE_TEXT);
                StringBuilder builder = new StringBuilder();
                while (this.scanner.hasNextLine()) {
                    String text = this.scanner.nextLine();
                    if (SAVE_TEXT.equals(text)) {
                        builder.delete(builder.lastIndexOf("\n"), builder.length());
                        this.editor.write(builder.toString());
                        System.out.println("Changes are saved.");
                        break;
                    } else if (CLOSE_TEXT.equals(text)) {
                        System.out.println("Closing without saving.");
                        break;
                    }
                    builder.append(text)
                        .append("\n");
                }
                break;
            case CLOSE:
                this.editor.close();
                break;
            case DELETE:
                this.editor.delete();
                break;
            case EXIT:
                this.isExitCommand = true;
                break;
        }
    }
}
