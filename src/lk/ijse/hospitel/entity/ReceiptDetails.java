package lk.ijse.hospitel.entity;

public class ReceiptDetails implements SuperEntity{
    private String mediCode;
    private String name;
    private String dose;
    private String receiptID;

    public ReceiptDetails() {
    }

    public ReceiptDetails(String mediCode, String name, String dose, String receiptID) {
        this.mediCode = mediCode;
        this.name = name;
        this.dose = dose;
        this.receiptID = receiptID;
    }

    public String getMediCode() {
        return mediCode;
    }

    public void setMediCode(String mediCode) {
        this.mediCode = mediCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }
}
