package lk.ijse.hospitel.dto;

public class ReceiptDetailsDTO {
    private String medicineCode;
    private String medicineName;
    private String dose;
    private String receiptId;

    public ReceiptDetailsDTO() {
    }

    public ReceiptDetailsDTO(String medicineCode, String medicineName, String dose, String receiptId) {
        this.medicineCode = medicineCode;
        this.medicineName = medicineName;
        this.dose = dose;
        this.receiptId = receiptId;
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    @Override
    public String toString() {
        return "ReceiptDetails{" +
                "medicineCode='" + medicineCode + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", dose='" + dose + '\'' +
                ", receiptId='" + receiptId + '\'' +
                '}';
    }
}
