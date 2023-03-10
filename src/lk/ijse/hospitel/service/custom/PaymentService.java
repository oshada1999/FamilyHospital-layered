package lk.ijse.hospitel.service.custom;

import lk.ijse.hospitel.service.SuperService;
import lk.ijse.hospitel.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentService extends SuperService {

    boolean addPayment(PaymentDTO payment) throws SQLException, ClassNotFoundException;

    ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException;

    boolean updatePayment(PaymentDTO payment) throws SQLException, ClassNotFoundException;

    String searchPaymentId(String id) throws SQLException, ClassNotFoundException;

    String getNextPaymentID() throws SQLException, ClassNotFoundException;
}
