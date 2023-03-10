package lk.ijse.hospitel.dto;

public class DoctorDTO {
    private String doctorId;
    private String name;
    private String address;
    private String contact;
    private String day;
    private String startTime;
    private String endTime;
    private String department;
    private String gender;

    public DoctorDTO() {
    }

    public DoctorDTO(String doctorId, String name, String address, String contact, String day, String startTime, String endTime, String department, String gender) {
        this.doctorId = doctorId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.department = department;
        this.gender = gender;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", day='" + day + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
