package lk.ijse.hospitel.entity;

public class Nurse implements SuperEntity{
    private String nId;
    private String name;
    private String address;
    private String contact;
    private String department;
    private String gender;

    public Nurse() {
    }

    public Nurse(String nId, String name, String address, String contact, String department, String gender) {
        this.nId = nId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.department = department;
        this.gender = gender;
    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
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
}
