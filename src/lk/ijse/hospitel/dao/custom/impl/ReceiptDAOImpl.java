package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.ReceiptDAO;
import lk.ijse.hospitel.dto.ReceiptDTO;
import lk.ijse.hospitel.entity.Receipt;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReceiptDAOImpl implements ReceiptDAO {
    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.
                execute("SELECT ReID FROM receipt ORDER BY ReID DESC LIMIT 1");
        String lastReceiptID="";
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
    @Override
    public boolean add(Receipt receipt) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO receipt VALUES(?,?,?)",
                receipt.getReID(),
                receipt.getDate(),
                receipt.getPatientId()
        );
    }

    @Override
    public ArrayList<Receipt> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Receipt dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Receipt search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
