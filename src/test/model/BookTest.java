package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {
    private Book testBook1;
    private Book testBook2;
    private Book testBook3;
    private Book testBook4;

    @BeforeEach
    void runBefore() {
        testBook1 = new Book("Pride and Prejudice", "Jane Austen", 480, 2, 4);
        testBook2 = new Book("1984", "George Orwell", 328, 1, 5);
        testBook3 = new Book("Harry Potter", "J.K Rowling", 607, 3, 5);
        testBook4 = new Book("Animal Farm", "George Orwell", 420, 5, 5);
    }

    @Test
    void testConstructor() {
        assertEquals("Pride and Prejudice", testBook1.getBookName());
        assertEquals("Jane Austen", testBook1.getBookAuthor());
        assertEquals(480, testBook1.getNumOfPages());
        assertEquals("In progress...", testBook1.getBookStatus());
        assertEquals("⭑⭑⭑⭑", testBook1.getBookRating());

        assertEquals("1984", testBook2.getBookName());
        assertEquals(328, testBook2.getNumOfPages());
        assertEquals("New!", testBook2.getBookStatus());

        assertEquals("Completed!", testBook3.getBookStatus());

        assertEquals("-", testBook4.getBookStatus());
    }
}