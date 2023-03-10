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
import lk.ijse.hospitel.service.ServiceFactory;
import lk.ijse.hospitel.service.custom.PlaceOrderService;
import lk.ijse.hospitel.db.DBConnection;
import lk.ijse.hospitel.tm.PlaceOrderTm;
import lk.ijse.hospitel.dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlaceMedicineOrdersController {

    public JFXComboBox cmbPatientId;
    public TextField txtQty;
    public TableView tblOrderCart;
    public TableColumn colQtyl;
    public Label lblType;
    public TableColumn colType1;
    public TableColumn colQty2;
    public TableColumn colDose1;
    public TableColumn colAction1;
    public TableColumn colTotal1;
    public TableColumn colAction2;
    public Label lblTotal;
    @FXML
    private Label lblReceiptId;

    @FXML
    private Label lblDate;

    @FXML
    private TableView<?> tblRecipt;

    @FXML
    private TableColumn<?, ?> colMediCode;

    @FXML
    private TableColumn<?, ?> colMediName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private Label lblPName;

    @FXML
    private JFXComboBox<String> cmbMediCode;

    @FXML
    private Label lblMediName;

    @FXML
    private TextField txtDose;

    @FXML
    private Label lblPId;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblDose;

    private final PlaceOrderService placeOrderService = (PlaceOrderService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PLACEORDER);

    private ObservableList<PlaceOrderTm> obList = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        loadOrderDate();
        loadPatientId();
        loadNextOrderId();
        loadMediCodes();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colMediCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colMediName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty2.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal1.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction2.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        colType1.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDose1.setCellValueFactory(new PropertyValueFactory<>("dose"));
    }

    private void loadMediCodes() {
        try {
            ObservableList<String> observableList= FXCollections.observableArrayList();

            ArrayList<String> mediList= placeOrderService.getAllMedicineCode();
            for (String code :
                    mediList) {
                observableList.add(code);
            }
            cmbMediCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void loadPatientId() {
        try {
            ObservableList<String> observableList= FXCollections.observableArrayList();
            ArrayList<String> mediList= placeOrderService.getAllPatientCode();

            for (String code :
                    mediList) {
                observableList.add(code);
            }
            cmbPatientId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadNextOrderId() throws SQLException, ClassNotFoundException {

        if (null!= placeOrderService.getNextOrderID()){
            lblReceiptId.setText(placeOrderService.getNextOrderID());
        }
    }

    private void loadOrderDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));

    }

    @FXML
    void backPatientOnAction(MouseEvent event) {

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String code = String.valueOf(cmbMediCode.getValue());
        int qty = Integer.parseInt(txtQty.getText());
        String desc = lblMediName.getText();
        String type=lblType.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        String dose=lblDose.getText();
        double total = unitPrice * qty;
        double netTotal=+total;
        lblTotal.setText(String.valueOf(netTotal));

        Button btn = new Button("Delete");
        btn.setCursor(Cursor.OPEN_HAND);
        btn.setStyle("-fx-background-color: #DB8D88");
        txtQty.clear();

        if (!obList.isEmpty()) {
            L1:

            for (int i = 0; i <tblOrderCart.getItems().size(); i++) {
                if (colMediCode.getCellData(i).equals(code)) {
                    qty += (int) colQty2.getCellData(i);
                    total = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);
                    tblOrderCart.refresh();
                    setNetTotal();
                    return;
                }
                //lblTotal.setText(String.valueOf(total));
            }
            setNetTotal();

        }


        btn.setOnAction((e) -> {
            new Alert(Alert.AlertType.WARNING,"Do You Want delete this Medicine");

            tblOrderCart.getItems().removeAll(tblOrderCart.getSelectionModel().getSelectedItem());

        });
        obList.add(new PlaceOrderTm(code,desc,qty,type,dose,unitPrice,total,btn));
        tblOrderCart.setItems(obList);
    }


    private void setNetTotal(){
        double total=0;
        for (int i=0;i<tblOrderCart.getItems().size();i++){
            total+=(double)colTotal1.getCellData(i);
            System.out.println(total);
        }
        lblTotal.setText(String.valueOf(total));
    }
    @FXML
    void cmbmediOnAction(ActionEvent event) {
        String code=String.valueOf(cmbMediCode.getValue());

        MedicineDTO medicine= null;
        try {
            medicine = placeOrderService.searchMedicine(code);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        fillMediFields(medicine);
    }
    private void fillMediFields(MedicineDTO medicine) {
        lblMediName.setText(medicine.getName());
        lblUnitPrice.setText(String.valueOf(medicine.getUnitPrice()));
        lblQty.setText(String.valueOf(medicine.getQty()));
        lblDose.setText(medicine.getDose());
        lblType.setText(medicine.getType());

    }

    @FXML
    void placeOrderOnAction(ActionEvent event) {
        String orderId = lblReceiptId.getText();
        String patientId = String.valueOf(cmbPatientId.getValue());

        ArrayList<OrderDetailsDTO> orderDetails = new ArrayList<>();

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            PlaceOrderTm tm = obList.get(i);
            orderDetails.add(new OrderDetailsDTO(orderId,tm.getCode(),tm.getName(),tm.getType(),tm.getDose(),tm.getQty(),tm.getUnitPrice()));
        }

        PlaceOrderDTO placeOrder = new PlaceOrderDTO(patientId, orderId,orderDetails);
        try {
            boolean isPlaced = placeOrder(placeOrder);
            if (isPlaced) {
                tblOrderCart.refresh();
                obList.clear();
                loadNextOrderId();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private  boolean placeOrder(PlaceOrderDTO placeOrder) {
        try {
            return placeOrderService.placeOrder(placeOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void cmbPatientOnACtion(ActionEvent actionEvent) {

        PatientDTO patient= null;
        try {
            patient = placeOrderService.searchPatient(String.valueOf(cmbPatientId.getValue()));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        fillPatientFields(patient);
    }

    private void fillPatientFields(PatientDTO patient) {
        lblPId.setText(patient.getName());
    }

    public void printOrderOnAction(ActionEvent actionEvent) {
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/hospitel/report/medicineOrder.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport(compileReport, null, DBConnection.getInstance().getConnection());
//            JasperPrintManager.printReport(jasperPrint, true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
