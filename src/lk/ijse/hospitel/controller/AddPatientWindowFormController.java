package lk.ijse.hospitel.controller;

import com.jfoenix.controls.JFXRadioButton;
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
import lk.ijse.hospitel.service.custom.PatientService;
import lk.ijse.hospitel.tm.PatientTm;
import lk.ijse.hospitel.dto.PatientDTO;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static lk.ijse.hospitel.controller.AvailabilityFormController.selectDoctorID;

public class AddPatientWindowFormController extends AddDoctorWindowFormController {

    public JFXRadioButton rbtnMale;
    public ToggleGroup Gender;
    public JFXRadioButton rbtnFemale;
    public TableColumn colGender;
    @FXML
    private AnchorPane addPatienrContext;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtDisease;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<PatientTm> tblPatient;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colDisease;

    @FXML
    private TableColumn<?, ?> colOption;

    private String searchText="";
    static String selectPatientID=null;

    private final PatientService patientService = (PatientService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PATIENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValue();
        loadPatient(searchText);
        setListener();
        SearchListener();


    }

    void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText=newValue;
                loadPatient(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setListener() {
        tblPatient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue) {
                setData(newValue);
                selectPatientID=newValue.getId();
            }
        });
    }

    private void setData(PatientTm newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtPhone.setText(newValue.getPhone());
        txtAge.setText(String.valueOf(newValue.getAge()));
        txtDisease.setText(newValue.getDisease());
        rbtnFemale.setSelected(newValue.getGender().equals("female"));
        rbtnMale.setSelected(newValue.getGender().equals("male"));
    }

    private void setCellValue() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colDisease.setCellValueFactory(new PropertyValueFactory<>("disease"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


    }

    public void loadPatient(String text) throws SQLException, ClassNotFoundException {
        ObservableList<PatientTm> tmList= FXCollections.observableArrayList();

        for (PatientDTO p:
                patientService.getAllPatient()) {
            if (p.getName().contains(text)||p.getAddress().contains(text)){
                Button btn=new Button("Delete");
                btn.setCursor(Cursor.OPEN_HAND);
                btn.setStyle("-fx-background-color: #DB8D88");
                PatientTm tm=new PatientTm(p.getId(),p.getName(),p.getAddress(),p.getPhone(),p.getAge(),p.getDisease(),p.getGender(),btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether do you want to delete this Patient ? ",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try {
                            boolean isDelete = patientService.deletePatient(p.getId());
                            if(isDelete) {
                                loadPatient(searchText);
                                clear();
                                new Alert(Alert.AlertType.CONFIRMATION, "Patient deleted!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
                        }
                    }

                });
            }

        }
        tblPatient.setItems(tmList);
    }

    private void clear(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtPhone.clear();
        txtAge.clear();
        txtDisease.clear();

    }

    public void addPatientOnAction(ActionEvent actionEvent) throws IOException {
        try {
            boolean isAdded = patientService.addPatient(new PatientDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtPhone.getText(),Integer.parseInt(txtAge.getText()),txtDisease.getText(),rbtnFemale.isSelected()?"female":"male"));
            if(isAdded) {
                loadPatient(searchText);
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Patient Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
             try {
                 boolean isAdded = patientService.updatePatient(new PatientDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtPhone.getText(),Integer.parseInt(txtAge.getText()),txtDisease.getText(),rbtnFemale.isSelected()?"female":"male"));
            if(isAdded) {
                loadPatient(searchText);
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Patient Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Role.DOCTOR_AVAILABILITY,addPatienrContext);
        selectDoctorID=null;
    }

    public void createAppointmentOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if (selectPatientID==null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select the patient and make an appointment");
            alert.show();
        }else {
            Navigation.navigate(Role.APPOINTMENT, addPatienrContext);
        }
    }
}
