package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.PaymentDAO;
import lk.ijse.hospitel.dto.PaymentDTO;
import lk.ijse.hospitel.entity.Payment;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean add(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO payment VALUES(?,?,?,?,?,?)",
                payment.getPayId(),
                payment.getDate(),
                payment.getChannelling_cost(),
                payment.getOther_cost(),
                payment.getTotal(),
                payment.getaId()
        );
    }
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM payment");

        ArrayList<Payment> paymentList=new ArrayList<>();
        while (rst.next()){
            Payment payment=new Payment(rst.getString("payId"),rst.getString("date"),rst.getDouble("channelling_cost"),rst.getDouble("other_cost"),rst.getDouble("total"),rst.getString("aId"));
            paymentList.add(payment);
        }
        return paymentList;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Payment payment) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update payment set date=?,channelling_cost=?,other_cost=?,total=?,aId=? where payId=?",
                payment.getDate(),
                payment.getChannelling_cost(),
                payment.getOther_cost(),
                payment.getTotal(),
                payment.getaId(),
                payment.getPayId()
        );
    }

    @Override
    public Payment search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String searchPayment(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM payment WHERE aId = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return result.getString(6);

        }
        return null;
    }
    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =CrudUtil.
                execute("SELECT payId FROM payment ORDER BY payId DESC LIMIT 1");
        String lastPayID="";
        if (resultSet.next()){
            lastPayID=resultSet.getString(1);
        }
        String nextPayId= generateNextPayId(lastPayID);
        return nextPayId;
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    private String generateNextPayId(String lastPayID) {
        String date="";
        String newDate="";
        date=new SimpleDateFormat("yyyy/MM").format(new Date());
        newDate=date;

        if((lastPayID.equals(""))==false) {
            String[] ids = lastPayID.split("P");
            int id = Integer.parseInt(ids[1]);
            id += 1;


            boolean isEquals=isDateEquals(ids[0],newDate);
            if(!isEquals){
                ids[0]=newDate;
                id=1;
            }

            String newOrderId=String.format("P%04d",id);
            return ids[0] + newOrderId;
        }

        return newDate+"P0001";
    }

    private boolean isDateEquals(String id, String date) {
        if(id.equals(date)){
            return true;
        }
        return false;
    }
}
