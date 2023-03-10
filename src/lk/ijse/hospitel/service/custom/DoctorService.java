package lk.ijse.hospitel.service.custom;

import lk.ijse.hospitel.service.SuperService;
import lk.ijse.hospitel.dto.DoctorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorService extends SuperService {
    boolean addDoctor(DoctorDTO doctor) throws SQLException, ClassNotFoundException;

    ArrayList<DoctorDTO> getAllDoctor() throws SQLException, ClassNotFoundException;

    boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException;

    boolean updateDoctor(DoctorDTO doctor) throws SQLException, ClassNotFoundException;

    DoctorDTO searchDoctor(String id) throws SQLException, ClassNotFoundException;
}
