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
import lk.ijse.hospitel.service.custom.ReceptionistService;
import lk.ijse.hospitel.tm.ReceptionistTm;
import lk.ijse.hospitel.dto.ReceptionistDTO;

import java.sql.SQLException;
import java.util.Optional;

public class AddreceptionistFormController {

    @FXML
    private TableView<ReceptionistTm> tblReception;

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
    private ToggleGroup Gender;

    @FXML
    private JFXRadioButton rbtnFemale;

    @FXML
    private TextField txtSearch;
    private String searchText="";

    private final ReceptionistService receptionistService = (ReceptionistService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.RECEPTIONIST);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValue();
        loadReceptionist(searchText);
        setListener();
        SearchListener();

    }

    private void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText=newValue;
                loadReceptionist(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    private void setListener() {
        tblReception.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue) {
                setData(newValue);
            }
        });
    }
    private void setData(ReceptionistTm newValue) {
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

    private void loadReceptionist(String text) throws SQLException, ClassNotFoundException {
        ObservableList<ReceptionistTm> tmList= FXCollections.observableArrayList();

        for (ReceptionistDTO r:
                receptionistService.getAllReceptionist()) {
            if (r.getName().contains(text)||r.getAddress().contains(text)){
                Button btn=new Button("Delete");
                btn.setCursor(Cursor.OPEN_HAND);
                btn.setStyle("-fx-background-color: #DB8D88");
                ReceptionistTm tm=new ReceptionistTm(r.getId(),r.getName(),r.getAddress(),r.getContact(),r.getDob(),r.getGender(),btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether do you want to delete this Receptionist ? ",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try {
                            boolean isDelete = receptionistService.deleteReceptionist(r.getId());
                            if(isDelete) {
                                loadReceptionist(searchText);
                                clear();
                                new Alert(Alert.AlertType.CONFIRMATION, "Receptionist deleted!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }

        }
        tblReception.setItems(tmList);
    }
    private void clear(){
        txtId.clear();
        txtDob.clear();
        txtAddress.clear();
        txtContact.clear();
        txtName.clear();
    }

    @FXML
    void addReceptionOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            boolean isAdded = receptionistService.addReceptionist(new ReceptionistDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),txtDob.getText(),rbtnFemale.isSelected()?"female":"male"));
            if(isAdded) {
                loadReceptionist(searchText);
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Receptionist Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
        }
    }

    @FXML
    void updateReceptionOnAction(ActionEvent event) {

        try {
            boolean isAdded = receptionistService.updateReceptionist(new ReceptionistDTO(txtId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),txtDob.getText(),rbtnFemale.isSelected()?"female":"male"));
            if(isAdded) {
                loadReceptionist(searchText);
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Receptionist Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
