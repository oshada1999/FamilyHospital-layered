package lk.ijse.hospitel.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import lk.ijse.hospitel.service.ServiceFactory;
import lk.ijse.hospitel.service.custom.DoctorService;
import lk.ijse.hospitel.tm.DoctorTm;
import lk.ijse.hospitel.dto.DoctorDTO;

import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddDoctorWindowFormController {
    public AnchorPane doctorManageContext;
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtDepartment;
    public JFXTextField txtAddress;
    public JFXRadioButton rbtnFemale;
    public TableColumn clmName;
    public TableView<DoctorTm> tblDoctor;
    public TableColumn clmId;
    public TableColumn clmAddress;
    public TableColumn clmContact;
    public TableColumn clmDepartment;
    public TableColumn clmGender;
    public TableColumn clmOption;
    public ToggleGroup gender;
    public JFXRadioButton rbtnMale;
    public JFXButton btnAddDoctor;
    public TextField txtSearch;
    public TableColumn clmDay;
    public TableColumn clmStartTime;
    public TableColumn clmEndTime;
    public JFXTextField txtDay;
    public JFXTimePicker txtStartTime;
    public JFXTimePicker txtEndTime;
    public JFXComboBox cmbDay;
    public JFXTextField txtSTime;
    public JFXTextField txtETime;
    private String searchText="";
    private Pattern idPattern;
    private Pattern namePattern;
    private Pattern phonePattern;

    private final DoctorService doctorService = (DoctorService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.DOCTOR);

    public void initialize() throws SQLException, ClassNotFoundException {
        cmbDay.getItems().addAll("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
        setCellValue();
        loadDoctor(searchText);
        setListener();
        SearchListener();
        idPattern=Pattern.compile("[(D)(0-9)]{4,}$");
        phonePattern=Pattern.compile("[(0-9)]{10,}$");
    }

     void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText=newValue;
                loadDoctor(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setListener() {
        tblDoctor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue) {
                setData(newValue);
            }
        });
    }

    private void setData(DoctorTm newValue) {
        txtID.setText(newValue.getDoctorId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtContact.setText(newValue.getContact());
        cmbDay.setAccessibleText(newValue.getDay());
        txtSTime.setText(newValue.getStartTime());
        txtETime.setText(newValue.getEndTime());
        txtDepartment.setText(newValue.getDepartment());
        rbtnFemale.setSelected(newValue.getGender().equals("female"));
        rbtnMale.setSelected(newValue.getGender().equals("male"));
    }

    private void setCellValue() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        clmStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        clmEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        clmDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


    }

    public void loadDoctor(String text) throws SQLException, ClassNotFoundException {
        ObservableList<DoctorTm> tmList= FXCollections.observableArrayList();

        for (DoctorDTO d: doctorService.getAllDoctor()) {
            if (d.getName().contains(text)||d.getAddress().contains(text)||d.getDepartment().contains(text)){
                Button btn=new Button("Delete");
                btn.setCursor(Cursor.OPEN_HAND);
                btn.setStyle("-fx-background-color: #DB8D88");
                DoctorTm tm=new DoctorTm(d.getDoctorId(),d.getName(),d.getAddress(),d.getContact(),d.getDay(),d.getStartTime(),d.getEndTime(),d.getDepartment(),d.getGender(),btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether do you want to delete this Doctor ? ",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try {
                            boolean isDelete = doctorService.deleteDoctor(d.getDoctorId());
                            if(isDelete) {
                                loadDoctor(searchText);
                                clear();
                                new Alert(Alert.AlertType.CONFIRMATION, "Doctor deleted!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }

        }
        tblDoctor.setItems(tmList);
    }

    public void editDoctorOnAction(ActionEvent actionEvent) {

    }
    private void clear(){
        txtID.clear();
        txtDepartment.clear();
        txtAddress.clear();
        txtContact.clear();
        txtName.clear();
        txtSTime.clear();
        txtETime.clear();
    }

    public void addNewDoctorOnAction(ActionEvent actionEvent) {
        boolean isId=idPattern.matcher(txtID.getText()).matches();
        boolean isPhone=phonePattern.matcher(txtContact.getText()).matches();
        if (isPhone) {
            if (isId) {

                try {
                    boolean isAdded = doctorService.addDoctor(new DoctorDTO(txtID.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(), cmbDay.getSelectionModel().getSelectedItem().toString(), txtSTime.getText(), txtETime.getText(), txtDepartment.getText(), rbtnFemale.isSelected() ? "female" : "male"));
                    if (isAdded) {
                        loadDoctor(searchText);
                        clear();
                        new Alert(Alert.AlertType.CONFIRMATION, "Doctor Added!").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Doctor exist!").show();
                }

            } else {
                txtID.setFocusColor(Paint.valueOf("Red"));
                txtID.requestFocus();
            }
        }else {
            txtContact.setFocusColor(Paint.valueOf("Red"));
            txtContact.requestFocus();
        }
    }
        public void updateDoctorOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

            try {
                boolean isAdded = doctorService.updateDoctor(new DoctorDTO(txtID.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(), cmbDay.getSelectionModel().getSelectedItem().toString(), txtSTime.getText(), txtETime.getText(), txtDepartment.getText(), rbtnFemale.isSelected() ? "female" : "male"));
                if(isAdded) {
                    loadDoctor(searchText);
                    clear();
                    new Alert(Alert.AlertType.CONFIRMATION, "Doctor Updated!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
            }
    }

}
