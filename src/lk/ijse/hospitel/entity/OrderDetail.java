package lk.ijse.hospitel.entity;

public class OrderDetail implements SuperEntity{
    private String orderID;
    private String mediCode;
    private String name;
    private String type;
    private String dose;
    private int orderQTY;
    private double unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(String orderID, String mediCode, String name, String type, String dose, int orderQTY, double unitPrice) {
        this.orderID = orderID;
        this.mediCode = mediCode;
        this.name = name;
        this.type = type;
        this.dose = dose;
        this.orderQTY = orderQTY;
        this.unitPrice = unitPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getMediCode() {
        return mediCode;
    }

    public void setMediCode(String mediCode) {
        this.mediCode = mediCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
