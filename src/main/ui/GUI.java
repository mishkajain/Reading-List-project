
//https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel

package ui;

import model.Book;
import model.BookList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener {


    private Book book;
    private BookList bookList;
    private static final String JSON_STORE = "./data/readingList.json";
    private final Dimension dimension = new Dimension(550, 700);


    private JPanel homePagePanel;
    private JPanel viewReadingListPanel;
    private JPanel addBookPanel;
    private JFrame progressBarPopUp;

    private JProgressBar progressBar;

    private JButton viewListPanelButton;
    private JButton addBookPanelButton;
    private JButton saveListButton;
    private JButton loadListButton;
    private JButton quitApplication;
    private JButton returnToHomePageButton;
    private JButton addBookToListButton;

    private JTextArea readingList;
    private ImageIcon imageIcon;

    private JLabel title;
    private JLabel author;
    private JLabel pages;
    private JLabel status;
    private JLabel rating;
    private JLabel addBookMessage;
    private JLabel giphyGif;
    private JLabel bookGif;
    private JLabel anotherBookGif;
    private JLabel thirdBookGif;
    private JLabel savedBookMessage;
    private JLabel loadedBookMessage;

    private JTextField titleText;
    private JTextField authorText;
    private JTextField pagesText;
    private JTextField statusText;
    private JTextField ratingText;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public GUI() {
        super("Reading List");
        bookList = new BookList();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createHomePagePanel();
        makeViewReadingListPanel();
        makeAddBookPanel();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        JLabel welcomeMessageLabel = new JLabel("Welcome to your Reading List!", SwingConstants.CENTER);
        addLabel(welcomeMessageLabel, homePagePanel);

        makeHomePageButtons();
        addButtonsToHomeScreen();
        addActionListenerToHomePageButtons();
        this.setLocationRelativeTo(null);
    }

    // EFFECTS : creates the first page users see when they start the reading list app
    // https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextArea.html
    public void createHomePagePanel() {
        homePagePanel = new JPanel(new GridLayout(7, 1));
        homePagePanel.setBackground(Color.LIGHT_GRAY);
        add(homePagePanel);
        homePagePanel.setVisible(true);
        setSize(dimension);
        setResizable(false);

        readingList = new JTextArea();
        readingList.setEditable(false);

    }

    // EFFECTS: instantiates the home panel buttons
    public void makeHomePageButtons() {
        viewListPanelButton = new JButton("View Reading List");
        addBookPanelButton = new JButton("Add a Book to Your Reading List");
        saveListButton = new JButton("Save Reading List to File");
        loadListButton = new JButton("Load Reading List from File");
        quitApplication = new JButton("Quit");
    }

    // EFFECTS: adds several buttons to the home panel
    //          adds a gif to the home panel
    // gif taken from:
    // https://acegif.com/gifs-of-books/
    private void addButtonsToHomeScreen() {
        addButton(viewListPanelButton, homePagePanel);
        addButton(addBookPanelButton, homePagePanel);
        addButton(saveListButton, homePagePanel);
        addButton(loadListButton, homePagePanel);
        addButton(quitApplication, homePagePanel);

        bookGif = new JLabel(imageIcon);
        addLabel(bookGif, homePagePanel);

        addImage("anotherBook.gif", bookGif);
        bookGif.setBounds(5,5,10,10);
    }

    // REQUIRES: label and panel not be null
    // EFFECTS: sets font for label and adds it to the given panel
    private void addLabel(JLabel label, JPanel panel) {
        label.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(label);
    }

    // REQUIRES: textField and panel not be null
    // EFFECTS: sets font for textField and adds it to the given panel
    private void addTextField(JTextField textField, JPanel panel) {
        textField.setFont(new Font("Arial", Font.PLAIN, 13));
        panel.add(textField);
    }

    // REQUIRES: button and panel not be null
    // EFFECTS: sets font, background, visibility and size for buttons and adds it to the given panel
    private void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(Color.WHITE);
        panel.add(button);
        setVisible(true);
        setResizable(true);
    }

    // EFFECTS: adds the action commands and listeners for all the home page buttons
    public void addActionListenerToHomePageButtons() {
        viewListPanelButton.addActionListener(this);
        addBookPanelButton.addActionListener(this);
        saveListButton.addActionListener(this);
        loadListButton.addActionListener(this);
        quitApplication.addActionListener(this);

        viewListPanelButton.setActionCommand("View Reading List");
        addBookPanelButton.setActionCommand("Add a Book to Your Reading List");
        saveListButton.setActionCommand("Save Reading List to File");
        loadListButton.setActionCommand("Load Reading List from File");
        quitApplication.setActionCommand("Quit");
    }

    @Override
    // EFFECTS: sets the action events for all the buttons in the application
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Reading List")) {
            displayViewReadingListPanel();
        } else if (e.getActionCommand().equals("Add a Book to Your Reading List")) {
            displayAddBookPanel();
        } else if (e.getActionCommand().equals("Save Reading List to File")) {
            saveReadingListToFile();
        } else if (e.getActionCommand().equals("Load Reading List from File")) {
            loadReadingListFromFile();
        } else if (e.getActionCommand().equals("Quit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Return")) {
            returnToHomePage();
        } else if (e.getActionCommand().equals("Add")) {
            addBookToReadingList();
        }
    }

    // EFFECTS: creates and sets the action for the return button
    private void returnToHomePageButton() {
        returnToHomePageButton = new JButton("Return");
        returnToHomePageButton.setActionCommand("Return");
        returnToHomePageButton.addActionListener(this);
    }

    // MODIFIES: visibility of homePagePanel, viewReadingListPanel,
    // addBookPanel, saveListButton, loadListButton, addBookMessage, giphyGif
    // EFFECTS: returns to the home page
    private void returnToHomePage() {
        homePagePanel.setVisible(true);
        viewReadingListPanel.setVisible(false);
        addBookPanel.setVisible(false);
        saveListButton.setForeground(Color.BLACK);
        loadListButton.setForeground(Color.BLACK);
        addBookMessage.setText("");
        giphyGif.setVisible(false);
    }

    // MODIFIES: readingList (label)
    // EFFECTS: loads and displays bookList that was saved to file in the readingList label
    private void loadReadingListFromFile() {
        try {
            bookList = jsonReader.read();
            readingList.setText(bookList.getListOfBooks());
            loadProgressBar();

            loadListButton.setForeground(Color.green);
            saveListButton.setForeground(Color.BLACK);
        } catch (IOException e) {
            loadListButton.setForeground(Color.red);
            saveListButton.setForeground(Color.BLACK);
        }
    }

    // gif taken from:
    // https://giphy.com/stickers/book-books-reading-3hoLIVAJYkz6T0Ichp
    private void loadProgressBar() {
        progressBarPopUp = new JFrame();
        progressBarPopUp.setVisible(true);
        progressBarPopUp.setLayout(null);
        progressBarPopUp.setBounds(500, 500, 350, 225);

        progressBar = new JProgressBar(0, 100);
        progressBarPopUp.add(progressBar);
        progressBar.setBounds(80, 75, 200, 100);
        progressBar.setPreferredSize(new Dimension(300, 100));
        progressBar.setStringPainted(true);
        fillProgressBar();

        thirdBookGif = new JLabel(imageIcon);
        progressBarPopUp.add(thirdBookGif);
        addImage("book.gif", thirdBookGif);
        thirdBookGif.setBounds(110, 0, 140, 140);
        savedBookMessage = new JLabel("Reading List Loaded Successfully!");
        progressBarPopUp.add(savedBookMessage);
        savedBookMessage.setBounds(75, 110, 300, 90);
    }

    // MODIFIES: Json File
    // EFFECTS: saves the current bookList to the readingList Json file
    private void saveReadingListToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookList);
            jsonWriter.close();

            saveProgressBar();

            saveListButton.setForeground(Color.green);
            loadListButton.setForeground(Color.BLACK);
        } catch (FileNotFoundException e) {
            saveListButton.setForeground(Color.red);
            loadListButton.setForeground(Color.BLACK);
        }
    }

    // EFFECTS: Creates a new pop-up window to display an animated loading
    //          progress bar to display the loaded/saved book messages
    // https://giphy.com/stickers/transparent-jP4tBUskCa8nt7FrrK
    private void saveProgressBar() {
        progressBarPopUp = new JFrame();
        progressBarPopUp.setVisible(true);
        progressBarPopUp.setLayout(null);
        progressBarPopUp.setBounds(500, 500, 350, 225);

        progressBar = new JProgressBar(0, 100);
        progressBarPopUp.add(progressBar);
        progressBar.setBounds(80, 75, 200, 100);
        progressBar.setPreferredSize(new Dimension(300, 100));
        progressBar.setStringPainted(true);
        fillProgressBar();

        anotherBookGif = new JLabel(imageIcon);
        progressBarPopUp.add(anotherBookGif);
        addImage("bookmarkBook.gif", anotherBookGif);
        anotherBookGif.setBounds(135, 15, 90, 90);
        loadedBookMessage = new JLabel("Reading List Saved Successfully!");
        progressBarPopUp.add(loadedBookMessage);
        loadedBookMessage.setBounds(75, 110, 300, 90);
    }

    // MODIFIES: progressBar
    // EFFECTS: fills the progress bar
    // https://www.youtube.com/watch?v=JEI-fcfnFkc
    public void fillProgressBar() {
        int counter = 0;
        while (counter <= 100) {
            progressBar.setValue(counter);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter += 5;
        }
    }

    // MODIFIES: the visibility of addBookPanel, homePagePanel, viewReadingListPanel
    // EFFECTS: displays the addBookPanel onto the screen
    private void displayAddBookPanel() {
        add(addBookPanel);
        addBookPanel.setVisible(true);
        homePagePanel.setVisible(false);
        viewReadingListPanel.setVisible(false);
    }

    // EFFECTS: creates the addBookPanel and adds the appropriate labels, buttons and text fields.
    // gif taken from:
    // https://giphy.com/stickers/book-stack-the-peachy-polka-dot-tppd-YmunwAcgeZJaH49CrT?utm_source=iframe&utm_medium=embed&utm_campaign=Embeds&utm_term=
    private void makeAddBookPanel() {
        addBookPanel = new JPanel(new GridLayout(7, 2));
        bookInformationLabelAndTextField();

        addBookMessage = new JLabel();
        addLabel(addBookMessage, addBookPanel);
        giphyGif = new JLabel(imageIcon);
        addImage("giphy.gif", giphyGif);
        addLabel(giphyGif, addBookPanel);
        giphyGif.setVisible(false);

        addBookToListButton = new JButton("Add");
        addBookToListButton.setActionCommand("Add");
        addBookToListButton.addActionListener(this);
        addButton(addBookToListButton, addBookPanel);

        returnToHomePageButton();
        addButton(returnToHomePageButton, addBookPanel);
    }

    // REQUIIRES: !(addBookPanel == null)
    //             all text fields and labels not be null
    // EFFECTS: instantiates all the labels and text fields on the addBookPanel and adds them to the panel
    private void bookInformationLabelAndTextField() {
        title = new JLabel("   Title:");
        addLabel(title, addBookPanel);
        titleText = new JTextField(30);
        addTextField(titleText, addBookPanel);
        author = new JLabel("   Author:");
        addLabel(author, addBookPanel);
        authorText = new JTextField(30);
        addTextField(authorText, addBookPanel);
        pages = new JLabel("   Number or Pages:");
        addLabel(pages, addBookPanel);
        pagesText = new JTextField(6);
        addTextField(pagesText, addBookPanel);
        status = new JLabel("   Current Status:");
        addLabel(status, addBookPanel);
        statusText = new JTextField(20);
        addTextField(statusText, addBookPanel);
        rating = new JLabel("   Rating:");
        addLabel(rating, addBookPanel);
        ratingText = new JTextField(2);
        addTextField(ratingText, addBookPanel);
    }

    // MODIFIES: bookList, readingList
    // EFFECTS: adds the book created by the information taken from the text fields on the addBookPanel
    //          if bookList does not already contain the book. Displays a gif after the book has been added successfully
    //          and clears all the textfields on the addBookPanel.
    public void addBookToReadingList() {
        try {
            book = new Book(titleText.getText(), authorText.getText(), Integer.valueOf(pagesText.getText()),
                    Integer.parseInt(statusText.getText()), Integer.parseInt(ratingText.getText()));
            if (bookList.contains(book)) {
                throw new Exception("   This book is already in your Reading List!");
            } else {
                bookList.addBook(book);
                readingList.setText(bookList.getListOfBooks());
                clearTextFields();
                addBookMessage.setForeground(Color.BLACK);
                addBookMessage.setText("             Book has been added successfully!");
                giphyGif.setVisible(true);
            }
        } catch (NumberFormatException e) {
            addBookMessage.setForeground(Color.red);
            addBookMessage.setText("   Please try again: Invalid entry!");
            clearTextFields();
        } catch (Exception e) {
            addBookMessage.setForeground(Color.red);
            addBookMessage.setText("   This book is already in your Reading List!");
            clearTextFields();
            giphyGif.setVisible(false);

        }

    }

    // EFFECTS: adds an image icon to the given label
    // Referenced from:
    // https://stackoverflow.com/questions/4339029/display-animated-gif-on-jpanel
    // https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    private void addImage(String image, JLabel label) {
        imageIcon = new ImageIcon(this.getClass().getResource(image));
        ImageIcon resizedImageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(150, 90,
                Image.SCALE_DEFAULT));
        label.setIcon(resizedImageIcon);
        label.setBounds(5,5,5,5);
    }

    // MODIFIES: titleText, authorText, pagesText, statusText, ratingText
    // EFFECTS: clears the text fields by setting them to empty strings ("")
    private void clearTextFields() {
        titleText.setText("");
        authorText.setText("");
        pagesText.setText("");
        statusText.setText("");
        ratingText.setText("");

    }

    // EFFECTS: Sets the readingList label to the list of books in the current bookList
    public void loadReadingList() {
        readingList.setText(bookList.getListOfBooks());
    }

    // REQUIRES: !(viewReadingListPanel == null)
    // MODIFIES: visibility of viewReadingListPanel, homePagePanel, addBookPanel
    // EFFECTS: adds the viewReadingListPanel to the container
    private void displayViewReadingListPanel() {
        add(viewReadingListPanel);
        viewReadingListPanel.setVisible(true);
        homePagePanel.setVisible(false);
        addBookPanel.setVisible(false);
    }

    // EFFECTS: makes the viewReadingListPanel with a scroll pane to display the current reading list
    // Referenced from:
    // https://stackoverflow.com/questions/10177183/add-scroll-into-text-area
    private void makeViewReadingListPanel() {
        viewReadingListPanel = new JPanel();
        viewReadingListPanel.setLayout(null);
        loadReadingList();
        JScrollPane scrollPane = new JScrollPane(readingList);
        readingList.setText("Your Reading List is Empty!");
        scrollPane.setBounds(25, 25, 500, 570);
        viewReadingListPanel.add(scrollPane);
        returnToHomePageButton();
        addButton(returnToHomePageButton, viewReadingListPanel);
        returnToHomePageButton.setBounds(190, 605, 200, 50);
    }
}