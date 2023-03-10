package lk.ijse.hospitel.tm;

import javafx.scene.control.Button;

public class PlaceOrderTm {
    private String code;
    private String name;
    private int qty;
    private String type;
    private String dose;
    private double unitPrice;
    private double total;
    private Button btnDelete;

    public PlaceOrderTm() {
    }

    public PlaceOrderTm(String code, String name, int qty, String type, String dose, double unitPrice, double total, Button btnDelete) {
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.type = type;
        this.dose = dose;
        this.unitPrice = unitPrice;
        this.total = total;
        this.btnDelete = btnDelete;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "PlaceOrderTm{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", type='" + type + '\'' +
                ", dose='" + dose + '\'' +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
