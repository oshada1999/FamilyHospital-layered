package lk.ijse.hospitel.dao.custom;

import lk.ijse.hospitel.dao.CrudDAO;
import lk.ijse.hospitel.dto.MedicineDTO;
import lk.ijse.hospitel.entity.Medicine;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedicineDAO extends CrudDAO<Medicine,String> {
    ArrayList<String> getAllMedicineCode() throws SQLException, ClassNotFoundException;
    boolean updateMedicineQty(Medicine medicine) throws SQLException, ClassNotFoundException;
}
