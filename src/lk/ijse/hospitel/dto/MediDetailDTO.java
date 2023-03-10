package lk.ijse.hospitel.dto;

public class MediDetailDTO {
    private String orderIdd;
    private String mediCode;
    private int qty;
    private String mediName;
    private double unitPrice;

    public MediDetailDTO() {
    }

    public MediDetailDTO(String orderIdd, String mediCode, int qty, String mediName, double unitPrice) {
        this.orderIdd = orderIdd;
        this.mediCode = mediCode;
        this.qty = qty;
        this.mediName = mediName;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getMediName() {
        return mediName;
    }

    public void setMediName(String mediName) {
        this.mediName = mediName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "mediDetail{" +
                "orderIdd='" + orderIdd + '\'' +
                ", mediCode='" + mediCode + '\'' +
                ", qty=" + qty +
                ", mediName='" + mediName + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
