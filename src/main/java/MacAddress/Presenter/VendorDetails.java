package MacAddress.Presenter;

public class VendorDetails {
    private String oui;
    private String isPrivate;
    private String companyName;
    private String companyAddress;
    private String countryCode;

    public String getOui() {
        return oui;
    }

    public void setOui(String oui) {
        this.oui = oui;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString(){
        String vendorDetails = "Vendor Details\n" +
                "oui: " + getOui() + "\n" +
                "isPrivate: " + getIsPrivate() + "\n" +
                "companyName: " + getCompanyName() + "\n" +
                "companyAddress: " + getCompanyAddress() + "\n" +
                "countryCode: " + getCountryCode() + "\n\n" +
                "---------------------------------------------------------------------------------\n\n" +
                "Next Mac Address Information will be after here\n\n";
        return vendorDetails;
    }
}
