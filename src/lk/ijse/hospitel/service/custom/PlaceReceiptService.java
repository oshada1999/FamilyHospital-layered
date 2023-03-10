package lk.ijse.hospitel.service.custom;

import lk.ijse.hospitel.service.SuperService;
import lk.ijse.hospitel.dto.MedicineDTO;
import lk.ijse.hospitel.dto.PlaceReceiptDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceReceiptService extends SuperService {
    boolean PurchaseReceipt(PlaceReceiptDTO placeReceipt) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllMedicineCode() throws SQLException, ClassNotFoundException;

    String getNextReceiptID() throws SQLException, ClassNotFoundException;

    MedicineDTO searchMedicine(String code) throws SQLException, ClassNotFoundException;
}
