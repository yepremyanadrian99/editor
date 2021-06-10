package main.java.enums;

public enum Command {

    OPEN("open"),
    CREATE("create"),
    COPY("copy"),
    RENAME("rename"),
    READ("read"),
    WRITE("write"),
    CLOSE("close"),
    DELETE("delete"),
    EXIT("exit");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
