package lk.ijse.hospitel.entity;

public class Patient implements SuperEntity{
    private String pId;
    private String name;
    private String address;
    private String phone;
    private int age;
    private String disease;
    private String gender;

    public Patient() {
    }

    public Patient(String pId, String name, String address, String phone, int age, String disease, String gender) {
        this.pId = pId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.disease = disease;
        this.gender = gender;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
