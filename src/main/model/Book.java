package model;

import org.json.JSONObject;
import persistence.Writable;

/*
* Represents a single Book object
*/

public class Book implements Writable {
    private String bookName;                 // book name
    private String bookAuthor;               // author of the book
    private Integer numOfPages;              // number of pages in the book
    private Integer bookStatus;              // status of the book ( 1: new, 2: in progress, 3: completed)
    private Integer bookRating;              // Rate the book out of 5


    /*
    * REQUIRES: name has a non-zero length
    *           author has a non-zero length
    *           pages >= 1
    *           status must be either 1, 2 or 3
    *           rating must be a number between 1 and 5
    * EFFECTS: creates a book with bookName (name), bookAuthor (author), the number of pages in the book (pages),
    *          bookStatus (status) and bookRating (rating)
    * */
    public Book(String name, String author, Integer pages, Integer status, Integer rating) {
        bookName = name;
        bookAuthor = author;
        numOfPages = pages;
        bookStatus = status;
        bookRating = rating;
    }

    // EFFECTS: returns the name of the book
    public String getBookName() {
        return bookName;
    }

    // EFFECTS: returns the name of the author of the book
    public String getBookAuthor() {
        return bookAuthor;
    }

    // EFFECTS: returns the number of pages in the book
    public Integer getNumOfPages() {
        return numOfPages;
    }

    // EFFECTS: returns the current status of the book
    public String getBookStatus() {
        String status;
        if (bookStatus.equals(1)) {
            status = "New!";
        } else if (bookStatus.equals(2)) {
            status = "In progress...";
        } else if (bookStatus.equals(3)) {
            status = "Completed!";
        } else {
            status = "-";
        }
        return status;
    }

    // EFFECTS: returns the current rating of the book
    public String getBookRating() {
        String rating;
        if (bookRating == 1) {
            rating = "⭑";
        } else if (bookRating == 2) {
            rating = "⭑⭑";
        } else if (bookRating == 3) {
            rating = "⭑⭑⭑";
        } else if (bookRating == 4) {
            rating = "⭑⭑⭑⭑";
        } else {
            rating = "⭑⭑⭑⭑⭑";
        }
        return rating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", bookName);
        json.put("author", bookAuthor);
        json.put("pages", numOfPages);
        json.put("status", bookStatus);
        json.put("rating", bookRating);
        return json;
    }

}


