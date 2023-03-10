package lk.ijse.hospitel.dao.custom;

import lk.ijse.hospitel.dao.SupperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SupperDAO {
     ArrayList<String> getAllPaidAppointment() throws SQLException, ClassNotFoundException;
}
