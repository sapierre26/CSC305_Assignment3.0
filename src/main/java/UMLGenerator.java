import java.util.List;

public class UMLGenerator {

    /**
     * Generates PlantUML markup for all Squares in the Blackboard.
     * Each Square corresponds to a Java file.
     * Abstract classes are marked appropriately.
     *
     * @return PlantUML string
     */
    public static String generatePlantUML() {
        StringBuilder sb = new StringBuilder();
        sb.append("@startuml\n");
        sb.append("!pragma layout smetana\n"); // optional layout optimization

        List<Square> squares = Blackboard.getInstance().getSquares();

        for (Square s : squares) {
            String className = s.getName().replace(".java", "");

            // Detect abstract classes
            Metrics metrics = s.getMetrics();
            if (s.getPath() != null && s.getPath().contains("abstract class")) {
                sb.append("abstract class ").append(className).append("\n");
            } else {
                sb.append("class ").append(className).append("\n");
            }

            // Example: could detect interfaces if needed
            if (s.getPath() != null && s.getPath().contains("interface")) {
                sb.append("interface ").append(className).append("\n");
            }
        }

        // Optional: add mock relationships
        sb.append("// relationships example\n");
        // For now, you could later parse 'extends', 'implements', and associations

        sb.append("@enduml\n");
        return sb.toString();
    }
}