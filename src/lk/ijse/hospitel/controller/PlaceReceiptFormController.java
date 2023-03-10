package lk.ijse.hospitel.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.hospitel.service.custom.PlaceReceiptService;
;
import lk.ijse.hospitel.db.DBConnection;
import lk.ijse.hospitel.tm.AddReceiptTm;
import lk.ijse.hospitel.dto.MedicineDTO;
import lk.ijse.hospitel.dto.PlaceReceiptDTO;
import lk.ijse.hospitel.dto.ReceiptDetailsDTO;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class PlaceReceiptFormController extends PatientPaidDetailsFormController {

    public Separator receiptContext;
    public AnchorPane receContext;
    public TableColumn<?,?> ColDose;
    @FXML
    private Label lblReceiptId;

    @FXML
    private Label lblDate;

    @FXML
    private TableView<AddReceiptTm> tblRecipt;

    @FXML
    private TableColumn<?, ?> colMediCode;

    @FXML
    private TableColumn<?, ?> colMediName;

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private TableColumn<?, ?> ColOption;

    @FXML
    private Label lblPName;

    @FXML
    private Label lblDName;

    @FXML
    private JFXComboBox<String> cmbMediCode;

    @FXML
    private Label lblMediName;

    @FXML
    private TextField txtDose;

    @FXML
    private Label lblPId;
    private Pattern dosePattern;
    @FXML
    private Label lblDId;
    private ObservableList<AddReceiptTm> obList = FXCollections.observableArrayList();

    private final PlaceReceiptService placeReceiptService = (PlaceReceiptService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PLACERECEIPT);

    /*private final MedicineBo medicineBo = (MedicineBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEDICINE);
    private final ReceiptBo receiptBo = (ReceiptBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RECEIPT);*/

    public void initialize() throws SQLException, ClassNotFoundException {
        loadReceiptDate();
        setLblData();
        loadNextReciptId();
        loadMedicineCode();
        setCellcolValue();
        dosePattern=Pattern.compile("^[m,g,l,0-9]{3,5}$");
    }

    private void setCellcolValue() {
        colMediCode.setCellValueFactory(new PropertyValueFactory<>("medicineCode"));
        colMediName.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        ColDose.setCellValueFactory(new PropertyValueFactory<>("dose"));
        ColOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadMedicineCode() {

        try {
            ObservableList<String> observableList= FXCollections.observableArrayList();

            ArrayList<String> mediList= placeReceiptService.getAllMedicineCode();

            for (String code :
                    mediList) {
                observableList.add(code);
            }
            cmbMediCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadNextReciptId() throws SQLException, ClassNotFoundException {

        String nextRecwiptId= placeReceiptService.getNextReceiptID();
        if (null!=nextRecwiptId){
            lblReceiptId.setText(nextRecwiptId);
        }
    }

    private void loadReceiptDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setLblData(){
        lblDName.setText(doctorName);
        lblPName.setText(patientName);
        lblDId.setText(doctorID);
        lblPId.setText(patientID);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
            String code = String.valueOf(cmbMediCode.getValue());
            String name = lblMediName.getText();
            String dose = txtDose.getText();
            String receiptId = lblReceiptId.getText();
            Button btn = new Button("Delete");
            btn.setCursor(Cursor.OPEN_HAND);
            btn.setStyle("-fx-background-color: #DB8D88");
            txtDose.clear();

            btn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether do you want to delete this Medicine ? ",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    AddReceiptTm tm = tblRecipt.getSelectionModel().getSelectedItem();
                    System.out.println("delete");
                    tblRecipt.getItems().removeAll(tblRecipt.getSelectionModel().getSelectedItem());
                }
            });
            obList.add(new AddReceiptTm(code, name, dose, btn));
            tblRecipt.setItems(obList);

    }

    @FXML
    void printOnAction(ActionEvent event) {
       /* InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/hospitel/report/doctorReceiptA4.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport(compileReport, null, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint, true);
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    @FXML
    void sendPharmOnAction(ActionEvent event) {
        String receiptId=lblReceiptId.getText();
        String patientId=lblPId.getText();

        ArrayList<ReceiptDetailsDTO> mediDetails = new ArrayList<>();
        for (int i=0;i<tblRecipt.getItems().size();i++){
            AddReceiptTm tm=obList.get(i);
            mediDetails.add(new ReceiptDetailsDTO(tm.getMedicineCode(),tm.getMedicineName(),tm.getDose(),receiptId));
        }

        PlaceReceiptDTO placeReceipt=new PlaceReceiptDTO(patientId,receiptId,mediDetails);

        boolean isAdded= placeReceipt(placeReceipt);
            /*if (isAdded){
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Send Pharmacist!");
                alert.show();

            }*/
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/hospitel/report/doctorReceiptA4.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport(compileReport, null, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint, true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException  e) {
            throw new RuntimeException(e);
        }

    }
    public boolean placeReceipt(PlaceReceiptDTO placeReceipt){

        try {
            return placeReceiptService.PurchaseReceipt(placeReceipt);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void cmbmediOnAction(ActionEvent actionEvent){
        String code=String.valueOf(cmbMediCode.getValue());

        MedicineDTO medicine= null;
        try {
            medicine = placeReceiptService.searchMedicine(code);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        fillMediFields(medicine);
    }

    private void fillMediFields(MedicineDTO medicine) {
        lblMediName.setText(medicine.getName());

    }

    public void backPatientOnAction(MouseEvent mouseEvent) throws IOException {
        patientID=null;
        Navigation.navigate(Role.PATIENT_DETAILS,receContext);
    }
}
