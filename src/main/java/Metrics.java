public class Metrics {
    private int totalClasses = 0;
    private int totalAbstractClasses = 0;
    private int incomingDependencies = 0;
    private int outgoingDependencies = 0;

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public void setTotalAbstractClasses(int totalAbstractClasses) {
        this.totalAbstractClasses = totalAbstractClasses;
    }

    public double calculateAbstractness() {
        if (totalClasses == 0) {
            return 0;
        }
        return (double) totalAbstractClasses / totalClasses;
    }

    public double calculateInstability() {
        int totalDependencies = incomingDependencies + outgoingDependencies;
        if (totalDependencies == 0) {
            return 0;
        }
        return (double) outgoingDependencies / totalDependencies;
    }

    public double calculateDistance() {
        return Math.abs(calculateAbstractness() + calculateInstability() - 1);
    }
}
