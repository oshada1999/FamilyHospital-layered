package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.ReceptionistDAO;
import lk.ijse.hospitel.dto.ReceptionistDTO;
import lk.ijse.hospitel.entity.Receptionist;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReceptionistDAOImpl implements ReceptionistDAO {
    @Override
    public boolean add(Receptionist receptionist) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO receptionist VALUES(?,?,?,?,?,?)",
                receptionist.getrId(),
                receptionist.getName(),
                receptionist.getAddress(),
                receptionist.getContact(),
                receptionist.getDob(),
                receptionist.getGender()
        );
    }
    @Override
    public ArrayList<Receptionist> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Receptionist");

        ArrayList<Receptionist> receptionistList = new ArrayList<>();
        while (rst.next()) {
            Receptionist receptionist = new Receptionist(rst.getString("rId"), rst.getString("name"), rst.getString("address"), rst.getString("contact"), rst.getString("dob"), rst.getString("gender"));
            receptionistList.add(receptionist);

        }
        return receptionistList;
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM receptionist WHERE rId=?", id);
    }
    @Override
    public boolean update(Receptionist receptionist) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update receptionist set name=?,address=?,contact=?,dob=?,gender=? where rId=?",
                receptionist.getName(),
                receptionist.getAddress(),
                receptionist.getContact(),
                receptionist.getDob(),
                receptionist.getGender(),
                receptionist.getrId()
        );
    }

    @Override
    public Receptionist search(String s) throws SQLException, ClassNotFoundException {
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
