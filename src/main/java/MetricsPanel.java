import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class MetricsPanel extends JPanel implements PropertyChangeListener {
    public MetricsPanel() {
        Blackboard.getInstance().addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("blackboardReady")) {
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        g2.setColor(Color.BLACK);
        g2.drawLine(50, height - 50, width - 50, height - 50); // X-axis
        g2.drawLine(50, 50, 50, height - 50);                  // Y-axis

        g2.drawString("Instability", width / 2, height - 20);
        g2.drawString("Abstractness", 10, height / 2);

        List<Square> squares = Blackboard.getInstance().getSquares();
    }

}
