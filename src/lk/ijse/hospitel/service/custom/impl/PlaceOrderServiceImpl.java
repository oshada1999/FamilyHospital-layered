package lk.ijse.hospitel.service.custom.impl;

import lk.ijse.hospitel.service.custom.PlaceOrderService;
import lk.ijse.hospitel.dao.DAOFactory;
import lk.ijse.hospitel.dao.custom.*;
import lk.ijse.hospitel.db.DBConnection;
import lk.ijse.hospitel.dto.*;
import lk.ijse.hospitel.entity.Medicine;
import lk.ijse.hospitel.entity.OrderDetail;
import lk.ijse.hospitel.entity.Orders;
import lk.ijse.hospitel.entity.Patient;
import lk.ijse.hospitel.service.util.Convertor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    private final OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final MedicineDAO medicineDAO= (MedicineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEDICINE);
    private final OrderDetailDAO orderDetailDAO= (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    private final PatientDAO patientDAO= (PatientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);
    private final QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    private final Convertor convertor=new Convertor();

    public boolean placeOrder(PlaceOrderDTO placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);


            boolean isOrderAdded = orderDAO.add(new Orders(placeOrder.getOrderId(), LocalDate.now(), placeOrder.getPatientId()));
            if (isOrderAdded) {

                boolean isUpdated = updateQty(placeOrder.getOrderDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded =saveOrderDetails(placeOrder.getOrderDetails());
                    if (isOrderDetailAdded) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
    private boolean updateQty(ArrayList<OrderDetailsDTO> orderDetails) throws SQLException, ClassNotFoundException {

        for (OrderDetailsDTO orderDetail : orderDetails) {
            if (!medicineDAO.updateMedicineQty(new Medicine(orderDetail.getId(),orderDetail.getName(),orderDetail.getType(),orderDetail.getDose(),orderDetail.getQty(),orderDetail.getUnitPrice()))) {
                return false;
            }
        }
        return true;
    }
    private boolean saveOrderDetails(ArrayList<OrderDetailsDTO> orderDetails) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDTO orderDetail : orderDetails) {
            if (!orderDetailDAO.add(new OrderDetail(orderDetail.getOrderId(),orderDetail.getId(),orderDetail.getName(),orderDetail.getType(),orderDetail.getDose(),orderDetail.getQty(),orderDetail.getUnitPrice()))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getAllMedicineCode() throws SQLException, ClassNotFoundException{
        return medicineDAO.getAllMedicineCode();
    }

    public ArrayList<String> getAllPatientCode() throws SQLException, ClassNotFoundException{
        return patientDAO.getAllPatientCode();
    }

    public String getNextOrderID() throws SQLException, ClassNotFoundException{
        return orderDAO.getNextID();
    }

    public MedicineDTO searchMedicine(String id) throws SQLException, ClassNotFoundException{
        Medicine searchMedicine = medicineDAO.search(id);
        return convertor.formMedicine(searchMedicine);
    }

    public PatientDTO searchPatient(String id) throws SQLException, ClassNotFoundException{
        Patient searchPatient = patientDAO.search(id);
        return convertor.fromPatient(searchPatient);
    }

    public ArrayList<String> getAllPaidAppointment() throws SQLException, ClassNotFoundException{
        return queryDAO.getAllPaidAppointment();
    }
}
