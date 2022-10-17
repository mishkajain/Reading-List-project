package ui;

import model.Book;
import model.BookList;

import java.util.Scanner;

public class ReadingListApp {
    private BookList listOfBooks;
    private Book newBook;
    private Book book;
    private Scanner input;

    public ReadingListApp() {
        runReadingList();
    }

    private void runReadingList() {
        boolean exit = false;
        String command;
        input = new Scanner(System.in);
        System.out.println("Welcome to your Reading List!");

        init();

        while (!exit) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("4")) {
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
        listOfBooks = new BookList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    // EFFECTS: Displays a menu of options to the user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\n1. View reading list");
        System.out.println("\n2. Add a book to your reading list");
        System.out.println("\n3. Remove a book from your reading list");
        System.out.println("\n4. Quit ReadingList\n");
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

        System.out.println("Please enter the following details:\n");
        System.out.println("Name: ");
        name = input.next();
        System.out.println("Author:");
        author = input.next();
        System.out.println("Number of pages:");
        pages = input.nextInt();
        System.out.println("Status:");
        status = input.nextInt();
        System.out.println("Rating:");
        rating = input.nextInt();

        newBook = new Book(name, author, pages, status, rating);
        if (listOfBooks.contains(newBook)) {
            System.out.println("THIS BOOK IS ALREADY IN YOUR READING LIST!");
        } else {
            listOfBooks.addBook(newBook);
            System.out.println("BOOK HAS BEEN ADDED SUCCESSFULLY!");
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
        System.out.println("BOOK HAS BEEN REMOVED SUCCESSFULLY");
    }

    // EFFECTS: prints out all the books in the current list of books
    private void displayCurrentBookList() {
        System.out.println("\nREADING LIST:\n\n" + listOfBooks.getListOfBooks());
    }
}