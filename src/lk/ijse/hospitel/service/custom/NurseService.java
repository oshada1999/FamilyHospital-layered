package lk.ijse.hospitel.service.custom;

import lk.ijse.hospitel.service.SuperService;
import lk.ijse.hospitel.dto.NurseDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NurseService extends SuperService {
    boolean addNurse(NurseDTO nurse) throws SQLException, ClassNotFoundException;

    ArrayList<NurseDTO> getAllNurse() throws SQLException, ClassNotFoundException;

    boolean deleteNurse(String id) throws SQLException, ClassNotFoundException;

    boolean updateNurse(NurseDTO nurse) throws SQLException, ClassNotFoundException;
}
