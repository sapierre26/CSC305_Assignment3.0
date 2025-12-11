import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.slf4j.LoggerFactory;


public class DiagramPanel extends JPanel {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(DiagramPanel.class);
    private BufferedImage image;

    public DiagramPanel(String umlSource) {
        setBackground(Color.WHITE);
        try {
            logger.info("Generating UML diagram...");
            SourceStringReader reader = new SourceStringReader(umlSource);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            reader.outputImage(os, new FileFormatOption(FileFormat.PNG));
            os.close();
            image = ImageIO.read(new ByteArrayInputStream(os.toByteArray()));
            logger.info("UML diagram generated successfully.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error generating UML diagram", e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (image != null) {
            return new Dimension(image.getWidth(), image.getHeight());
        }
        return super.getPreferredSize();
    }
}
