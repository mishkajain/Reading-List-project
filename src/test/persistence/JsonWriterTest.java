package persistence;

import model.Book;
import model.BookList;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            BookList listOfBooks = new BookList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }

    }

    @Test
    void testWriterEmptyBookList() {
        try {
            BookList listOfBooks = new BookList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookList.json");
            writer.open();
            writer.write(listOfBooks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookList.json");
            listOfBooks = reader.read();
            assertEquals(0, listOfBooks.getNumberOfBooks());


        } catch (IOException e) {
            fail("Exception should not have been thrown!");
        }
    }

    @Test
    void testGeneralBookListWithOneBook() {
        try {
            BookList listOfBooks = new BookList();
            listOfBooks.addBook(new Book("1984", "George Orwell", 328, 1, 5));
            JsonWriter writer = new JsonWriter("./data/testGeneralBookListWithOneBook.json");
            writer.open();
            writer.write(listOfBooks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGeneralBookListWithOneBook.json");
            listOfBooks = reader.read();
            assertEquals(1, listOfBooks.getNumberOfBooks());
            checkBook("1984", "George Orwell", 328, "New!", 5, listOfBooks.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testGeneralBookListWithMultipleBooks() {
        try {
            BookList listOfBooks = new BookList();
            listOfBooks.addBook(new Book("1984", "George Orwell", 328, 1, 5));
            listOfBooks.addBook(new Book("Pride and Prejudice", "Jane Austen", 480, 2, 4));
            listOfBooks.addBook(new Book("Harry Potter", "J.K Rowling", 607, 3, 5));
            JsonWriter writer = new JsonWriter("./data/testGeneralBookListWithOneBook.json");
            writer.open();
            writer.write(listOfBooks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testGeneralBookListWithOneBook.json");
            listOfBooks = reader.read();
            assertEquals(3, listOfBooks.getNumberOfBooks());
            checkBook("1984", "George Orwell", 328, "New!", 5, listOfBooks.get(0));
            checkBook("Pride and Prejudice", "Jane Austen", 480, "In progress...", 4, listOfBooks.get(1));
            checkBook("Harry Potter", "J.K Rowling", 607, "Completed!", 5, listOfBooks.get(2));


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}