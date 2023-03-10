package lk.ijse.hospitel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.NavigableMap;

public class Navigation {
    public static AnchorPane pane;

    public static void navigate(Role role,AnchorPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (role){
            case ADMIN:
                iniUI("AdminWindowForm.fxml");
                break;
            case DOCTOR:
                iniUI("DoctorWindowForm.fxml");
                break;
            case PHARMACIST:
                iniUI("PharmacistWindow.fxml");
                break;
            case RECEPTIONIST:
                iniUI("ReceptionForm.fxml");
                break;
            case LOGOUT:
                iniUI("SigninWindowForm.fxml");
                break;
            case ADD_DOCTOR:
                iniUI("AddDoctorWindowForm.fxml");
                break;
            case ADMIN_DASH:
                iniUI("AdminDashboardForm.fxml");
                break;
            case SIGNUP:
                iniUI("");
                break;
            case ADD_NURSE:
                iniUI("AddNurseForm.fxml");
                break;
            case ADD_PHARMACIST:
                iniUI("AddPharmacistForm.fxml");
                break;
            case ADD_RECEPTIONIST:
                iniUI("AddreceptionistForm.fxml");
                break;
            case ADD_MEDICINE:
                iniUI("AddMedicineForm.fxml");
                break;
            case ADD_SUPPLIER:
                iniUI("AddSupplierForm.fxml");
                break;
            case DOCTOR_AVAILABILITY:
                iniUI("AvailabilityForm.fxml");
                break;
            case RECEPTION_DASH:
                iniUI("ReceptionDashboardForm.fxml");
                break;
            case ADD_PATIENT:
                iniUI("AddPatientWindowForm.fxml");
                break;
            case APPOINTMENT:
                iniUI("AddAppointmentForm.fxml");
                break;
            case PAYMENT_RECEP:
                iniUI("AddPaymentForm.fxml");
                break;
            case PATIENT_DETAILS:
                iniUI("PatientDetailsForm.fxml");
                break;
            case RECEIPT:
                iniUI("receiptForm.fxml");
                break;
            case CHOOSE_APPOINTMENT:
                iniUI("ChoseAppointmentForm.fxml");
                break;
            case ORDER:
                iniUI("MedicineOrders.fxml");
                break;
        }
    }
    public static void iniUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/hospitel/view/"+location)));
    }
}
