package lk.ijse.hospitel.entity;

public class Payment implements SuperEntity{
    private String payId;
    private String date;
    private double channelling_cost;
    private double other_cost;
    private double total;
    private String aId;

    public Payment() {
    }

    public Payment(String payId, String date, double channelling_cost, double other_cost, double total, String aId) {
        this.payId = payId;
        this.date = date;
        this.channelling_cost = channelling_cost;
        this.other_cost = other_cost;
        this.total = total;
        this.aId = aId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }
}
