package main.java.editor.state;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import main.java.enums.Command;

public abstract class EditorState {

    public List<Command> getAvailableCommands() {
        return List.of(Command.EXIT);
    }

    public File open(String fileName) throws IOException {
        throw new UnsupportedOperationException();
    }

    public File create(String fileName) throws IOException {
        throw new UnsupportedOperationException();
    }

    public void copy(String srcFileName, String destFileName) throws IOException {
        throw new UnsupportedOperationException();
    }

    public void rename(String fileName, String newFileName) throws IOException {
        throw new UnsupportedOperationException();
    }

    public void write(String fileName, String message) throws IOException {
        throw new UnsupportedOperationException();
    }

    public byte[] read(String fileName) throws IOException {
        throw new UnsupportedOperationException();
    }

    public void close() {
        throw new UnsupportedOperationException();
    }

    public void delete(String fileName) throws IOException {
        throw new UnsupportedOperationException();
    }

    protected EditorInternal getEditorInternal() {
        return EditorInternal.INSTANCE;
    }

    protected enum EditorInternal {

        INSTANCE;

        protected File openInternal(String fileName) throws FileNotFoundException {
            File file = new File(fileName);
            boolean exists = file.exists();
            if (!exists) {
                throw new FileNotFoundException(fileName);
            }
            return file;
        }

        protected File createInternal(String fileName) throws IOException {
            File file = new File(fileName);
            if (file.exists()) {
                throw new FileAlreadyExistsException(file.getName());
            }
            file.createNewFile();
            return file;
        }

        protected void copyInternal(String srcFileName, String destFileName) throws IOException {
            byte[] srcBytes = readInternal(srcFileName);
            createInternal(destFileName);
            writeInternal(destFileName, srcBytes);
        }

        protected void rename(String fileName, String newFileName) throws IOException {
            File oldFile = openInternal(fileName);
            File newFile = createInternal(newFileName);
            oldFile.renameTo(newFile);
        }

        protected byte[] readInternal(String fileName) throws IOException {
            File file = openInternal(fileName);
            try (FileInputStream input = new FileInputStream(file)) {
                int length = (int) file.length();
                if (length <= 0) {
                    return new byte[0];
                }
                byte[] bytes = new byte[length];
                input.read(bytes);
                return bytes;
            }
        }

        protected void writeInternal(String fileName, byte[] bytes) throws IOException {
            File file = openInternal(fileName);
            try (FileOutputStream output = new FileOutputStream(file)) {
                output.write(bytes);
            }
        }

        protected void deleteInternal(String fileName) throws IOException {
            File file = openInternal(fileName);
            file.delete();
        }
    }
}
