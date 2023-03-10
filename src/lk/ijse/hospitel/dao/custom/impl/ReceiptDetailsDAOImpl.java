package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.ReceiptDetailsDAO;
import lk.ijse.hospitel.dto.ReceiptDetailsDTO;
import lk.ijse.hospitel.entity.ReceiptDetails;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReceiptDetailsDAOImpl implements ReceiptDetailsDAO {
    @Override
    public boolean add(ReceiptDetails receiptDetails) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO receiptdetails VALUES(?,?,?,?)",
                receiptDetails.getMediCode(),
                receiptDetails.getName(),
                receiptDetails.getDose(),
                receiptDetails.getReceiptID()
        );
    }

    @Override
    public ArrayList<ReceiptDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ReceiptDetails dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ReceiptDetails search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }
}
