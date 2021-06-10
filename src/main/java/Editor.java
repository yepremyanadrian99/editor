package main.java;

import java.io.File;
import java.io.IOException;
import java.util.List;

import main.java.enums.Command;
import main.java.state.EditorState;
import main.java.state.FileOpenedEditorState;
import main.java.state.NullEditorState;

public enum Editor {

    INSTANCE;

    private EditorState state = new NullEditorState();
    private File file;

    public List<Command> getAvailableCommands() {
        return this.state.getAvailableCommands();
    }

    public void create(String fileName) throws IOException {
        this.file = this.state.create(fileName);
        this.state = new FileOpenedEditorState();
    }

    public void open(String fileName) throws IOException {
        this.file = this.state.open(fileName);
        this.state = new FileOpenedEditorState();
    }

    public void write(String text) throws IOException {
        this.state.write(this.file, text);
    }

    public String read() throws IOException {
        return this.state.read(this.file);
    }

    public void close() {
        this.file = null;
        this.state = new NullEditorState();
    }

    public void delete() {
        this.state.delete(this.file);
        this.file = null;
        this.state = new NullEditorState();
    }
}
