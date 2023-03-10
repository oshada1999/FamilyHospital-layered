package lk.ijse.hospitel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.ijse.hospitel.service.ServiceFactory;
import lk.ijse.hospitel.service.custom.MedicineService;
import lk.ijse.hospitel.tm.MedicineTm;
import lk.ijse.hospitel.dto.MedicineDTO;

import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddMedicineFormController {

    @FXML
    private TableView<MedicineTm> tblMedicine;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colDose;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtDose;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtType;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXTextField txtUnitPrice;
    private String searchText="";
    private Pattern medicineCodePattern;
    private Pattern dosePattern;

    private final MedicineService medicineService = (MedicineService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.MEDICINE);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValue();
        loadMedicine(searchText);
        setListener();
        SearchListener();
        medicineCodePattern=Pattern.compile("^[m0-9_]{4,}$");
        dosePattern=Pattern.compile("^[m,g,l,0-9]{3,5}$");

    }

    private void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText=newValue;
                loadMedicine(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setListener() {
        tblMedicine.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue) {
                setData(newValue);
            }
        });
    }

    private void setData(MedicineTm newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtType.setText(newValue.getType());
        txtDose.setText(newValue.getDose());
        txtQty.setText(String.valueOf(newValue.getQty()));
        txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
    }

    private void setCellValue() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDose.setCellValueFactory(new PropertyValueFactory<>("dose"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    public void loadMedicine(String text) throws SQLException, ClassNotFoundException {
        ObservableList<MedicineTm> tmList= FXCollections.observableArrayList();

        for (MedicineDTO m:
                medicineService.getAllMedicine()) {
            if (m.getName().contains(text)){
                Button btn=new Button("Delete");
                btn.setCursor(Cursor.OPEN_HAND);
                btn.setStyle("-fx-background-color: #DB8D88");
                MedicineTm tm=new MedicineTm(m.getId(),m.getName(),m.getType(),m.getDose(),m.getQty(),m.getUnitPrice(),btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you sure whether do you want to delete this Medicine ? ",
                            ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES){
                        try {
                            boolean isDelete = medicineService.deleteMedicine(m.getId());
                            if(isDelete) {
                                loadMedicine(searchText);
                                clear();
                                new Alert(Alert.AlertType.CONFIRMATION, "Medicine deleted!").show();
                            }
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }

        }
        tblMedicine.setItems(tmList);
    }

    public void editDoctorOnAction(ActionEvent actionEvent) {

    }
    private void clear(){
        txtId.clear();
        txtName.clear();
        txtType.clear();
        txtDose.clear();
        txtQty.clear();
        txtUnitPrice.clear();
    }

    public void addMedicineOnAction(ActionEvent actionEvent) {
        boolean isCodeMatched=medicineCodePattern.matcher(txtId.getText()).matches();
        boolean isDoseMatched=dosePattern.matcher(txtDose.getText()).matches();
        if (isCodeMatched) {
            if (isDoseMatched) {
                try {
                    boolean isAdded = medicineService.addMedicine(new MedicineDTO( txtId.getText(), txtName.getText(), txtType.getText(), txtDose.getText(), Integer.parseInt(txtQty.getText()), Double.parseDouble(txtUnitPrice.getText())));
                    if (isAdded) {
                        loadMedicine(searchText);
                        clear();
                        new Alert(Alert.AlertType.CONFIRMATION, "Medicine Added!").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
                }
            } else {
                txtDose.setFocusColor(Paint.valueOf("Red"));
                txtDose.requestFocus();
            }
        }else {
            txtId.setFocusColor(Paint.valueOf("Red"));
            txtId.requestFocus();
        }
    }
    public void updateMedicineOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {
            boolean isAdded = medicineService.updateMedicine(new MedicineDTO(txtId.getText(),txtName.getText(),txtType.getText(),txtDose.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnitPrice.getText())));
            if(isAdded) {
                loadMedicine(searchText);
                clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Medicine Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
