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

import javax.naming.Name;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DoctorWindowFormController {
    public AnchorPane DoctorContext;
    public AnchorPane dashBoardContext;
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

    public void logoutOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Role.LOGOUT,DoctorContext);
    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.DOCTOR,DoctorContext);
    }

    public void appointmentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.PATIENT_DETAILS,dashBoardContext);
    }

    public void receiptOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.RECEIPT,dashBoardContext);
    }
}
