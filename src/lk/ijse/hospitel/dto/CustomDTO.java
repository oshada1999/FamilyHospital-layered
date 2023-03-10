package lk.ijse.hospitel.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class CustomDTO {
    private String aId;
    private String pName;
    private String patientID;
    private int age;
    private String pAddress;
    private String dName;
    private String doctorID;
    private String date;

    private String doctorId;
    private String name;
    private String address;
    private String contact;
    private String day;
    private String startTime;
    private String endTime;
    private String department;
    private String gender;

    private String id;
    private String type;
    private String dose;
    private int qty;
    private double unitPrice;

    private String orderIdd;
    private String mediCode;
    private String mediName;

    private String orderId;

    private LocalDate orderDate;
    private String patientId;

    private String patientName;
    private int patientAge;
    private String patientAddress;
    private String doctorName;

    private String paymentID;
    private double channelling_cost;
    private double other_cost;
    private double total;
    private String appointmentID;


    private ArrayList<OrderDetailsDTO> orderDetails=new ArrayList<>();

    private String receiptId;
    private ArrayList<ReceiptDetailsDTO> receiptDetail=new ArrayList<>();

    private String medicineCode;
    private String medicineName;

    private String receiptID;

    public CustomDTO() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getOrderIdd() {
        return orderIdd;
    }

    public void setOrderIdd(String orderIdd) {
        this.orderIdd = orderIdd;
    }

    public String getMediCode() {
        return mediCode;
    }

    public void setMediCode(String mediCode) {
        this.mediCode = mediCode;
    }

    public String getMediName() {
        return mediName;
    }

    public void setMediName(String mediName) {
        this.mediName = mediName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public double getChannelling_cost() {
        return channelling_cost;
    }

    public void setChannelling_cost(double channelling_cost) {
        this.channelling_cost = channelling_cost;
    }

    public double getOther_cost() {
        return other_cost;
    }

    public void setOther_cost(double other_cost) {
        this.other_cost = other_cost;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public ArrayList<OrderDetailsDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetailsDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public ArrayList<ReceiptDetailsDTO> getReceiptDetail() {
        return receiptDetail;
    }

    public void setReceiptDetail(ArrayList<ReceiptDetailsDTO> receiptDetail) {
        this.receiptDetail = receiptDetail;
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "aId='" + aId + '\'' +
                ", pName='" + pName + '\'' +
                ", patientID='" + patientID + '\'' +
                ", age=" + age +
                ", pAddress='" + pAddress + '\'' +
                ", dName='" + dName + '\'' +
                ", doctorID='" + doctorID + '\'' +
                ", date='" + date + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", day='" + day + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", department='" + department + '\'' +
                ", gender='" + gender + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", dose='" + dose + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", orderIdd='" + orderIdd + '\'' +
                ", mediCode='" + mediCode + '\'' +
                ", mediName='" + mediName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientAge=" + patientAge +
                ", patientAddress='" + patientAddress + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", paymentID='" + paymentID + '\'' +
                ", channelling_cost=" + channelling_cost +
                ", other_cost=" + other_cost +
                ", total=" + total +
                ", appointmentID='" + appointmentID + '\'' +
                ", orderDetails=" + orderDetails +
                ", receiptId='" + receiptId + '\'' +
                ", receiptDetail=" + receiptDetail +
                ", medicineCode='" + medicineCode + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", receiptID='" + receiptID + '\'' +
                '}';
    }
}
