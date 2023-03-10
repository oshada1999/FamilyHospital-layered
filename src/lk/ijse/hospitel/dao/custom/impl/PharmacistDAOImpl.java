package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.PharmacistDAO;
import lk.ijse.hospitel.dto.PharmacistDTO;
import lk.ijse.hospitel.entity.Pharmacist;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PharmacistDAOImpl implements PharmacistDAO {
    @Override
    public boolean add(Pharmacist pharmacist) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO pharmacist VALUES(?,?,?,?,?,?)",
                pharmacist.getpId(),
                pharmacist.getName(),
                pharmacist.getAddress(),
                pharmacist.getContact(),
                pharmacist.getDob(),
                pharmacist.getGender()
        );
    }
    @Override
    public ArrayList<Pharmacist> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM pharmacist");

        ArrayList<Pharmacist> pharmacistList = new ArrayList<>();
        while (rst.next()) {
            Pharmacist pharmacist = new Pharmacist(rst.getString("pId"), rst.getString("name"), rst.getString("address"), rst.getString("contact"), rst.getString("dob"), rst.getString("gender"));
            pharmacistList.add(pharmacist);

        }
        return pharmacistList;
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM pharmacist WHERE pId=?", id);
    }
    @Override
    public boolean update(Pharmacist pharmacist) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update pharmacist set name=?,address=?,contact=?,dob=?,gender=? where pId=?",
                pharmacist.getName(),
                pharmacist.getAddress(),
                pharmacist.getContact(),
                pharmacist.getDob(),
                pharmacist.getGender(),
                pharmacist.getpId()
        );
    }

    @Override
    public Pharmacist search(String s) throws SQLException, ClassNotFoundException {
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
