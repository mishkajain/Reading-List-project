package model;

import exceptions.DuplicateBookException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookListTest {
    private BookList bookList;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Book book6;

    @BeforeEach
    void runBefore() {
        bookList = new BookList();
        book1 = new Book("Pride and Prejudice", "Jane Austen", 480, 2, 4);
        book2 = new Book("1984", "George Orwell", 328, 1, 5);
        book3 = new Book("Harry Potter and the Deathly Hallows", "J.K Rowling", 607, 2, 5);
        book4 = new Book("Harry Potter and the Prisoner of Azkaban", "J.K Rowling", 607, 2, 5);
        book5 = new Book("Harry Potter and the Prisoner of Azkaban", "JK Rowling", 607, 2, 5);
        book6 = new Book("Harry Potter and the Prisoner of Azkaban", "JK Rowling", 607, 2, 5);
    }

    @Test
    void testAddBook() {
        try {
            bookList.addBook(book1);
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }

        assertTrue(bookList.contains(book1));
    }

    @Test
    void testAddMultipleBooks() {
        try {
            bookList.addBook(book1);
            bookList.addBook(book2);
            bookList.addBook(book3);
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }

        assertTrue(bookList.contains(book1));
        assertTrue(bookList.contains(book2));
        assertTrue(bookList.contains(book3));
    }

    @Test
    void testAddSameBookMultipleTimes() {
        try {
            bookList.addBook(book1);
            bookList.addBook(book1);
            fail("Should have thrown exception");
        } catch (DuplicateBookException e) {
            // all good
        }

        assertEquals(1, bookList.getNumberOfBooks());

        try {
            bookList.addBook(book2);
            bookList.addBook(book2);
            fail("Should have thrown exception");
        } catch (DuplicateBookException e) {
            // all good
        }

        assertEquals(2, bookList.getNumberOfBooks());
    }

    @Test
    void testAddBookWithDifferentNameSameAuthor() {
        try {
            bookList.addBook(book3);
            bookList.addBook(book4);
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }
        assertEquals(2, bookList.getNumberOfBooks());

    }

    @Test
    void testAddBookWithSameNameDifferentAuthor() {
        try {
            bookList.addBook(book4);
            bookList.addBook(book5);
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }

        assertEquals(2, bookList.getNumberOfBooks());
    }

    @Test
    void testNumberOfBooks() {
        try {
            bookList.addBook(book1);
            assertEquals(1, bookList.getNumberOfBooks());
            bookList.addBook(book2);
            assertEquals(2, bookList.getNumberOfBooks());
            bookList.addBook(book3);
            assertEquals(3, bookList.getNumberOfBooks());
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    void testContainsBook() {
        try {
            bookList.addBook(book1);
            assertTrue(bookList.contains(book1));

            assertFalse(bookList.contains(book2));
            bookList.addBook(book2);
            assertTrue(bookList.contains(book2));

            assertFalse(bookList.contains(book5));
            bookList.addBook(book5);
            assertTrue(bookList.contains(book5));
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    void testContainsBookSameNameDifferentAuthor() {
        try {
            bookList.addBook(book4);
            assertTrue(bookList.contains(book4));

            bookList.addBook(book5);
            assertTrue(bookList.contains(book5));
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }

        assertEquals(2, bookList.getNumberOfBooks());
    }


    @Test
    void testRemoveBookFromList() {
        try {
            bookList.addBook(book1);
            bookList.addBook(book2);
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }
        assertTrue(bookList.contains(book2));

        bookList.removeBook(book2);
        assertEquals(book1.getBookName().concat(" by ").concat(book1.getBookAuthor()).concat("\n").
                        concat("Status: ".concat(book1.getBookStatus().concat("\n".concat("Rating: "
                                .concat(book1.getBookRating() +
                                 "\n====================================\n"))))),
                bookList.getListOfBooks());

        assertFalse(bookList.contains(book2));
        assertEquals("Book removed from list", bookList.removeBook(book1));
    }

    @Test
    void testRemoveMultipleBooksFromList() {
        try {
            bookList.addBook(book1);
            bookList.addBook(book2);
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }

        bookList.removeBook(book1);
        bookList.removeBook(book2);

        assertEquals(0, bookList.getNumberOfBooks());
    }

    @Test
    void testRemoveBookFromListButBookIsNotInTheList() {
        // list is empty
        assertEquals("Your reading list is empty", bookList.removeBook(book1));
        try {
            bookList.addBook(book1);
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }
        assertEquals("Could not find the book in the list", bookList.removeBook(book2));
    }

    @Test
    void testGetListOfBooks() {
        try {
            bookList.addBook(book1);
            bookList.addBook(book2);
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }

        assertEquals(book1.getBookName().concat(" by ").concat(book1.getBookAuthor()).concat("\n").
                concat("Status: ".concat(book1.getBookStatus().concat("\n".concat("Rating: ".
                        concat(book1.getBookRating() +
                                "\n====================================\n" +
                                book2.getBookName().concat(" by ").concat(book2.getBookAuthor()).concat("\n").
                                        concat("Status: ".concat(book2.getBookStatus().concat("\n".concat("Rating: ".
                                                concat(book2.getBookRating() +
                                                        "\n====================================\n")))))))))),
                bookList.getListOfBooks());

    }

    @Test
    void testGetABookFromListOfBooks() {
        try {
            bookList.addBook(book1);
            bookList.addBook(book2);
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }

        assertEquals(book2, bookList.get(1));
    }

    @Test
    void testSize() {
        assertEquals(0, bookList.size());

        try {
            bookList.addBook(book1);
            bookList.addBook(book2);
        } catch (DuplicateBookException e) {
            fail("Should not have thrown exception");
        }
        assertEquals(2, bookList.size());
    }

}
