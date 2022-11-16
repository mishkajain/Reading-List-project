package ui;

import model.Book;
import model.BookList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
    Represents the main window in which the
    reading list is constructed
 */

public class GUI extends JFrame implements ActionListener {


    private Book book;
    private BookList bookList;
    private final String readingListFile = "./data/readingList.json";
    private Dimension dimension = new Dimension(500, 600);


    private JPanel homePagePanel;
    private JPanel viewReadingListPanel;
    private JPanel addBookPanel;

    private JButton viewListPanelButton;
    private JButton addBookPanelButton;
    private JButton saveListButton;
    private JButton loadListButton;
    private JButton quitApplication;
    private JButton returnToHomePageButton;
    private JButton addBookToListButton;

    private JTextArea readingList;// = new JLabel("Your Reading List is empty");

    private JLabel title;
    private JLabel author;
    private JLabel pages;
    private JLabel status;
    private JLabel rating;
    private JLabel addBookMessage;
    private JLabel emptyLabel;

    private JTextField titleText;
    private JTextField authorText;
    private JTextField pagesText;
    private JTextField statusText;
    private JTextField ratingText;


    public GUI() {
        super("Reading List");
        bookList = new BookList();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createHomePagePanel();
        makeViewReadingListPanel();
        makeAddBookPanel();

        JLabel welcomeMessageLabel = new JLabel("Welcome to your Reading List!");
        addLabel(welcomeMessageLabel, homePagePanel);
        makeHomePageButtons();

        addButton(viewListPanelButton, homePagePanel);
        addButton(addBookPanelButton, homePagePanel);
        addButton(saveListButton, homePagePanel);
        addButton(loadListButton, homePagePanel);
        addButton(quitApplication, homePagePanel);

        addActionListenerToButton();


    }

    private void addLabel(JLabel label, JPanel panel) {
        label.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(label);
    }

    private void addTextField(JTextField textField, JPanel panel) {
        textField.setFont(new Font("Arial", Font.PLAIN, 13));
        panel.add(textField);
    }

    private void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(Color.WHITE);
        panel.add(button);
        setVisible(true);
        setResizable(true);
    }

    public void createHomePagePanel() {
        homePagePanel = new JPanel();
        homePagePanel.setBackground(Color.LIGHT_GRAY);
        add(homePagePanel);
        homePagePanel.setVisible(true);
        setSize(dimension);
        setResizable(false);


        readingList = new JTextArea();
        //readingList.setText("Your Reading List is Empty!");
        readingList.setEditable(false);

    }

    public void makeHomePageButtons() {
        viewListPanelButton = new JButton("View Reading List");
        addBookPanelButton = new JButton("Add a Book to Your Reading List");
        saveListButton = new JButton("Save Reading List to File");
        loadListButton = new JButton("Load Reading List from File");
        quitApplication = new JButton("Quit");

    }

    private void centreOnScreen() {
    }

    public void addImageToLabel(JLabel j) {

    }

    public void addActionListenerToButton() {
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

    private void makeReturnToHomePageButton() {
        returnToHomePageButton = new JButton("Return");
        returnToHomePageButton.setActionCommand("Return");
        returnToHomePageButton.addActionListener(this);
    }


    private void returnToHomePage() {
        homePagePanel.setVisible(true);
        viewReadingListPanel.setVisible(false);
        addBookPanel.setVisible(false);
    }

    private void loadReadingListFromFile() {
    }

    private void saveReadingListToFile() {
    }

    private void displayAddBookPanel() {
        add(addBookPanel);
        addBookPanel.setVisible(true);
        homePagePanel.setVisible(false);
        viewReadingListPanel.setVisible(false);
    }

    private void makeAddBookPanel() {
        addBookPanel = new JPanel(new GridLayout(7, 2));
        bookInformationLabelAndTextField();

        addBookMessage = new JLabel();
        addLabel(addBookMessage, addBookPanel);
        emptyLabel = new JLabel();
        addLabel(emptyLabel, addBookPanel);

        addBookToListButton = new JButton("Add");
        addBookToListButton.setActionCommand("Add");
        addBookToListButton.addActionListener(this);
        addButton(addBookToListButton, addBookPanel);

        makeReturnToHomePageButton();
        addButton(returnToHomePageButton, addBookPanel);
    }

    private void bookInformationLabelAndTextField() {
        title = new JLabel("Title");
        addLabel(title, addBookPanel);
        titleText = new JTextField(30);
        addTextField(titleText, addBookPanel);
        author = new JLabel("Author");
        addLabel(author, addBookPanel);
        authorText = new JTextField(30);
        addTextField(authorText, addBookPanel);
        pages = new JLabel("Number or Pages");
        addLabel(pages, addBookPanel);
        pagesText = new JTextField(6);
        addTextField(pagesText, addBookPanel);
        status = new JLabel("Current Status");
        addLabel(status, addBookPanel);
        statusText = new JTextField(20);
        addTextField(statusText, addBookPanel);
        rating = new JLabel("Rating");
        addLabel(rating, addBookPanel);
        ratingText = new JTextField(2);
        addTextField(ratingText, addBookPanel);
    }

    public void addBookToReadingList() {
        try {
            book = new Book(titleText.getText(), authorText.getText(), Integer.valueOf(pagesText.getText()),
                    Integer.parseInt(statusText.getText()), Integer.parseInt(ratingText.getText()));
            bookList.addBook(book);
            readingList.setText(bookList.getListOfBooks());
            clearTextFields();
            addBookMessage.setText("Book has been added successfully!");
        } catch (NumberFormatException e) {
            addBookMessage.setText("Please try again!");
            clearTextFields();
        }

    }

    private void clearTextFields() {
        titleText.setText("");
        authorText.setText("");
        pagesText.setText("");
        statusText.setText("");
        ratingText.setText("");

    }

    public void loadReadingList() {
        readingList.setText(bookList.getListOfBooks());
    }

    private void displayViewReadingListPanel() {
        add(viewReadingListPanel);
        viewReadingListPanel.setVisible(true);
        homePagePanel.setVisible(false);
        addBookPanel.setVisible(false);
    }

    private void makeViewReadingListPanel() {
        viewReadingListPanel = new JPanel(new GridBagLayout());
        loadReadingList();
        JScrollPane scrollPane = new JScrollPane(readingList);
        scrollPane.setPreferredSize(new Dimension(400, 500));
        viewReadingListPanel.add(scrollPane);
        makeReturnToHomePageButton();
        addButton(returnToHomePageButton, viewReadingListPanel);
    }

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
        }
    }
}