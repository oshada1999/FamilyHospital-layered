package lk.ijse.hospitel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.hospitel.service.ServiceFactory;
import lk.ijse.hospitel.service.custom.AppointmentService;
import lk.ijse.hospitel.service.custom.PaymentService;
import lk.ijse.hospitel.db.DBConnection;
import lk.ijse.hospitel.tm.PaymentTm;
import lk.ijse.hospitel.dto.AppointmentDTO;
import lk.ijse.hospitel.dto.PaymentDTO;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;

import static lk.ijse.hospitel.controller.AddAppointmentFormController.selectAppointmentID;
import static lk.ijse.hospitel.controller.ChoseAppointmentFormController.isChoose;


public class AddPaymentFormController {

    public Label lblTotal;
    public Label lblStatus;
    public JFXButton btnPay;
    @FXML
    private AnchorPane paymentContext;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<PaymentTm> tblPaymemt;

    @FXML
    private TableColumn<?, ?> colpayId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colAppId;

    @FXML
    private TableColumn<?, ?> colChannellingCost;

    @FXML
    private TableColumn<?, ?> colOtherCost;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblPayId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblAppId;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblDoctorName;

    @FXML
    private JFXTextField txtChannellingCoast;

    @FXML
    private JFXTextField txtOtherCost;
    private String searchText="";

    private final AppointmentService appointmentService = (AppointmentService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.APPOINTMENT);
    private final PaymentService paymentService = (PaymentService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PAYMENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        loadPayDate();
        loadNextPaymentId();
        loadPayment(searchText);
        setListener();
        SearchListener();
        setdatalabel();
        setCellValue();
        paidAppointment();
    }
    void SearchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchText=newValue;
                loadPayment(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setListener() {
        tblPaymemt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue) {
                try {
                    setData(newValue);
                    selectAppointmentID = newValue.getAppointmentID();
                    paidAppointment();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setData(PaymentTm newValue) throws SQLException, ClassNotFoundException {
        lblPayId.setText(newValue.getPaymentID());
        lblDate.setText(newValue.getDate());
        lblAppId.setText(newValue.getAppointmentID());

        AppointmentDTO appointment= appointmentService.searchAppointment(lblAppId.getText());
        lblPatientName.setText(appointment.getpName());
        lblDoctorName.setText(appointment.getdName());
        txtChannellingCoast.setText(String.valueOf(newValue.getChannelling_cost()));
        txtOtherCost.setText(String.valueOf(newValue.getOther_cost()));
        lblTotal.setText(String.valueOf(newValue.getTotal()));
    }

    private void setCellValue() {
        colpayId.setCellValueFactory(new PropertyValueFactory<>("paymentID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAppId.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colChannellingCost.setCellValueFactory(new PropertyValueFactory<>("channelling_cost"));
        colOtherCost.setCellValueFactory(new PropertyValueFactory<>("other_cost"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    public void loadPayment(String text) throws SQLException, ClassNotFoundException {
        ObservableList<PaymentTm> tmList= FXCollections.observableArrayList();

        for (PaymentDTO p:
                paymentService.getAllPayment()) {
            if (p.getAppointmentID().contains(text)||p.getPaymentID().contains(text)){

                PaymentTm tm=new PaymentTm(p.getPaymentID(),p.getDate(),p.getChannelling_cost(),p.getOther_cost(),p.getTotal(),p.getAppointmentID());
                tmList.add(tm);

            }

        }
        tblPaymemt.setItems(tmList);
    }
    public void paidAppointment() throws SQLException, ClassNotFoundException {

        String paidAnswer= paymentService.searchPaymentId(selectAppointmentID);
        if (paidAnswer!=null){
            lblStatus.setText("Paid");
            lblStatus.setTextFill(Paint.valueOf("GREEN"));
           // btnPay.setVisible(false);
        }else {
            lblStatus.setText("Pending");
            lblStatus.setTextFill(Paint.valueOf("RED"));
        }
    }
    private void clear(){
        txtChannellingCoast.clear();
        txtOtherCost.clear();
    }

    private void setdatalabel() throws SQLException, ClassNotFoundException {

        AppointmentDTO appointment= appointmentService.searchAppointment(selectAppointmentID);
        lblAppId.setText(appointment.getaId());
        lblPatientName.setText(appointment.getpName());
        lblDoctorName.setText(appointment.getdName());
    }
    void loadNextPaymentId() {
        try {
            String nextPayId= paymentService.getNextPaymentID();
            if(null!=nextPayId){
                lblPayId.setText(nextPayId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadPayDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        if (isChoose){
            Navigation.navigate(Role.CHOOSE_APPOINTMENT,paymentContext);
            selectAppointmentID=null;
        }else {
            Navigation.navigate(Role.APPOINTMENT, paymentContext);
            selectAppointmentID = null;
        }
    }@FXML
    void payOnAction(ActionEvent event) {

        System.out.println(selectAppointmentID);
        String payId=lblPayId.getText();
        String apoID=lblAppId.getText();
        String date=lblDate.getText();
        double channelling_cost=Double.parseDouble(txtChannellingCoast.getText());
        double other_cost;
        if (txtOtherCost.getText().equals("")){
            other_cost=0.0;
        }else {
            other_cost=Double.parseDouble(txtOtherCost.getText());
        }
        double total=channelling_cost+other_cost;

        if (lblStatus.getText().equals("Paid")){
            Alert alert=new Alert(Alert.AlertType.ERROR,"already paid this Appointment");
            alert.show();
        }else {
            try {
                boolean isAdded = paymentService.addPayment(new PaymentDTO(payId,date,channelling_cost,other_cost,total,apoID));
                if (isAdded) {
                    loadPayment(searchText);
                    clear();
                    loadNextPaymentId();
                    paidAppointment();
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment Added!").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
            }
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        String payId=lblPayId.getText();
        String apoID=lblAppId.getText();
        String date=lblDate.getText();
        double channelling_cost=Double.parseDouble(txtChannellingCoast.getText());
        double other_cost;
        if (txtOtherCost.getText().equals("")){
            other_cost=0.0;
        }else {
            other_cost=Double.parseDouble(txtOtherCost.getText());
        }
        double total=channelling_cost+other_cost;
        try {
            boolean isAdded = paymentService.updatePayment(new PaymentDTO(payId,date,channelling_cost,other_cost,total,apoID));
            if(isAdded) {
                loadPayment(searchText);
                clear();
                loadNextPaymentId();
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printReportOnAction(ActionEvent actionEvent) {
        InputStream inputStream = this.getClass().getResourceAsStream("/lk/ijse/hospitel/report/reportPay.jrxml");
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
}
