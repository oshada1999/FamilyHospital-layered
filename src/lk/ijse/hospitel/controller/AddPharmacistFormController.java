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
import lk.ijse.hospitel.service.ServiceFactory;
import lk.ijse.hospitel.service.custom.PharmacistService;
import lk.ijse.hospitel.tm.PharmacistTm;
import lk.ijse.hospitel.dto.PharmacistDTO;

import java.sql.SQLException;
import java.util.Optional;

public class AddPharmacistFormController {

    @FXML
    private TableView<PharmacistTm> tblPharmacist;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtDob;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXRadioButton rbtnMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton rbtnFemale;

    @FXML
    private TextField txtSearch;
    private String searchText="";

    private final PharmacistService pharmacistService = (PharmacistService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PHARMACIST);


    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValue();
        loadPharmacist(searchText);
        setListener();
        SearchListener();

    }

    private void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText=newValue;
                loadPharmacist(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    private void setListener() {
        tblPharmacist.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue) {
                setData(newValue);
            }
        });
    }
    private void setData(PharmacistTm newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtContact.setText(newValue.getContact());
        txtDob.setText(newValue.getDob());
        rbtnFemale.setSelected(newValue.getGender().equals("female"));
        rbtnMale.setSelected(newValue.getGender().equals("male"));
    }
    private void setCellValue() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadPharmacist(String text) throws SQLException, ClassNotFoundException {
        ObservableList<PharmacistTm> tmList= FXCollections.observableArrayList();

        for (PharmacistDTO p:
                pharmacistService.getAllPharmacist()) {
            if (p.getName().contains(text)||p.getAddress().contains(text)){
                Button btn=new Button("Delete");
                btn.setCursor(Cursor.OPEN_HAND);
                btn.setStyle("-fx-background-color: #DB8D88");
                PharmacistTm tm=new PharmacistTm(p.getId(),p.getName(),p.getAddress(),p.getContact(),p.getDob(),p.getGender(),btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether do you want to delete this Pharmacist ? ",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try {
                            boolean isDelete = pharmacistService.deletePharmacist(p.getId());
                            if(isDelete) {
                                loadPharmacist(searchText);
                                clear();
                                new Alert(Alert.AlertType.CONFIRMATION, "Pharmacist deleted!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }

        }
        tblPharmacist.setItems(tmList);
    }




    private void clear(){
        txtId.clear();
        txtDob.clear();
        txtAddress.clear();
        txtContact.clear();
        txtName.clear();
    }

    @FXML
    void addPharmacistOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            boolean isAdded = pharmacistService.addPharmacist(new PharmacistDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),txtDob.getText(),rbtnFemale.isSelected()?"female":"male"));
            if(isAdded) {
                loadPharmacist(searchText);
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Pharmacist Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
        }
    }

    @FXML
    void updatePharmacistOnAction(ActionEvent event) {

        try {
            boolean isAdded = pharmacistService.updatePharmacist(new PharmacistDTO( txtId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),txtDob.getText(),rbtnFemale.isSelected()?"female":"male"));
            if(isAdded) {
                loadPharmacist(searchText);
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Pharmacist Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
