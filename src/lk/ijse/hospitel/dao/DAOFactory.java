package lk.ijse.hospitel.dao;

import lk.ijse.hospitel.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
      return   (null==daoFactory)? daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        APPOINTMENT,DOCTOR,MEDICINE,NURSE,ORDER,ORDERDETAIL,PAIDPATIENT,PATIENT,PAYMENT,PHARMACIST,QUERYDAO,RECEIPT,RECEIPTDETAILS,RECEPTIONIST
    }
    public SupperDAO getDAO(DAOTypes types){
        switch (types){
            case DOCTOR :
                return new DoctorDAOImpl();
            case APPOINTMENT:
                return new AppointmentDAOImpl();
            case MEDICINE:
                return new MedicineDAOImpl();
            case NURSE:
                return new NurseDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            case PATIENT:
                return new PatientDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PHARMACIST:
                return new PharmacistDAOImpl();
            case QUERYDAO:
                return new QueryDAOImpl();
            case RECEIPT:
                return new ReceiptDAOImpl();
            case RECEIPTDETAILS:
                return new ReceiptDetailsDAOImpl();
            case RECEPTIONIST:
                return new ReceptionistDAOImpl();
            default:
                return null;
        }
    }
}
