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

        // Draw axes
        g2.setColor(Color.BLACK);
        g2.drawLine(50, height - 50, width - 50, height - 50); // X-axis
        g2.drawLine(50, 50, 50, height - 50);                  // Y-axis

        g2.drawString("Instability", width / 2, height - 20);
        g2.drawString("Abstractness", 10, height / 2);

        // Draw data points
        List<Square> squares = Blackboard.getInstance().getSquares();
        for (Square s : squares) {
            Metrics metrics = s.getMetrics();

            double a = metrics.calculateAbstractness();   // <-- uses Metrics logic
            double i = metrics.calculateInstability();    // <-- uses Metrics logic

            // Map to panel coordinates
            int x = 50 + (int) (a * (width - 100));
            int y = height - 50 - (int) (i * (height - 100));

            g2.setColor(Color.RED);
            g2.fillOval(x - 5, y - 5, 10, 10);

            g2.setColor(Color.BLACK);
            g2.drawString(s.getName(), x + 5, y - 5);

             double d = metrics.calculateDistance();
             g2.drawString(String.format("D=%.2f", d), x + 5, y + 15);
        }
    }

}
