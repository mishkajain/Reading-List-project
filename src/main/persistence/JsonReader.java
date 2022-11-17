// Based on the supplied Workroom example for CPSC 210 :
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

package persistence;

import model.Book;
import model.BookList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Representing a reader that reads readingList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads readingList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        BookList listOfBooks = new BookList();
        return parseBookList(listOfBooks, jsonObject);
    }

    // EFFECTS: Reads the source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: ReadingList
    // EFFECTS: parses listOfBooks from JSON object and adds them to ReadingList
    private BookList parseBookList(BookList listOfBooks, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfBooks");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(listOfBooks, nextBook);
        }
        return listOfBooks;
    }

    // MODIFIES: rl
    // EFFECTS: parses book from JSON object and adds it to listOfBooks
    private void addBook(BookList listOfBooks, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String author = jsonObject.getString("author");
        Integer pages = jsonObject.getInt("pages");
        Integer status = jsonObject.getInt("status");
        Integer rating = jsonObject.getInt("rating");

        Book book = new Book(name, author, pages, status, rating);
        listOfBooks.addBook(book);
    }
}
