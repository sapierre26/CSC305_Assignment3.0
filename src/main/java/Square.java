public class Square {
    private final String path;
    private final int lines;

    public Square(String path, int lines) {
        this.path = path;
        this.lines = lines;
    }

    public int getLinesOfCode() {
        return lines;
    }

    public String getName() {
        return path.substring(path.lastIndexOf("/") + 1);
    }
}
