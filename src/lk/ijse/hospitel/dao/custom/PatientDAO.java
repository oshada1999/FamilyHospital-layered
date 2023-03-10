package lk.ijse.hospitel.dao.custom;

import lk.ijse.hospitel.dao.CrudDAO;
import lk.ijse.hospitel.dto.PatientDTO;
import lk.ijse.hospitel.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientDAO extends CrudDAO<Patient,String> {
    ArrayList<String> getAllPatientCode() throws SQLException, ClassNotFoundException;
}
