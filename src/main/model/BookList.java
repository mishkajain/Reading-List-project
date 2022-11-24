package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class BookList implements Writable {
    // Initialising an empty ArrayList with type Book called listOfBooks
    final ArrayList<Book> listOfBooks;
    private String list = "";

    // EFFECTS: Creates a new an empty ArrayList with type Book called listOfBooks
    public BookList() {
        listOfBooks = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds a Book object to the list of books
    public String addBook(Book book) {
        if (contains(book)) {
            String bookAlreadyInList = "This book has already been added to your list";
            return bookAlreadyInList;
        } else {
            listOfBooks.add(book);
            String addedBook = "Book has been added to your list";
            EventLog.getInstance().logEvent(new Event("Added book: '" + book.getBookName() + "' to Reading List"));
            return addedBook;
        }
    }

    // EFFECTS: returns the number of books in the list of books
    public int getNumberOfBooks() {
        return listOfBooks.size();
    }

    // EFFECTS: returns a string containing the list of books added to the list of books
    public String getListOfBooks() {
        list = "";
        for (int i = 0; i < listOfBooks.size(); i++) {
            list = list + listOfBooks.get(i).getBookName() + " by "
                    + listOfBooks.get(i).getBookAuthor()
                    + "\n" + "Status: " + listOfBooks.get(i).getBookStatus()
                    + "\n" + "Rating: " + listOfBooks.get(i).getBookRating()
                    + "\n====================================\n";
        }
        return list;
    }

    // MODIFIES: this
    // EFFECTS: removes a book from the list of books
    public String removeBook(Book book) {
        String bookIsThere = "Book removed from list";
        String bookIsNotThere = "Could not find the book in the list";
        String emptyReadingList = "Your reading list is empty";

        if (listOfBooks.isEmpty()) {
            return emptyReadingList;
        } else if (listOfBooks.contains(book)) {
            listOfBooks.remove(book);
            EventLog.getInstance().logEvent(new Event("Removed book: '" + book.getBookName() + "' from Reading List"));
            return bookIsThere;
        }
        return bookIsNotThere;
    }

    // EFFECTS: checks if the list of books contains a particular book
    public boolean contains(Book book) {
        boolean found = false;
        for (int i = 0; i < listOfBooks.size(); i++) {
            if (listOfBooks.get(i).getBookName().equals(book.getBookName())) {
                if (listOfBooks.get(i).getBookAuthor().equals(book.getBookAuthor())) {
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
    private JSONArray listOfBooksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book book : listOfBooks) {
            jsonArray.put(book.toJson());
        }

        EventLog.getInstance().logEvent(new Event("Saved Reading List to file"));
        return jsonArray;
    }
}


