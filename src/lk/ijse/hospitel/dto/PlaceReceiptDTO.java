package lk.ijse.hospitel.dto;

import java.util.ArrayList;

public class PlaceReceiptDTO {
    private String patientId;
    private String receiptId;
    private ArrayList<ReceiptDetailsDTO> receiptDetail=new ArrayList<>();

    public PlaceReceiptDTO() {
    }

    public PlaceReceiptDTO(String patientId, String receiptId, ArrayList<ReceiptDetailsDTO> receiptDetail) {
        this.patientId = patientId;
        this.receiptId = receiptId;
        this.receiptDetail = receiptDetail;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public ArrayList<ReceiptDetailsDTO> getReceiptDetail() {
        return receiptDetail;
    }

    public void setReceiptDetail(ArrayList<ReceiptDetailsDTO> receiptDetail) {
        this.receiptDetail = receiptDetail;
    }

    @Override
    public String toString() {
        return "PlaceReceipt{" +
                "patientId='" + patientId + '\'' +
                ", receiptId='" + receiptId + '\'' +
                ", receiptDetail=" + receiptDetail +
                '}';
    }
}
