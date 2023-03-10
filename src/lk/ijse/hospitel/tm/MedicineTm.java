package lk.ijse.hospitel.tm;

import javafx.scene.control.Button;

public class MedicineTm {
    private String id;
    private String name;
    private String type;
    private String dose;
    private int qty;
    private double unitPrice;
    private Button btn;

    public MedicineTm() {
    }

    public MedicineTm(String id, String name, String type, String dose, int qty, double unitPrice, Button btn) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.dose = dose;
        this.qty = qty;
        this.unitPrice = unitPrice;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "MedicineTm{" +
                "id='" + id + '\'' +
                ", description='" + name + '\'' +
                ", type='" + type + '\'' +
                ", dose='" + dose + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", btn=" + btn +
                '}';
    }
}
