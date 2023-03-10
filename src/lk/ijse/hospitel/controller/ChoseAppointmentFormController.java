package lk.ijse.hospitel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hospitel.tm.AppointmentTm;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;

import java.io.IOException;
import java.sql.SQLException;

public class ChoseAppointmentFormController extends AddAppointmentFormController {

    public AnchorPane chooseContext;
    public TableColumn colPatientId;
    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<AppointmentTm> tblAppointment;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colPatientName;

    @FXML
    private TableColumn<?, ?> colDiseasep;

    @FXML
    private TableColumn<?, ?> colAgep;

    @FXML
    private TableColumn<?, ?> colpAddress;

    @FXML
    private TableColumn<?, ?> colDName;

    @FXML
    private TableColumn<?, ?> colDDepartment;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private Label lblAppointment;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblDoctorName;

    @FXML
    private Label lblPatientDisease;

    @FXML
    private Label lblDoctorDepartment;

    @FXML
    private Label lblAddress;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private Label lblAge;
    private String searchText="";
    static boolean isChoose;
    public void initialize() throws SQLException, ClassNotFoundException {
        loadNextAppointmentId();
        setCellValueApp();
        loadAppointment(searchText);
        SearchListener();
        setListenerApp();
    }


    void setCellValueApp() {
        colID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        colAgep.setCellValueFactory(new PropertyValueFactory<>("age"));
        colpAddress.setCellValueFactory(new PropertyValueFactory<>("patientAddress"));
        colDName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colDoctorId.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    @FXML
    public void paymentOnAction(ActionEvent event) throws IOException {
        if(selectAppointmentID==null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "please select patient appointment");
            alert.show();
        }else {
            Navigation.navigate(Role.PAYMENT_RECEP, chooseContext);
            isChoose=true;
        }
    }
}
