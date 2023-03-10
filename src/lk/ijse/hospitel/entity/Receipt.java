package lk.ijse.hospitel.entity;

import java.time.LocalDate;

public class Receipt implements SuperEntity{
    private String ReID;
    private LocalDate date;
    private String patientId;

    public Receipt() {
    }

    public Receipt(String reID, LocalDate date, String patientId) {
        ReID = reID;
        this.date = date;
        this.patientId = patientId;
    }

    public String getReID() {
        return ReID;
    }

    public void setReID(String reID) {
        ReID = reID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
