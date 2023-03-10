package lk.ijse.hospitel.dao.custom;

import lk.ijse.hospitel.dao.CrudDAO;
import lk.ijse.hospitel.dto.PaymentDTO;
import lk.ijse.hospitel.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment, String> {
    String searchPayment(String id) throws SQLException, ClassNotFoundException;
}
