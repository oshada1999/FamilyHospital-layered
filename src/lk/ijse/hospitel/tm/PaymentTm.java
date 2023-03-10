package lk.ijse.hospitel.tm;

public class PaymentTm {
    private String paymentID;
    private String date;
    private double channelling_cost;
    private double other_cost;
    private double total;
    private String appointmentID;

    public PaymentTm() {
    }

    public PaymentTm(String paymentID, String date, double channelling_cost, double other_cost, double total, String appointmentID) {
        this.paymentID = paymentID;
        this.date = date;
        this.channelling_cost = channelling_cost;
        this.other_cost = other_cost;
        this.total = total;
        this.appointmentID = appointmentID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
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

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    @Override
    public String toString() {
        return "PaymentTm{" +
                "paymentID='" + paymentID + '\'' +
                ", date='" + date + '\'' +
                ", channelling_cost=" + channelling_cost +
                ", other_cost=" + other_cost +
                ", total=" + total +
                ", appointmentID='" + appointmentID + '\'' +
                '}';
    }
}
