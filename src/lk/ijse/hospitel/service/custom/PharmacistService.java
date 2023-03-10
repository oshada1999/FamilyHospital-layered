package lk.ijse.hospitel.service.custom;

import lk.ijse.hospitel.service.SuperService;
import lk.ijse.hospitel.dto.PharmacistDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PharmacistService extends SuperService {
    boolean addPharmacist(PharmacistDTO pharmacist) throws SQLException, ClassNotFoundException;

    ArrayList<PharmacistDTO> getAllPharmacist() throws SQLException, ClassNotFoundException;

    boolean deletePharmacist(String id) throws SQLException, ClassNotFoundException;

    boolean updatePharmacist(PharmacistDTO pharmacist) throws SQLException, ClassNotFoundException;
}
