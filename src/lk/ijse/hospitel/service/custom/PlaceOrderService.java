package lk.ijse.hospitel.service.custom;

import lk.ijse.hospitel.service.SuperService;
import lk.ijse.hospitel.dto.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderService extends SuperService {
    boolean placeOrder(PlaceOrderDTO placeOrder) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllMedicineCode() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllPatientCode() throws SQLException, ClassNotFoundException;

    String getNextOrderID() throws SQLException, ClassNotFoundException;

    MedicineDTO searchMedicine(String id) throws SQLException, ClassNotFoundException;

    PatientDTO searchPatient(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllPaidAppointment() throws SQLException, ClassNotFoundException;
}
