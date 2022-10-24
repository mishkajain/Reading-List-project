// Based on the supplied Teller example for CPSC 210 :
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

package ui;

import model.Book;
import model.ReadingList;

import java.util.Scanner;

public class ReadingListApp {
    private ReadingList listOfBooks;
    private Book newBook;
    private Book book;
    private Scanner input;
    private Scanner removeInput;

    public ReadingListApp() {
        runReadingList();
    }

    private void runReadingList() {
        boolean exit = false;
        String command;
        Integer removecCommand;
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

    private void processCommand(String command) {
        if (command.equals("1")) {
            doViewList();
        } else if (command.equals("2")) {
            doAddBookToList();
        } else if (command.equals("3")) {
            doRemoveBookFromList(Integer.valueOf(command));
        } else {
            System.out.println("Selection not valid...\nPlease try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: initialises a new empty book list
    private void init() {
        listOfBooks = new ReadingList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

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

    // EFFECTS: Prints the current reading list
    private void doViewList() {
        displayCurrentBookList();
    }

    // MODIFIES: this
    // EFFECTS: Creates a book and adds it to the current reading list
    public void doAddBookToList() {
        String name;
        String author;
        Integer pages;
        Integer status;
        Integer rating;

        System.out.println("Please enter the following details:");
        System.out.print("Name: ");
        name = input.next();
        System.out.print("Author: ");
        author = input.next();
        System.out.print("Number of pages: ");
        pages = input.nextInt();
        System.out.print("Status: ");
        status = input.nextInt();
        System.out.print("Rating: ");
        rating = input.nextInt();

        newBook = new Book(name, author, pages, status, rating);
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

    // EFFECTS: prints out all the books in the current list of books
    private void displayCurrentBookList() {
        if (listOfBooks.size() == 0) {
            System.out.println("YOUR READING LIST IS EMPTY");
        } else {
            System.out.println("\nREADING LIST:\n" + listOfBooks.getListOfBooks());
        }
    }
}