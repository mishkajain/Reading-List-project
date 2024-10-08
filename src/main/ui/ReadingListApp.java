// Based on the supplied Teller example for CPSC 210 :
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

package ui;

import model.Book;
import model.BookList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
* Representing the Reading List App which you interact with from the console
*/

public class ReadingListApp {
    private static final String JSON_STORE = "./data/readingList.json";
    private Scanner input;
    private BookList listOfBooks;
    private Book newBook;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public ReadingListApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runReadingList();
    }

    private void runReadingList() {
        boolean exit = false;
        String command;
        input = new Scanner(System.in);
        System.out.println("\nWelcome to your Reading List!");

        init();

        while (!exit) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("6")) {
                exit = true;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Goodbye! Come back soon :)");
    }

    // EFFECTS: Displays a menu of options to the user
    private void displayMenu() {
        System.out.println("Select from:");
        System.out.println("    1. -> View reading list");
        System.out.println("    2. -> Add a book to your reading list");
        System.out.println("    3. -> Remove a book from your reading list");
        System.out.println("    4. -> Save reading list to file");
        System.out.println("    5. -> Load reading list from file");
        System.out.println("    6. -> Quit ReadingList\n");
    }

    private void processCommand(String command) {
        if (command.equals("1")) {
            doViewList();
        } else if (command.equals("2")) {
            doAddBookToList();
        } else if (command.equals("3")) {
            doRemoveBookFromList(Integer.valueOf(command));
        } else if (command.equals("4")) {
            saveReadingList();
        } else if (command.equals("5")) {
            loadReadingList();
        } else {
            System.out.println("Selection not valid...\nPlease try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: initialises a new empty book list
    private void init() {
        listOfBooks = new BookList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: Prints the current reading list
    private void doViewList() {
        displayCurrentBookList();
    }

    // MODIFIES: this
    // EFFECTS: Creates a book and adds it to the current reading list
    public void doAddBookToList() {
        String title;
        String author;
        Integer pages;
        Integer status;
        Integer rating;

        System.out.println("Please enter the following details:");
        System.out.print("Title: ");
        title = input.next();
        System.out.print("Author: ");
        author = input.next();
        System.out.print("Number of pages: ");
        pages = input.nextInt();
        System.out.print("Status: ");
        status = input.nextInt();
        System.out.print("Rating: ");
        rating = input.nextInt();

        newBook = new Book(title, author, pages, status, rating);
        if (listOfBooks.contains(newBook)) {
            System.out.println("\nTHIS BOOK IS ALREADY IN YOUR READING LIST!\n");
        } else {
            listOfBooks.addBook(newBook);
            System.out.println("\nBOOK HAS BEEN ADDED SUCCESSFULLY!\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the chosen book from the list of books
    public void doRemoveBookFromList(Integer command) {
        displayCurrentBookList();
        System.out.println("TYPE THE INDEX NUMBER OF THE BOOK YOU WISH TO REMOVE: ");
        command = input.nextInt() - 1;
        Book removingBook = listOfBooks.get(command);
        listOfBooks.removeBook(removingBook);
        System.out.println("BOOK HAS BEEN REMOVED SUCCESSFULLY\n");
    }

    private void saveReadingList() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfBooks);
            jsonWriter.close();
            System.out.println("Saved Reading List to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    private void loadReadingList() {
        try {
            listOfBooks = jsonReader.read();
            System.out.println("Loaded Reading List from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // EFFECTS: prints out all the books in the current list of books
    private void displayCurrentBookList() {
        if (listOfBooks.size() == 0) {
            System.out.println("YOUR READING LIST IS EMPTY");
        } else {
            System.out.println("\nREADING LIST:\n\n" + listOfBooks.getListOfBooks());
        }
    }
}