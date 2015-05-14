package model;

import java.io.Serializable;

public class TopUp implements Serializable {
    String serviceName;
    String mobileNumber;
    int amount;

    public TopUp() {
    }

    public TopUp(String serviceName, String mobileNumber, int amount) {
        this.serviceName = serviceName;
        this.mobileNumber = mobileNumber;
        this.amount = amount;
    }

    
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toString() {
        return String.format("%s TopUp for Mobile number %s for QR %d",
                serviceName, mobileNumber, amount);
    }
}
