package MacAddress.Presenter;

public class BlockDetails {
    private String blockFound;
    private String borderLeft;
    private String borderRight;
    private String blockSize;
    private String assignmentBlockSize;
    private String dateCreated;
    private String dateUpdated;


    public String getBlockFound() {
        return blockFound;
    }

    public void setBlockFound(String blockFound) {
        this.blockFound = blockFound;
    }

    public String getBorderLeft() {
        return borderLeft;
    }

    public void setBorderLeft(String borderLeft) {
        this.borderLeft = borderLeft;
    }

    public String getBorderRight() {
        return borderRight;
    }

    public void setBorderRight(String borderRight) {
        this.borderRight = borderRight;
    }

    public String getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(String blockSize) {
        this.blockSize = blockSize;
    }

    public String getAssignmentBlockSize() {
        return assignmentBlockSize;
    }

    public void setAssignmentBlockSize(String assignmentBlockSize) {
        this.assignmentBlockSize = assignmentBlockSize;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public String toString(){
        String blockDetails = "Block Details\n" +
                "blockFound: " + getBlockFound() + "\n" +
                "borderLeft: " + getBorderLeft() + "\n" +
                "borderRight: " + getBorderRight() + "\n" +
                "blockSize: " + getBlockSize() + "\n" +
                "assignmentBlockSize: " + getAssignmentBlockSize() + "\n" +
                "dateCreated: " + getDateCreated() + "\n" +
                "dateUpdated: " + getDateUpdated() + "\n\n";
        return blockDetails;
    }

}
