package lk.ijse.hospitel.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PharmacistWindowController {

    public AnchorPane pharmacistContext;
    @FXML
    private Label lblTime;

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

    @FXML
    private AnchorPane dashBoardContext;

    @FXML
    void dashboardOnAction(ActionEvent event) {

    }

    @FXML
    void logoutOnAction(MouseEvent event) throws IOException {
        Navigation.navigate(Role.LOGOUT,pharmacistContext);
    }

    @FXML
    void orderOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Role.ORDER,dashBoardContext);
    }

}

