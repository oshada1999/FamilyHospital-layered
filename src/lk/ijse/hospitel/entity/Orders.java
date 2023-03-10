package lk.ijse.hospitel.entity;

import java.time.LocalDate;

public class Orders implements SuperEntity{
    private String orderId;
    private LocalDate orderDate;
    private String patientId;

    public Orders() {
    }

    public Orders(String orderId, LocalDate orderDate, String patientId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.patientId = patientId;
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
}
