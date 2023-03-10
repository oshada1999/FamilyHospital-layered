package lk.ijse.hospitel.entity;

public class Medicine implements SuperEntity{
    private String mId;
    private String name;
    private String type;
    private String dose;
    private int qtyOnHand;
    private double unitPrice;

    public Medicine() {
    }

    public Medicine(String mId, String name, String type, String dose, int qtyOnHand, double unitPrice) {
        this.mId = mId;
        this.name = name;
        this.type = type;
        this.dose = dose;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
