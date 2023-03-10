package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.DoctorDAO;
import lk.ijse.hospitel.dto.DoctorDTO;
import lk.ijse.hospitel.entity.Doctor;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorDAOImpl implements DoctorDAO {
    @Override
    public  boolean add(Doctor doctor) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO doctor VALUES(?,?,?,?,?,?,?,?,?)",
                doctor.getDocID(),
                doctor.getName(),
                doctor.getAddress(),
                doctor.getContact(),
                doctor.getDay(),
                doctor.getStartTime(),
                doctor.getEndTime(),
                doctor.getDepartment(),
                doctor.getGender()
        );
    }
    @Override
    public ArrayList<Doctor> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Doctor");

        ArrayList<Doctor> doctorList=new ArrayList<>();
        while (rst.next()){
            Doctor doctor=new Doctor(rst.getString("DocID"),rst.getString("Name"),rst.getString("Address"),rst.getString("Contact"),rst.getString("Day"),rst.getString("StartTime"),rst.getString("EndTime"),rst.getString("Department"),rst.getString("Gender"));
            doctorList.add(doctor);
        }
        return doctorList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM doctor WHERE DocID=?", id);

    }
    @Override
    public boolean update(Doctor doctor) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update doctor set Name=?,Address=?,Contact=?,Day=?,StartTime=?,EndTime=?,Department=?,Gender=? where DocID=?",
                doctor.getName(),
                doctor.getAddress(),
                doctor.getContact(),
                doctor.getDay(),
                doctor.getStartTime(),
                doctor.getEndTime(),
                doctor.getDepartment(),
                doctor.getGender(),
                doctor.getDocID()
        );
    }
    @Override
    public Doctor search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM doctor WHERE DocID = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Doctor(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    result.getString(8),
                    result.getString(9)

            );
        }
        return null;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean existByPk(String DocID) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM doctor WHERE DocID=?", DocID);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
