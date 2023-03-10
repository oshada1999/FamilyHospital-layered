package lk.ijse.hospitel.dto;

import java.time.LocalDate;

public class OrderDTO {
    private String orderId;
    private LocalDate orderDate;
    private String patientId;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, LocalDate orderDate, String patientId) {
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", patientId='" + patientId + '\'' +
                '}';
    }
}
