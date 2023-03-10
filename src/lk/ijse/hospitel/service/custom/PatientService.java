package lk.ijse.hospitel.service.custom;

import lk.ijse.hospitel.service.SuperService;
import lk.ijse.hospitel.dto.PatientDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientService extends SuperService {
    boolean addPatient(PatientDTO patient) throws SQLException, ClassNotFoundException;

    ArrayList<PatientDTO> getAllPatient() throws SQLException, ClassNotFoundException;

    boolean deletePatient(String id) throws SQLException, ClassNotFoundException;

    boolean updatePatient(PatientDTO patient) throws SQLException, ClassNotFoundException;

    PatientDTO searchPatient(String id) throws SQLException, ClassNotFoundException;
}
