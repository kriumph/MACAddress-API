package MacAddress.Presenter;

public class MacAddressInfo {
    private MacAddressDetails macAddressDetails;
    private BlockDetails blockDetails;
    private VendorDetails vendorDetails;
    private String macAddressInfo;

    public MacAddressDetails getMacAddressDetails() {
        return macAddressDetails;
    }

    public void setMacAddressDetails(MacAddressDetails macAddressDetails) {
        this.macAddressDetails = macAddressDetails;
        this.macAddressInfo = "";
    }

    public BlockDetails getBlockDetails() {
        return blockDetails;
    }

    public void setBlockDetails(BlockDetails blockDetails) {
        this.blockDetails = blockDetails;
    }

    public VendorDetails getVendorDetails() {
        return vendorDetails;
    }

    public void setVendorDetails(VendorDetails vendorDetails) {
        this.vendorDetails = vendorDetails;
    }

    public String getMacAddressInfo(){ return this.macAddressInfo; }

    public void setMacAddressInfo(String macAddressInfo) {this.macAddressInfo = macAddressInfo;}

    public void isAfterIndicatedYear(boolean isAfterIndicatedYear){
        if(isAfterIndicatedYear){
            this.setMacAddressInfo(this.getMacAddressDetails().toString().replace("Mac Address Details\n", "Mac Address Details (Too New)\n"));
        }else{
            this.setMacAddressInfo(this.getMacAddressDetails().toString());
        }
    }

    @Override
    public String toString(){
        if (this.getMacAddressDetails().getIsValid().equals("false")) {
            this.setMacAddressInfo("The MAC Address is invalid.");
        }else {
            String newString = this.getMacAddressInfo() +
                    getBlockDetails().toString() +
                    this.getVendorDetails().toString();
            this.setMacAddressInfo(newString);
        }
        return this.getMacAddressInfo();
    }

}
