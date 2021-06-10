package main.java;

import java.io.IOException;

import main.java.controller.Controller;

public class Main {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        controller.start();
    }
}
