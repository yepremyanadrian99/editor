package main.java.editor.state;

import java.io.File;
import java.io.IOException;
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
                Command.CREATE,
                Command.COPY,
                Command.RENAME
            ),
            defaultCommands.stream()
        ).collect(Collectors.toList());
    }

    @Override
    public File open(String fileName) throws IOException {
        return getEditorInternal().openInternal(fileName);
    }

    @Override
    public File create(String fileName) throws IOException {
        return getEditorInternal().createInternal(fileName);
    }

    @Override
    public void copy(String srcFileName, String destFileName) throws IOException {
        getEditorInternal().copyInternal(srcFileName, destFileName);
    }

    @Override
    public void rename(String fileName, String newFileName) throws IOException {
        getEditorInternal().rename(fileName, newFileName);
    }
}
