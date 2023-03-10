package lk.ijse.hospitel.dto;

public class PatientPaidDetailsDTO {
    private String patientName;
    private int patientAge;
    private String patientAddress;
    private String doctorName;
    private String date;

    public PatientPaidDetailsDTO() {
    }

    public PatientPaidDetailsDTO(String patientName, int patientAge, String patientAddress, String doctorName, String date) {
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientAddress = patientAddress;
        this.doctorName = doctorName;
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PatientDetails{" +
                "patientName='" + patientName + '\'' +
                ", patientAge=" + patientAge +
                ", patientAddress='" + patientAddress + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
