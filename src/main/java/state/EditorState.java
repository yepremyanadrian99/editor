package main.java.state;

import java.io.File;
import java.io.IOException;
import java.util.List;

import main.java.enums.Command;

public abstract class EditorState {

    public List<Command> getAvailableCommands() {
        return List.of(Command.EXIT);
    }

    public File create(String fileName) throws IOException {
        throw new UnsupportedOperationException();
    }

    public File open(String fileName) throws IOException {
        throw new UnsupportedOperationException();
    }

    public void write(File file, String message) throws IOException {
        throw new UnsupportedOperationException();
    }

    public String read(File file) throws IOException {
        throw new UnsupportedOperationException();
    }

    public void close() {
        throw new UnsupportedOperationException();
    }

    public void delete(File file) {
        throw new UnsupportedOperationException();
    }
}
