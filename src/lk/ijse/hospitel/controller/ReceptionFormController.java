package lk.ijse.hospitel.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NavigableMap;

import static lk.ijse.hospitel.controller.AvailabilityFormController.selectDoctorID;
import static lk.ijse.hospitel.controller.AddPatientWindowFormController.selectPatientID;
import static lk.ijse.hospitel.controller.ChoseAppointmentFormController.isChoose;

public class ReceptionFormController {
    public AnchorPane ReceptionContext;
    public AnchorPane dashBoardContext;
    public AnchorPane adminContext;
    public Label lblTime;

    public void initialize(){
        setDateAndTime();
    }
    public void setDateAndTime(){
        Timeline time=new Timeline(
                new KeyFrame(Duration.ZERO, e->{
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd  "+"|"+"  HH:mm:ss");
                    lblTime.setText(LocalDateTime.now().format(formatter));
                }),new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void addPationOnAction(ActionEvent actionEvent) throws IOException {
        selectDoctorID=null;
        selectPatientID=null;
        isChoose=false;
        Navigation.navigate(Role.DOCTOR_AVAILABILITY,dashBoardContext);
    }

    public void logoutOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Role.LOGOUT,ReceptionContext);
    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        selectDoctorID=null;
        selectPatientID=null;
        Navigation.navigate(Role.RECEPTION_DASH,dashBoardContext);
    }

    public void paymentOnAction(ActionEvent actionEvent) throws IOException {
        selectDoctorID=null;
        selectPatientID=null;
        Navigation.navigate(Role.PAYMENT_RECEP,dashBoardContext);
    }

    public void appointmentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.CHOOSE_APPOINTMENT,dashBoardContext);
    }
}
