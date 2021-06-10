package main.java.state;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.enums.Command;

public class NullEditorState extends EditorState {

    @Override
    public List<Command> getAvailableCommands() {
        List<Command> defaultCommands = super.getAvailableCommands();
        return Stream.concat(
            Stream.of(
                Command.OPEN,
                Command.CREATE
            ),
            defaultCommands.stream()
        ).collect(Collectors.toList());
    }

    @Override
    public File create(String fileName) throws IOException {
        File file = new File(fileName);
        boolean isCreated = file.createNewFile();
        if (!isCreated) {
            throw new FileAlreadyExistsException(fileName);
        }
        return file;
    }

    @Override
    public File open(String fileName) throws IOException {
        File file = new File(fileName);
        boolean exists = file.exists();
        if (!exists) {
            throw new FileNotFoundException(fileName);
        }
        return file;
    }
}
