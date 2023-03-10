package lk.ijse.hospitel.dto;

public class NurseDTO {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String department;
    private String gender;

    public NurseDTO() {
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public NurseDTO(String id, String name, String address, String contact, String department, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.department = department;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
