public class Metrics {
    private int totalClasses = 0;
    private int totalAbstractClasses = 0;
    private int incomingDependencies = 0;
    private int outgoingDependencies = 0;

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public int getTotalAbstractClasses() {
        return totalAbstractClasses;
    }

    public void setTotalAbstractClasses(int totalAbstractClasses) {
        this.totalAbstractClasses = totalAbstractClasses;
    }

    public int getIncomingDependencies() {
        return incomingDependencies;
    }

    public void setIncomingDependencies(int incomingDependencies) {
        this.incomingDependencies = incomingDependencies;
    }

    public int getOutgoingDependencies() {
        return outgoingDependencies;
    }

    public void setOutgoingDependencies(int outgoingDependencies) {
        this.outgoingDependencies = outgoingDependencies;
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
