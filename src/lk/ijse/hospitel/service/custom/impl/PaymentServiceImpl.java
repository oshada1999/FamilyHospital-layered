package lk.ijse.hospitel.service.custom.impl;

import lk.ijse.hospitel.service.custom.PaymentService;
import lk.ijse.hospitel.dao.DAOFactory;
import lk.ijse.hospitel.dao.custom.PaymentDAO;
import lk.ijse.hospitel.dto.PaymentDTO;
import lk.ijse.hospitel.entity.Payment;
import lk.ijse.hospitel.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    private final Convertor convertor=new Convertor();

    @Override
    public boolean addPayment(PaymentDTO payment) throws SQLException, ClassNotFoundException{
        return paymentDAO.add(convertor.toPayment(payment));
    }
    @Override
    public ArrayList<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException{
        ArrayList<Payment> all = paymentDAO.getAll();
        ArrayList<PaymentDTO> allPayment=new ArrayList<>();
        for (Payment payment : all) {
            allPayment.add(convertor.formPayment(payment));
        }
        return allPayment;
    }
    @Override
    public boolean updatePayment(PaymentDTO payment) throws SQLException, ClassNotFoundException{
        return paymentDAO.update(convertor.toPayment(payment));
    }
    @Override
    public String searchPaymentId(String id) throws SQLException, ClassNotFoundException{
        return paymentDAO.searchPayment(id);
    }
    @Override
    public String getNextPaymentID() throws SQLException, ClassNotFoundException{
        return paymentDAO.getNextID();
    }
}
