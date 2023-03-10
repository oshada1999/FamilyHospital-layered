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
import lk.ijse.hospitel.service.custom.NurseService;
import lk.ijse.hospitel.tm.NurseTm;
import lk.ijse.hospitel.dto.NurseDTO;

import java.sql.SQLException;
import java.util.Optional;

public class AddNurseFormController {

    public TableView<NurseTm> tblNurse;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDepartment;
    public TableColumn colGender;
    public TableColumn colOption;
    public TextField txtSearch;
    public JFXRadioButton rbtnMale;
    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtDeparment;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private JFXRadioButton rbtnFemale;

    private String searchText = "";

    private final NurseService nurseService = (NurseService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.NURSE);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValue();
        loadNurse(searchText);
        setListener();
        SearchListener();

    }

    private void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText = newValue;
                loadNurse(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setListener() {
        tblNurse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                setData(newValue);
            }
        });
    }

    private void setData(NurseTm newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtContact.setText(newValue.getContact());
        txtDeparment.setText(newValue.getDepartment());
        rbtnFemale.setSelected(newValue.getGender().equals("female"));
        rbtnMale.setSelected(newValue.getGender().equals("male"));
    }

    private void setCellValue() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    public void loadNurse(String text) throws SQLException, ClassNotFoundException {
        ObservableList<NurseTm> tmList = FXCollections.observableArrayList();

        for (NurseDTO n :
                nurseService.getAllNurse()) {
            if (n.getName().contains(text) || n.getAddress().contains(text) || n.getDepartment().contains(text)) {
                Button btn = new Button("Delete");
                btn.setCursor(Cursor.OPEN_HAND);
                btn.setStyle("-fx-background-color: #DB8D88");
                NurseTm tm = new NurseTm(n.getId(), n.getName(), n.getAddress(), n.getContact(), n.getDepartment(), n.getGender(), btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether do you want to delete this Doctor ? ",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {
                        try {
                            boolean isDelete = nurseService.deleteNurse(n.getId());
                            if (isDelete) {
                                loadNurse(searchText);
                                clear();
                                new Alert(Alert.AlertType.CONFIRMATION, "Nurse deleted!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }

        }
        tblNurse.setItems(tmList);
    }

    private void clear() {
        txtId.clear();
        txtDeparment.clear();
        txtAddress.clear();
        txtContact.clear();
        txtName.clear();
    }

    public void addNurseOnAction(ActionEvent actionEvent) {
        try {
            boolean isAdded = nurseService.addNurse(new NurseDTO(txtId.getText(), txtName.getText(),  txtAddress.getText(), txtContact.getText(),  txtDeparment.getText(), rbtnFemale.isSelected() ? "female" : "male"));
            if (isAdded) {
                loadNurse(searchText);
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Nurse Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
        }
    }

    public void updateNurseOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {
            boolean isAdded = nurseService.updateNurse(new NurseDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(), txtDeparment.getText(), rbtnFemale.isSelected() ? "female" : "male"));
            if (isAdded) {
                loadNurse(searchText);
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Nurse Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}