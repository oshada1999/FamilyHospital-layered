package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.OrderDetailDAO;
import lk.ijse.hospitel.dto.OrderDetailsDTO;
import lk.ijse.hospitel.entity.OrderDetail;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean add(OrderDetail orderDetails) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO OrderDetail VALUES(?, ?, ?, ?,?,?,?)";
        return CrudUtil.execute(sql,orderDetails.getOrderID(),orderDetails.getMediCode(),orderDetails.getName(),orderDetails.getType(),orderDetails.getDose(),orderDetails.getOrderQTY(),orderDetails.getUnitPrice());
    }


    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
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
