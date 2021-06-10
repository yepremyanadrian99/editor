package main.java.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Scanner;

import main.java.editor.Editor;
import main.java.enums.Command;

public class Controller {

    private static final String SAVE_TEXT = ":save";
    private static final String CLOSE_TEXT = ":close";

    private final Scanner scanner = new Scanner(System.in);
    private final Editor editor = Editor.INSTANCE;

    private boolean isExitCommand = false;

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
                open();
                break;
            case CREATE:
                create();
                break;
            case COPY:
                copy();
                break;
            case RENAME:
                rename();
                break;
            case READ:
                read();
                break;
            case WRITE:
                write();
                break;
            case CLOSE:
                close();
                break;
            case DELETE:
                delete();
                break;
            case EXIT:
                exit();
                break;
        }
    }

    private void open() throws IOException {
        System.out.print("Enter file name: ");
        this.editor.open(this.scanner.nextLine());
        System.out.println("Opening file.");
    }

    private void create() throws IOException {
        System.out.print("Enter file name: ");
        this.editor.create(this.scanner.nextLine());
        System.out.println("File created successfully.");
    }

    private void copy() throws IOException {
        System.out.print("Enter src file name: ");
        String srcFileName = this.scanner.nextLine();
        System.out.print("Enter dest file name: ");
        String destFileName = this.scanner.nextLine();
        this.editor.copy(srcFileName, destFileName);
        System.out.println("File copied successfully.");
    }

    private void rename() throws IOException {
        System.out.print("Enter file name: ");
        String fileName = this.scanner.nextLine();
        System.out.printf("Rename existing file: %s to: ", fileName);
        String newFileName = this.scanner.nextLine();
        this.editor.rename(fileName, newFileName);
        System.out.println("File renamed successfully.");
    }

    private void read() throws IOException {
        System.out.println("------- File content starts here ------- ");
        System.out.println(this.editor.read());
        System.out.println("-------  File content ends here  ------- ");
    }

    private void write() throws IOException {
        System.out.println("Enter your text");
        System.out.printf("For saving write %s\n", SAVE_TEXT);
        System.out.printf("For closing without saving write %s\n", CLOSE_TEXT);
        StringBuilder builder = new StringBuilder();
        while (true) {
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
    }

    private void close() {
        this.editor.close();
        System.out.println("Closing file.");
    }

    private void delete() throws IOException {
        this.editor.delete();
        System.out.println("File deleted successfully.");
    }

    private void exit() {
        this.isExitCommand = true;
    }
}
