package main.java.state;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.enums.Command;
import main.java.enums.Constants;

public class FileOpenedEditorState extends EditorState {

    @Override
    public List<Command> getAvailableCommands() {
        List<Command> defaultCommands = super.getAvailableCommands();
        return Stream.concat(
            Stream.of(
                Command.READ,
                Command.WRITE,
                Command.CLOSE,
                Command.DELETE
            ),
            defaultCommands.stream()
        ).collect(Collectors.toList());
    }

    @Override
    public void write(File file, String message) throws IOException {
        try (FileOutputStream output = new FileOutputStream(file)) {
            output.write(message.getBytes());
        }
    }

    @Override
    public String read(File file) throws IOException {
        try (FileInputStream input = new FileInputStream(file)) {
            int length = (int) file.length();
            if (length <= 0) {
                return Constants.EMPTY_STRING;
            }
            byte[] bytes = new byte[length];
            input.read(bytes);
            return new String(bytes);
        }
    }

    @Override
    public void close() {
    }

    @Override
    public void delete(File file) {
        file.delete();
    }
}
