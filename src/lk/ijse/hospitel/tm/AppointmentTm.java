package lk.ijse.hospitel.tm;


import javafx.scene.control.Button;

public class AppointmentTm {
    private String appointmentID;
    private String patientName;
    private String patientID;
    private int age;
    private String patientAddress;
    private String doctorName;
    private String doctorID;
    private String date;
    private Button btn;

    public AppointmentTm() {
    }

    public AppointmentTm(String aId, String pName, String patientID, int age, String pAddress, String dName, String doctorID, String date, Button btn) {
        this.appointmentID = aId;
        this.patientName = pName;
        this.patientID = patientID;
        this.age = age;
        this.patientAddress = pAddress;
        this.doctorName = dName;
        this.doctorID = doctorID;
        this.date = date;
        this.btn = btn;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "AppointmentTm{" +
                "aId='" + appointmentID + '\'' +
                ", pName='" + patientName + '\'' +
                ", patientID='" + patientID + '\'' +
                ", age=" + age +
                ", pAddress='" + patientAddress + '\'' +
                ", dName='" + doctorName + '\'' +
                ", doctorID='" + doctorID + '\'' +
                ", date='" + date + '\'' +
                ", btn=" + btn +
                '}';
    }
}


