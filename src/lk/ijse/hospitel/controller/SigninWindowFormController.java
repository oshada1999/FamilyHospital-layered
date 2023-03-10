package lk.ijse.hospitel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;

import java.io.IOException;
import java.util.regex.Pattern;

public class SigninWindowFormController {
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXComboBox<String> cmbRole;
    public AnchorPane signinContext;
    private Pattern userNamePattern;
    private Pattern passwordPattern;


    public void initialize(){
        cmbRole.getItems().addAll("admin","receptionist","pharmacist","doctor");
        userNamePattern=Pattern.compile("^[a-z0-9]{4,}$");
        passwordPattern=Pattern.compile("^[a-zA-Z0-9_]{8,}$");
    }

    public void signinOnAction(ActionEvent actionEvent) throws IOException {
        boolean isUsernameMatched = userNamePattern.matcher(txtUsername.getText()).matches();
        boolean isPasswordMatched = passwordPattern.matcher(txtPassword.getText()).matches();
        if (isUsernameMatched){
            if (isPasswordMatched){
                String role = cmbRole.getSelectionModel().getSelectedItem().toString();
                if (role.equals("doctor")){

                    Navigation.navigate(Role.DOCTOR,signinContext);
                }else if (role.equals("admin")){
                    Navigation.navigate(Role.ADMIN,signinContext);
                }else if (role.equals("pharmacist")){
                    Navigation.navigate(Role.PHARMACIST,signinContext);
                }else {
                    Navigation.navigate(Role.RECEPTIONIST,signinContext);
                }
            }else {
                txtPassword.setFocusColor(Paint.valueOf("Red"));
                txtPassword.requestFocus();
            }
        }else {
            txtUsername.setFocusColor(Paint.valueOf("Red"));
            txtUsername.requestFocus();
        }

    }

    public void signupOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Role.SIGNUP,signinContext);
        /*signinContext.getChildren().clear();
        signinContext.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/hospitel/view/SignupWindowForm.fxml")));*/
    }
}
