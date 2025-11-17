import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
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

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultTreeModel repoTree = new DefaultTreeModel(root);
        JTree tree = new JTree(repoTree);

        JScrollPane scroll = new JScrollPane(tree);
        scroll.setPreferredSize(new Dimension(200, 400));
        add(scroll, BorderLayout.WEST);

        JTabbedPane mainTabs = new JTabbedPane();
        mainTabs.addTab("Grid", new JPanel());
        mainTabs.addTab("Metrics", new JPanel());
        mainTabs.addTab("Diagram", new JPanel());
        add(mainTabs, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setTitle("Code Visualizer");
        main.setSize(800, 400);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}
