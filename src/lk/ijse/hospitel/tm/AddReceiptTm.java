package lk.ijse.hospitel.tm;

import javafx.scene.control.Button;

public class AddReceiptTm {
    private String medicineCode;
    private String medicineName;
    private String dose;
    private Button btn;

    public AddReceiptTm() {
    }

    public AddReceiptTm(String medicineCode, String medicineName, String dose, Button btn) {
        this.medicineCode = medicineCode;
        this.medicineName = medicineName;
        this.dose = dose;
        this.btn = btn;
    }

    public String getMedicineCode() {
        return medicineCode;
    }

    public void setMedicineCode(String medicineCode) {
        this.medicineCode = medicineCode;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "AddReceiptTm{" +
                "medicineCode='" + medicineCode + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", dose='" + dose + '\'' +
                ", btn=" + btn +
                '}';
    }
}
