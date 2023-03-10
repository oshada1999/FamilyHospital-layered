package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.OrderDAO;
import lk.ijse.hospitel.dto.OrderDTO;
import lk.ijse.hospitel.entity.Orders;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Orders order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Orders VALUES(?, ?, ?)";
        return CrudUtil.execute(sql, order.getOrderId(), LocalDate.now(), order.getPatientId());
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Orders dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Orders search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =CrudUtil.
                execute("SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1");
        String lastReceiptID= "";
        if (resultSet.next()){
            lastReceiptID=resultSet.getString(1);
        }
        String nextReceiptId= generateNextAppointmentId(lastReceiptID);

        return nextReceiptId;
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    private String generateNextAppointmentId(String lastReceiptID) {
        String date="";
        String newDate="";
        date=new SimpleDateFormat("yyyy/MM").format(new Date());
        newDate=date;

        if((lastReceiptID.equals(""))==false) {
            String[] ids = lastReceiptID.split("R");
            int id = Integer.parseInt(ids[1]);
            id += 1;


            boolean isEquals=isDateEquals(ids[0],newDate);
            if(!isEquals){
                ids[0]=newDate;
                id=1;
            }

            String newReceiptId=String.format("R%04d",id);
            return ids[0] + newReceiptId;
        }

        return newDate+"R0001";
    }

    private boolean isDateEquals(String id, String date) {
        if(id.equals(date)){
            return true;
        }
        return false;
    }
}
