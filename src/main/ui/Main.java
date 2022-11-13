package ui;

import javax.swing.*;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) {
        new GUI();
        try {
            new ReadingListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
