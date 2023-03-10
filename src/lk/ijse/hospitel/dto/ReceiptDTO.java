package lk.ijse.hospitel.dto;

import java.time.LocalDate;

public class ReceiptDTO {
    private String receiptID;
    private LocalDate date;
    private String patientID;

    public ReceiptDTO() {
    }

    public ReceiptDTO(String receiptID, LocalDate date, String patientID) {
        this.receiptID = receiptID;
        this.date = date;
        this.patientID = patientID;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptID='" + receiptID + '\'' +
                ", date=" + date +
                ", patientID='" + patientID + '\'' +
                '}';
    }
}
