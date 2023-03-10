package lk.ijse.hospitel.dto;

import java.util.ArrayList;

public class PlaceOrderDTO {
    private String patientId;
    private String orderId;
    private ArrayList<OrderDetailsDTO> orderDetails=new ArrayList<>();

    public PlaceOrderDTO() {
    }

    public PlaceOrderDTO(String patientId, String orderId, ArrayList<OrderDetailsDTO> orderDetails) {
        this.patientId = patientId;
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ArrayList<OrderDetailsDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetailsDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "patientId='" + patientId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
