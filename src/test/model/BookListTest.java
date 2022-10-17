package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BookListTest {
    private BookList bookList;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void runBefore() {
        bookList = new BookList();
        book1 = new Book("Pride and Prejudice", "Jane Austen", 480, 2, 4);
        book2 = new Book("1984", "George Orwell", 328, 1, 5);
        book3 = new Book("Harry Potter and the Deathly Hallows", "J.K Rowling", 607, 2, 5);
    }

    @Test
    void testAddBook() {
        bookList.addBook(book1);
        assertTrue(bookList.contains(book1));

        assertEquals("Book has been added to your list", bookList.addBook(book2));
        assertEquals("This book has already been added to your list", bookList.addBook(book1));

    }

    @Test
    void testAddMultipleBooks() {
        bookList.addBook(book1);
        bookList.addBook(book2);
        bookList.addBook(book3);

        assertTrue(bookList.contains(book1));
        assertTrue(bookList.contains(book2));
        assertTrue(bookList.contains(book3));
    }

    @Test
    void testAddSameBookMultipleTimes() {
        bookList.addBook(book1);
        bookList.addBook(book1);
        bookList.addBook(book1);

        assertEquals(1, bookList.getNumberOfBooks());
    }

    @Test
    void testNumberOfBooks() {
        bookList.addBook(book1);
        assertEquals(1, bookList.getNumberOfBooks());
        bookList.addBook(book2);
        assertEquals(2, bookList.getNumberOfBooks());
        bookList.addBook(book3);
        assertEquals(3, bookList.getNumberOfBooks());
    }

    @Test
    void testContains() {
        bookList.addBook(book1);
        assertTrue(bookList.contains(book1));

        assertFalse(bookList.contains(book2));
        bookList.addBook(book2);
        assertTrue(bookList.contains(book2));

        assertFalse(bookList.contains(book3));
        bookList.addBook(book3);
        assertTrue(bookList.contains(book3));
    }

    @Test
    void testRemoveBookFromList() {
        bookList.addBook(book1);
        bookList.addBook(book2);
        assertTrue(bookList.contains(book2));

        bookList.removeBook(book2);
        assertEquals(book1.getBookName().concat(" by ").concat(book1.getBookAuthor()).concat("\n").
                        concat("Status: ".concat(book1.getBookStatus().concat("\n".concat("Rating: "
                                .concat(String.valueOf(book1.getBookRating()) +
                                 "\n---------------------------------------\n"))))),
                bookList.getListOfBooks());

        assertFalse(bookList.contains(book2));
        assertEquals("Book removed from list", bookList.removeBook(book1));
    }

    @Test
    void testRemoveMultipleBooksFromList() {
        bookList.addBook(book1);
        bookList.addBook(book2);

        bookList.removeBook(book1);
        bookList.removeBook(book2);

        assertEquals(0, bookList.getNumberOfBooks());
    }

    @Test
    void testRemoveBookFromListButBookIsNotInTheList() {
        // list is empty
        assertEquals("Your reading list is empty", bookList.removeBook(book1));
        bookList.addBook(book1);
        assertEquals("Could not find the book in the list", bookList.removeBook(book2));
    }

    @Test
    void testGetListOfBooks() {
        bookList.addBook(book1);
        bookList.addBook(book2);

        assertEquals(book1.getBookName().concat(" by ").concat(book1.getBookAuthor()).concat("\n").
                concat("Status: ".concat(book1.getBookStatus().concat("\n".concat("Rating: ".
                        concat(String.valueOf(book1.getBookRating()) +
                                "\n---------------------------------------\n" +
                                book2.getBookName().concat(" by ").concat(book2.getBookAuthor()).concat("\n").
                                        concat("Status: ".concat(book2.getBookStatus().concat("\n".concat("Rating: ".
                                                concat(String.valueOf(book2.getBookRating()) +
                                                        "\n---------------------------------------\n")))))))))),
                bookList.getListOfBooks());

    }

    @Test
    void testGetABookFromListOfBooks() {
        bookList.addBook(book1);
        bookList.addBook(book2);

        assertEquals(book2, bookList.get(1));
    }

}
