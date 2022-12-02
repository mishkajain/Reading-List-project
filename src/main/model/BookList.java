package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

/*
* Represents the List which contains Book objects
* */

public class BookList implements Writable {
    // Initialising an empty ArrayList with type Book called listOfBooks
    final ArrayList<Book> listOfBooks;

    // EFFECTS: Creates a new an empty ArrayList with type Book called listOfBooks
    public BookList() {
        listOfBooks = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds a Book object to the list of books
    //          logs an event indicating that a book has been added to BookList
    public void addBook(Book book) {
        if (!contains(book)) {
            listOfBooks.add(book);
            EventLog.getInstance().logEvent(new Event("Added book: '" + book.getBookName()
                    + "' to Reading List"));
        }
    }

    // EFFECTS: returns the number of books in the list of books
    public int getNumberOfBooks() {
        return listOfBooks.size();
    }

    // EFFECTS: returns a string containing the list of books added to the list of books
    public String getListOfBooks() {
        String list = "";
        for (Book b : listOfBooks) {
            list = list.concat(b.getBookName().concat(" by ".concat(b.getBookAuthor().concat("\n"
                    .concat("Pages: ".concat(String.valueOf(b.getNumOfPages()).concat("\n".concat("Status: "
                    .concat(b.getBookStatus().concat("\n".concat("Rating: ".concat(b.getBookRating()
                            .concat("\n-------------------------------------------------------------\n")))))))))))));

        }

        return list;
    }

    // MODIFIES: this
    // EFFECTS: removes a book from the list of books
    //          logs an event indicating that a book has been removed from BookList
    public String removeBook(Book book) {
        String bookIsThere = "Book removed from list";
        String bookIsNotThere = "Could not find the book in the list";
        String emptyReadingList = "Your reading list is empty";

        if (listOfBooks.isEmpty()) {
            return emptyReadingList;
        } else if (listOfBooks.contains(book)) {
            listOfBooks.remove(book);
            EventLog.getInstance().logEvent(new Event("Removed book: '" + book.getBookName()
                    + "' from Reading List"));
            return bookIsThere;
        }
        return bookIsNotThere;
    }

    // EFFECTS: checks if the list of books contains a particular book
    public boolean contains(Book book) {
        boolean found = false;
        for (Book b : listOfBooks) {
            if (b.getBookName().equals(book.getBookName())) {
                if (b.getBookAuthor().equals(book.getBookAuthor())) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public Book get(Integer command) {
        return listOfBooks.get(command);
    }

    public int size() {
        return listOfBooks.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfBooks", listOfBooksToJson());
        return json;
    }

    // EFFECTS: returns things in this BookList as a JSON array
    //          logs an event indicating that a BookList has been saved to file
    private JSONArray listOfBooksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book book : listOfBooks) {
            jsonArray.put(book.toJson());
        }

        EventLog.getInstance().logEvent(new Event("Saved Reading List to file"));
        return jsonArray;
    }
}


