package model;

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
        bookList.addBook(book1);
        assertTrue(bookList.contains(book1));
    }

    @Test
    void testAddMultipleBooks() {
        bookList.addBook(book1);
        bookList.addBook(book2);
        bookList.addBook(book3);

        assertTrue(bookList.contains(book1));
        assertTrue(bookList.contains(book2));
        assertTrue(bookList.contains(book3));
        assertEquals("⭑⭑⭑⭑⭑", bookList.get(1).getBookRating());
    }

    @Test
    void testAddSameBookMultipleTimes() {
        bookList.addBook(book1);
        bookList.addBook(book1);

        assertEquals(1, bookList.getNumberOfBooks());
    }

    @Test
    void testAddBookWithDifferentNameSameAuthor() {
        bookList.addBook(book3);
        bookList.addBook(book4);
        assertEquals(2, bookList.getNumberOfBooks());

    }

    @Test
    void testAddBookWithSameNameDifferentAuthor() {
        bookList.addBook(book4);
        bookList.addBook(book5);

        assertEquals(2, bookList.getNumberOfBooks());
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
    void testContainsBook() {
        bookList.addBook(book1);
        assertTrue(bookList.contains(book1));

        assertFalse(bookList.contains(book2));
        bookList.addBook(book2);
        assertTrue(bookList.contains(book2));

        assertFalse(bookList.contains(book5));
        bookList.addBook(book5);
        assertTrue(bookList.contains(book5));
    }

    @Test
    void testContainsBookSameNameDifferentAuthor() {
        bookList.addBook(book4);
        assertTrue(bookList.contains(book4));

        bookList.addBook(book5);
        assertTrue(bookList.contains(book5));
    }


    @Test
    void testRemoveBookFromList() {
        bookList.addBook(book1);
        bookList.addBook(book2);
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
        bookList.addBook(book1);
        bookList.addBook(book2);

        assertEquals(book2, bookList.get(1));
    }

    @Test
    void testSize() {
        assertEquals(0, bookList.size());

        bookList.addBook(book1);
        bookList.addBook(book2);
        assertEquals(2, bookList.size());
    }

}
