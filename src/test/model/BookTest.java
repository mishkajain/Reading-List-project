package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* Tests for the Book class
*/

class BookTest {
    private Book testBook1;
    private Book testBook2;
    private Book testBook3;
    private Book testBook4;
    private Book testBook5;
    private Book testBook6;

    @BeforeEach
    void runBefore() {
        testBook1 = new Book("Pride and Prejudice", "Jane Austen", 480, 2, 5);
        testBook2 = new Book("1984", "George Orwell", 328, 1, 4);
        testBook3 = new Book("Harry Potter", "J.K Rowling", 607, 3, 3);
        testBook4 = new Book("Animal Farm", "George Orwell", 420, 5, 2);
        testBook5 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 208, 3, 1);
        testBook6 = new Book("1984", "George Orwell", 328, 1, 5);
    }

    @Test
    void testConstructor() {
        assertEquals("Pride and Prejudice", testBook1.getBookName());
        assertEquals("Jane Austen", testBook1.getBookAuthor());
        assertEquals(480, testBook1.getNumOfPages());
        assertEquals("In progress...", testBook1.getBookStatus());
        assertEquals("⭑⭑⭑⭑⭑", testBook1.getBookRating());

        assertEquals("1984", testBook2.getBookName());
        assertEquals(328, testBook2.getNumOfPages());
        assertEquals("New!", testBook2.getBookStatus());
        assertEquals("⭑⭑⭑⭑", testBook2.getBookRating());

        assertEquals("Completed!", testBook3.getBookStatus());
        assertEquals("⭑⭑⭑", testBook3.getBookRating());

        assertEquals("-", testBook4.getBookStatus());
        assertEquals("⭑⭑", testBook4.getBookRating());

        assertEquals("⭑", testBook5.getBookRating());

        assertEquals("⭑⭑⭑⭑⭑", testBook6.getBookRating());
    }
}