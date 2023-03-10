package lk.ijse.hospitel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hospitel.service.ServiceFactory;
import lk.ijse.hospitel.service.custom.AppointmentService;
import lk.ijse.hospitel.service.custom.DoctorService;
import lk.ijse.hospitel.service.custom.PatientService;

import lk.ijse.hospitel.tm.AppointmentTm;
import lk.ijse.hospitel.dto.AppointmentDTO;
import lk.ijse.hospitel.dto.DoctorDTO;
import lk.ijse.hospitel.dto.PatientDTO;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static lk.ijse.hospitel.controller.AvailabilityFormController.selectDoctorID;
public class AddAppointmentFormController extends AddPatientWindowFormController {

    public Label lblPatientDisease;
    public JFXDatePicker txtDatepic;
    public Label lblAddress;
    public Label lblAge;
    public Label lblAppointment;
    public TableColumn colpAddress;
    public TableColumn colDName;
    public TableColumn colDDepartment;
    public TableColumn colDiseasep;
    public TableColumn colAgep;
    public TableColumn colID;
    public TableColumn colDoctorId;
    public Label lblPatientID;
    public Label lblDoctorID;

    @FXML
    private AnchorPane appointmentContext;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<AppointmentTm> tblAppointment;

    @FXML
    private TableColumn<?, ?> colAppointmentID;

    @FXML
    private TableColumn<?, ?> colPatientID;

    @FXML
    private TableColumn<?, ?> colPatientName;

    @FXML
    private TableColumn<?, ?> colDoctorID;

    @FXML
    private TableColumn<?, ?> colDoctorName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private JFXComboBox<?> cmbPatientID;

    @FXML
    private JFXComboBox<?> cmbDoctorID;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblDoctorName;

    @FXML
    private Label lblPatientAddress;

    @FXML
    private Label lblDoctorDepartment;

    @FXML
    private JFXTextField txtDate;
    private String searchText="";
    static String selectAppointmentID =null;
    PatientDTO patient=new PatientDTO();

    private final AppointmentService appointmentService = (AppointmentService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.APPOINTMENT);
    private final DoctorService doctorService = (DoctorService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.DOCTOR);
    private final PatientService patientService = (PatientService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PATIENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        loadNextAppointmentId();
        setCellValueApp();
        loadAppointment(searchText);
        setListenerApp();
        SearchListener();
        setdatalabel();

    }
     void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText=newValue;
                loadAppointment(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    void setListenerApp() {
        tblAppointment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue) {
                setData(newValue);
               selectAppointmentID = newValue.getAppointmentID();
            }
        });
    }

    private void setData(AppointmentTm newValue) {
        lblAppointment.setText(newValue.getAppointmentID());
        lblPatientName.setText(newValue.getPatientName());
        lblPatientID.setText(newValue.getPatientID());
        lblAddress.setText(newValue.getPatientAddress());
        lblAge.setText(String.valueOf(newValue.getAge()));
        lblDoctorName.setText(newValue.getDoctorName());
        lblDoctorID.setText(newValue.getDoctorID());
        txtDate.setText(newValue.getDate());
    }

    void setCellValueApp() {
        colID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        colAgep.setCellValueFactory(new PropertyValueFactory<>("age"));
        colpAddress.setCellValueFactory(new PropertyValueFactory<>("patientAddress"));
        colDName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colDoctorId.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    public void loadAppointment(String text) throws SQLException, ClassNotFoundException {
        ObservableList<AppointmentTm> tmList= FXCollections.observableArrayList();

        for (AppointmentDTO a:
                appointmentService.getAllAppointment()) {
            if (a.getdName().contains(text)||a.getpName().contains(text)||a.getDoctorID().contains(text)){
                Button btn=new Button("Delete");
                btn.setCursor(Cursor.OPEN_HAND);
                btn.setStyle("-fx-background-color: #DB8D88");
                AppointmentTm tm=new AppointmentTm(a.getaId(),a.getpName(),a.getPatientID(),a.getAge(),a.getpAddress(),a.getdName(),a.getDoctorID(),a.getDate(),btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether do you want to delete this Appointment ? ",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try {

                            boolean isDelete = appointmentService.deleteAppointment(a.getaId());
                            if(isDelete) {
                                loadAppointment(searchText);
                                clear();
                                new Alert(Alert.AlertType.CONFIRMATION, "Appointment deleted!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }

        }
        tblAppointment.setItems(tmList);
    }

    public void editDoctorOnAction(ActionEvent actionEvent) {

    }
    private void clear(){
        txtDate.clear();
    }

    void setdatalabel() throws SQLException, ClassNotFoundException {
        DoctorDTO doctor= doctorService.searchDoctor(selectDoctorID);
        lblDoctorName.setText(doctor.getName());
        lblDoctorID.setText(doctor.getDoctorId());

        patient= patientService.searchPatient(selectPatientID);
        lblPatientName.setText(patient.getName());
        lblPatientID.setText(patient.getId());
        lblAddress.setText(patient.getAddress());
        lblAge.setText(String.valueOf(patient.getAge()));
        txtDate.requestFocus();
    }
    @FXML
    void addAppointmentOnAction(ActionEvent event) {

        try {
            boolean isAdded = appointmentService.addAppointment(new AppointmentDTO(lblAppointment.getText(),lblPatientName.getText(),lblPatientID.getText(),Integer.parseInt(lblAge.getText()),lblAddress.getText(),lblDoctorName.getText(),lblDoctorID.getText(),txtDate.getText()));
            if(isAdded) {
                loadAppointment(searchText);
                clear();
                loadNextAppointmentId();
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void loadNextAppointmentId() {
        try {
            String nextOrderID= appointmentService.getNextAppointmentID();
            if(null!=nextOrderID){
                lblAppointment.setText(nextOrderID);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Role.ADD_PATIENT,appointmentContext);
        selectPatientID =null;
    }
    @FXML
    void updateAppointmentOnAction(ActionEvent event) {

        try {
            boolean isAdded = appointmentService.updateAppointment(new AppointmentDTO(lblAppointment.getText(),lblPatientName.getText(),lblPatientID.getText(),Integer.parseInt(lblAge.getText()),lblAddress.getText(),lblDoctorName.getText(),lblDoctorID.getText(),txtDate.getText()));
            if(isAdded) {
                loadAppointment(searchText);
                clear();
                loadNextAppointmentId();
                new Alert(Alert.AlertType.CONFIRMATION, "Appointment Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void paymentOnAction(ActionEvent actionEvent) throws IOException {
        if(selectAppointmentID==null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "please select patient appointment");
            alert.show();
        }else {
            Navigation.navigate(Role.PAYMENT_RECEP, appointmentContext);
        }
    }
}
