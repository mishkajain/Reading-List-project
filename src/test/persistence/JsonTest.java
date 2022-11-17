package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String name, String author, Integer pages, String status, String rating, Book book) {
        assertEquals(name, book.getBookName());
        assertEquals(author, book.getBookAuthor());
        assertEquals(pages, book.getNumOfPages());
        assertEquals(status, book.getBookStatus());
        assertEquals(rating, book.getBookRating());
    }
}
