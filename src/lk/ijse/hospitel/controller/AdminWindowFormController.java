package lk.ijse.hospitel.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminWindowFormController {
    public AnchorPane adminContext;
    public AnchorPane dashboardContext;
    public Label lblLabel;
    public Label lblLabel1;
    public AnchorPane anjID;
    public Label lblTime;

    public void initialize(){
        setDateAndTime();
    }
    public void setDateAndTime(){
        Timeline time=new Timeline(
                new KeyFrame(Duration.ZERO,e->{
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd  "+"|"+"  HH:mm:ss");
                    lblTime.setText(LocalDateTime.now().format(formatter));
                }),new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void logoutOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Role.LOGOUT,adminContext);
        /*adminContext.getChildren().clear();
        adminContext.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/hospitel/view/SigninWindowForm.fxml")));*/
    }

    public void doctorOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.ADD_DOCTOR,dashboardContext);
        /*dashboardContext.getChildren().clear();
        dashboardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/hospitel/view/AddDoctorWindowForm.fxml")));*/
    }

    public void onDashbordAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.ADMIN_DASH,dashboardContext);
        /*dashboardContext.getChildren().clear();
        dashboardContext.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/hospitel/view/AdminDashboardForm.fxml")));*/
    }

    public void medicineOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.ADD_MEDICINE,dashboardContext);
    }

    public void dashOnAction(ActionEvent actionEvent) {
    }

    public void nurseOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.ADD_NURSE,dashboardContext);
    }

    public void pharmacistOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.ADD_PHARMACIST,dashboardContext);
    }

    public void receptionistOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.ADD_RECEPTIONIST,dashboardContext);
    }

    public void incomeOnAction(ActionEvent actionEvent) {
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.ADD_SUPPLIER,dashboardContext);
    }

    public void onAction(ActionEvent actionEvent) {
    }

    public void mediOrderOnAction(ActionEvent actionEvent) {
    }
}
