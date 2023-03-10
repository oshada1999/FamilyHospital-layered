package lk.ijse.hospitel.service.custom;

import lk.ijse.hospitel.service.SuperService;
import lk.ijse.hospitel.dto.ReceptionistDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReceptionistService extends SuperService {
    boolean addReceptionist(ReceptionistDTO receptionist) throws SQLException, ClassNotFoundException;

    ArrayList<ReceptionistDTO> getAllReceptionist() throws SQLException, ClassNotFoundException;

    boolean deleteReceptionist(String id) throws SQLException, ClassNotFoundException;

    boolean updateReceptionist(ReceptionistDTO receptionist) throws SQLException, ClassNotFoundException;
}
