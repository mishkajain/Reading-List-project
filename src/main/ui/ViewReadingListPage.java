package ui;

import javax.swing.*;
import java.awt.*;

public class ViewReadingListPage {
    private JPanel panel1;
    private JLabel returnLabel;
    private JScrollPane scrollPane;

    public ViewReadingListPage() {
        createUIComponents();
    }

    private void createUIComponents() {
        panel1 = new JPanel();
        panel1.setVisible(true);
        panel1.setPreferredSize(new Dimension(500, 600));
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel1.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(480, 500));

    }
}
