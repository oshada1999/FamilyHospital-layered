package lk.ijse.hospitel.service.custom.impl;

import lk.ijse.hospitel.service.custom.PlaceReceiptService;
import lk.ijse.hospitel.dao.DAOFactory;
import lk.ijse.hospitel.dao.custom.MedicineDAO;
import lk.ijse.hospitel.dao.custom.ReceiptDAO;
import lk.ijse.hospitel.dao.custom.ReceiptDetailsDAO;
import lk.ijse.hospitel.db.DBConnection;
import lk.ijse.hospitel.dto.MedicineDTO;
import lk.ijse.hospitel.dto.PlaceReceiptDTO;
import lk.ijse.hospitel.dto.ReceiptDetailsDTO;
import lk.ijse.hospitel.entity.Medicine;
import lk.ijse.hospitel.entity.Receipt;
import lk.ijse.hospitel.entity.ReceiptDetails;
import lk.ijse.hospitel.service.util.Convertor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlaceReceiptServiceImpl implements PlaceReceiptService {
    private final ReceiptDAO receiptDAO = (ReceiptDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RECEIPT);
    private final ReceiptDetailsDAO receiptDetailsDAO = (ReceiptDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RECEIPTDETAILS);
    private final MedicineDAO medicineDAO= (MedicineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEDICINE);
    private final Convertor convertor=new Convertor();

    public boolean PurchaseReceipt(PlaceReceiptDTO placeReceipt) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);

            boolean isReceiptAdded = receiptDAO.add(new Receipt(placeReceipt.getReceiptId(), LocalDate.now(),placeReceipt.getPatientId()));

            if (isReceiptAdded) {
                boolean isReceiptDetailAdded = savereceiptDetails(placeReceipt.getReceiptDetail());
                if (isReceiptDetailAdded) {
                    DBConnection.getInstance().getConnection().commit();
                    return true;

                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
    private boolean savereceiptDetails(ArrayList<ReceiptDetailsDTO> receiptDetail) throws SQLException, ClassNotFoundException {
        for (ReceiptDetailsDTO receiptd : receiptDetail) {
            if (!receiptDetailsDAO.add(new ReceiptDetails(receiptd.getMedicineCode(),receiptd.getMedicineName(),receiptd.getDose(),receiptd.getReceiptId()))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public ArrayList<String> getAllMedicineCode() throws SQLException, ClassNotFoundException{
        return medicineDAO.getAllMedicineCode();
    }

    @Override
    public String getNextReceiptID() throws SQLException, ClassNotFoundException {
        return receiptDAO.getNextID();
    }

    @Override
    public MedicineDTO searchMedicine(String code) throws SQLException, ClassNotFoundException {
        Medicine search = medicineDAO.search(code);
        return convertor.formMedicine(search);
    }
}
