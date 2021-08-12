package MacAddress.Presenter;

import java.util.List;

public class MacAddressDetails {
    private String searchTerm;
    private String isValid;
    private String virtualMachine;
    private List<String> applications;
    private String transmissionType;
    private String administrationType;
    private String wiresharkNotes;
    private String comment;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getVirtualMachine() {
        return virtualMachine;
    }

    public void setVirtualMachine(String virtualMachine) {
        this.virtualMachine = virtualMachine;
    }

    public List<String> getApplications() {
        return applications;
    }

    public void setApplications(List<String> applications) {
        this.applications = applications;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getAdministrationType() {
        return administrationType;
    }

    public void setAdministrationType(String administrationType) {
        this.administrationType = administrationType;
    }

    public String getWiresharkNotes() {
        return wiresharkNotes;
    }

    public void setWiresharkNotes(String wiresharkNotes) {
        this.wiresharkNotes = wiresharkNotes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        String macAddressDetails = "---------------------------------------------------------------------------------\n\n" +
                "Mac Address Details\n" +
                "searchTerm: " + getSearchTerm() + "\n" +
                "isValid: " + getIsValid() + "\n" +
                "virtualMachine: " + getVirtualMachine() + "\n" +
                "applications: " + getApplications() + "\n" +
                "transmissionType: " + getTransmissionType() + "\n" +
                "administrationType: " + getAdministrationType() + "\n" +
                "wiresharkNotes: " + getWiresharkNotes() + "\n" +
                "comment: " + getComment() + "\n\n";
        return macAddressDetails;
    }
}
