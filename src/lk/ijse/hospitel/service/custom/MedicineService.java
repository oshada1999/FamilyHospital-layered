package lk.ijse.hospitel.service.custom;

import lk.ijse.hospitel.service.SuperService;
import lk.ijse.hospitel.dto.MedicineDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineService extends SuperService {
    boolean addMedicine(MedicineDTO medicine) throws SQLException, ClassNotFoundException;

    ArrayList<MedicineDTO> getAllMedicine() throws SQLException, ClassNotFoundException;

    boolean deleteMedicine(String id) throws SQLException, ClassNotFoundException;

    boolean updateMedicine(MedicineDTO medicine) throws SQLException, ClassNotFoundException;

}
