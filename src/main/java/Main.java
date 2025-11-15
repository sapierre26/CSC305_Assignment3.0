import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public Main() {
        JTextField urlField = new JTextField();
        JButton okbutton = new JButton("OK");
        JPanel gridPanel = new GridPanel();
        TheNanny controller = new TheNanny(urlField);

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("URL: "), BorderLayout.WEST);
        top.add(urlField, BorderLayout.CENTER);
        top.add(okbutton, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        okbutton.addActionListener(controller);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setTitle("Code Visualizer");
        main.setSize(800, 400);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}
