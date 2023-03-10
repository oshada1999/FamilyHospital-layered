package lk.ijse.hospitel.dto;

public class AppointmentDTO {
    private String aId;
    private String pName;
    private String patientID;
    private int age;
    private String pAddress;
    private String dName;
    private String doctorID;
    private String date;

    public AppointmentDTO() {
    }

    public AppointmentDTO(String aId, String pName, String patientID, int age, String pAddress, String dName, String doctorID, String date) {
        this.aId = aId;
        this.pName = pName;
        this.patientID = patientID;
        this.age = age;
        this.pAddress = pAddress;
        this.dName = dName;
        this.doctorID = doctorID;
        this.date = date;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
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

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
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

    @Override
    public String toString() {
        return "Appointment{" +
                "aId='" + aId + '\'' +
                ", pName='" + pName + '\'' +
                ", patientID='" + patientID + '\'' +
                ", age=" + age +
                ", pAddress='" + pAddress + '\'' +
                ", dName='" + dName + '\'' +
                ", doctorID='" + doctorID + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
