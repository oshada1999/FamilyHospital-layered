package lk.ijse.hospitel.tm;

public class PatientPaidDetailsTm {
    private String patientName;
    private String patientId;
    private int patientAge;
    private String patientAddress;
    private String doctorName;
    private String doctorId;
    private String date;

    public PatientPaidDetailsTm() {
    }

    public PatientPaidDetailsTm(String patientName, String patientId, int patientAge, String patientAddress, String doctorName, String doctorId, String date) {
        this.patientName = patientName;
        this.patientId = patientId;
        this.patientAge = patientAge;
        this.patientAddress = patientAddress;
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PatientPaidDetailsTm{" +
                "patientName='" + patientName + '\'' +
                ", patientId='" + patientId + '\'' +
                ", patientAge=" + patientAge +
                ", patientAddress='" + patientAddress + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
