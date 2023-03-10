package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.NurseDAO;
import lk.ijse.hospitel.dto.NurseDTO;
import lk.ijse.hospitel.entity.Nurse;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NurseDAOImpl implements NurseDAO{
    @Override
    public boolean add(Nurse nurse) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO nurse VALUES(?,?,?,?,?,?)",
                nurse.getnId(),
                nurse.getName(),
                nurse.getAddress(),
                nurse.getContact(),
                nurse.getDepartment(),
                nurse.getGender()
        );

    }
    @Override
    public ArrayList<Nurse> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM nurse");

        ArrayList<Nurse> nurseList=new ArrayList<>();
        while (rst.next()){
            Nurse nurse=new Nurse(rst.getString("nId"),rst.getString("name"),rst.getString("address"),rst.getString("contact"),rst.getString("department"),rst.getString("gender"));
            nurseList.add(nurse);

        }
        return nurseList;
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM nurse WHERE nId=?", id);

    }
    @Override
    public boolean update(Nurse nurse) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update nurse set name=?,address=?,contact=?,department=?,gender=? where nId=?",
                nurse.getName(),
                nurse.getAddress(),
                nurse.getContact(),
                nurse.getDepartment(),
                nurse.getGender(),
                nurse.getnId()
        );
    }

    @Override
    public Nurse search(String s) throws SQLException, ClassNotFoundException {
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
