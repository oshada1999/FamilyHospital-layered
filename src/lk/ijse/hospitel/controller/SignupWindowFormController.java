package lk.ijse.hospitel.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;

import java.io.IOException;

public class SignupWindowFormController {
    public JFXComboBox<String> cmbRole;
    public AnchorPane signupContext;

    public void initialize(){
        cmbRole.getItems().addAll("admin","receptionist","pharmacist","doctor");

    }

    public void signupOnAction(ActionEvent actionEvent) throws IOException {
        signupContext.getChildren().clear();
        signupContext.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/hospitel/view/SigninWindowForm.fxml")));
    }

    public void backOnAcction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Role.LOGOUT,signupContext);
        /*signupContext.getChildren().clear();
        signupContext.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/hospitel/view/SigninWindowForm.fxml")));*/
    }
}
