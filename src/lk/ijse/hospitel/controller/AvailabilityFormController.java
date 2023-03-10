package lk.ijse.hospitel.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hospitel.util.Navigation;
import lk.ijse.hospitel.util.Role;

import java.io.IOException;
import java.sql.SQLException;

public class AvailabilityFormController extends AddDoctorWindowFormController {
    public AnchorPane drAvailabilityContext;
    public TableColumn coldId;
    public TableColumn colName;
    public TableColumn colDepartment;
    public TableColumn colDay;
    public TableColumn colStartTime;
    public TableColumn colEndTime;
    public TextField txtSearch;
    private String searchText = "";
    static String selectDoctorID = null;



    public void initialize() throws SQLException, ClassNotFoundException {
        coldId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        loadDoctor(searchText);
        setListener();
        SearchListener();
    }

    public void setListener() {
        tblDoctor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                selectDoctorID = newValue.getDoctorId();
            }
        });
    }

    public void addPatientOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        if (selectDoctorID==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select the desired doctor for the patient from the table");
            alert.show();
        }else {
            Navigation.navigate(Role.ADD_PATIENT, drAvailabilityContext);

        }
    }
}
