package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.QueryDAO;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<String> getAllPaidAppointment() throws SQLException, ClassNotFoundException {
        ResultSet rst= CrudUtil.execute("select * from appointment a inner join payment p on a.aId=p.aId");
        ArrayList<String> paidList=new ArrayList<>();
        while (rst.next()){
            String paidId=rst.getString("aId");
            paidList.add(paidId);
            System.out.println(paidId);
        }
        return paidList;
    }
}
