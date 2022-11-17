package persistence;

import model.Book;
import model.BookList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderFileDoesNotExist() {
        JsonReader reader = new JsonReader("./data/testNoSuchFile.json");
        try {
            BookList bookList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookList() {
        JsonReader reader = new JsonReader("./data/testEmptyBookList.json");
        try {
            BookList listOfBooks = reader.read();
            assertEquals(0, listOfBooks.getNumberOfBooks());
        } catch (IOException e) {
            fail("Couldn't read from file; File is empty");
        }
    }

    @Test
    void testReaderBookList() {
        JsonReader reader = new JsonReader("./data/testBookList.json");
        try {
            BookList listOfBooks = reader.read();
            checkBook("Harry Potter and the Philosopher's Stone", "J.K Rowling", 223,
                    "In progress...", "⭑⭑⭑⭑", listOfBooks.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }
}