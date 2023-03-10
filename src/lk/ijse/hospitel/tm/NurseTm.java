package lk.ijse.hospitel.tm;

import javafx.scene.control.Button;

public class NurseTm {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String department;
    private String gender;
    private Button btn;

    public NurseTm() {
    }

    public NurseTm(String id, String name, String address, String contact, String department, String gender, Button btn) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.department = department;
        this.gender = gender;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "NurseTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                ", btn=" + btn +
                '}';
    }
}
