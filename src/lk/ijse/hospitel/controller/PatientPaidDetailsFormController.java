package lk.ijse.hospitel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hospitel.service.ServiceFactory;
import lk.ijse.hospitel.service.custom.AppointmentService;
import lk.ijse.hospitel.service.custom.PlaceOrderService;
import lk.ijse.hospitel.tm.PatientPaidDetailsTm;
import lk.ijse.hospitel.dto.AppointmentDTO;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;

import java.io.IOException;
import java.sql.SQLException;

public class PatientPaidDetailsFormController extends AddPatientWindowFormController{

    public AnchorPane patientDetails;
    public TableColumn colPatientId;
    public TableColumn colPatientName;
    public TableColumn colAddress;
    public TableColumn colPhone;
    public TableColumn colAge;
    public TableColumn colDisease;
    public TableColumn colGender;
    public TableColumn colStatus;
    public TableView tblPaidPatient;
    public TextField txtSearch;
    public TableColumn colDoctorID;
    public TableColumn colPatientID;
    public TableColumn colPatientAddress;
    public TableColumn colDoctorName;
    public TableColumn colDoctorId;
    public TableColumn colDate;
    private String searchText="";
    public String status="Pending";
    static String doctorName="";
    static String patientName="";
    static String doctorID="";
    static String patientID=null;

    private final PlaceOrderService placeOrderService = (PlaceOrderService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PLACEORDER);
    private final AppointmentService appointmentService = (AppointmentService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.APPOINTMENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        getPaidPatient(searchText);
        //getPaidDoctor(searchText);
        setCellValue();
        searchListener();
        selectListener();
    }

    void selectListener() {
        tblPaidPatient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue){
                setpaidData((PatientPaidDetailsTm) newValue);
            }
        });
    }

    private void setpaidData(PatientPaidDetailsTm newValue) {
        doctorName=newValue.getDoctorName();
        patientName=newValue.getPatientName();
        doctorID=newValue.getDoctorId();
        patientID=newValue.getPatientId();

    }

    private void searchListener() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            try {
                getPaidPatient(searchText);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void setCellValue(){

        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        colPatientAddress.setCellValueFactory(new PropertyValueFactory<>("patientAddress"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("patientAge"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colDoctorId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        //colDoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
    }
    public void getPaidPatient(String searchText) throws SQLException, ClassNotFoundException {
        ObservableList<PatientPaidDetailsTm> paidList= FXCollections.observableArrayList();

        for (String paidPatientId:
                placeOrderService.getAllPaidAppointment() ) {

            AppointmentDTO appointment = appointmentService.searchAppointment(paidPatientId);
            if (appointment.getpName().contains(searchText)||appointment.getdName().contains(searchText)) {
                PatientPaidDetailsTm tm=new PatientPaidDetailsTm(appointment.getpName(),appointment.getPatientID(),appointment.getAge(),appointment.getpAddress(),appointment.getdName(),appointment.getDoctorID(),appointment.getDate());
                paidList.add(tm);
            }
        }
        tblPaidPatient.setItems(paidList);
    }

    public void ReceiptOnAction(ActionEvent actionEvent) throws IOException {
        if (patientID==null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "please select patient");
            alert.show();
        }else {
            Navigation.navigate(Role.RECEIPT, patientDetails);
        }
    }
}
