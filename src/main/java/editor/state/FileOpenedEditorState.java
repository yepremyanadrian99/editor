package main.java.editor.state;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.enums.Command;

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
    public byte[] read(String fileName) throws IOException {
        return getEditorInternal().readInternal(fileName);
    }

    @Override
    public void write(String fileName, String message) throws IOException {
        getEditorInternal().writeInternal(fileName, message.getBytes());
    }

    @Override
    public void close() {
    }

    @Override
    public void delete(String fileName) throws IOException {
        getEditorInternal().deleteInternal(fileName);
    }
}
