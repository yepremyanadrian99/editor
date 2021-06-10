package main.java.editor;

import java.io.File;
import java.io.IOException;
import java.util.List;

import main.java.editor.state.EditorState;
import main.java.editor.state.FileOpenedEditorState;
import main.java.editor.state.NullEditorState;
import main.java.enums.Command;

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

    public void copy(String srcFileName, String destFileName) throws IOException {
        this.state.copy(srcFileName, destFileName);
    }

    public void rename(String fileName, String newFileName) throws IOException {
        this.state.rename(fileName, newFileName);
    }

    public void write(String text) throws IOException {
        this.state.write(this.file.getName(), text);
    }

    public String read() throws IOException {
        return new String(this.state.read(this.file.getName()));
    }

    public void close() {
        this.state.close();
        this.file = null;
        this.state = new NullEditorState();
    }

    public void delete() throws IOException {
        this.state.delete(this.file.getName());
        this.file = null;
        this.state = new NullEditorState();
    }
}
