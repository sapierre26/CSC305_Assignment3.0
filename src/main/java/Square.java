import java.util.Collection;

public class Square {
    private final String path;
    private final int lines;
    private final Metrics metrics;

    public Square(String path, int lines) {
        this.path = path;
        this.lines = lines;
        this.metrics = new Metrics();
    }

    public int getLinesOfCode() {
        return lines;
    }

    public String getName() {
        return path.substring(path.lastIndexOf("/") + 1);
    }

    public String getPath() {
        return path;
    }

    public Metrics getMetrics() {
        return metrics;
    }
}
