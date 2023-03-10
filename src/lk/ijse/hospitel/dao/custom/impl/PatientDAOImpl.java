package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.PatientDAO;
import lk.ijse.hospitel.dto.PatientDTO;
import lk.ijse.hospitel.entity.Patient;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientDAOImpl implements PatientDAO {
    @Override
    public boolean add(Patient patient) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO patient VALUES(?,?,?,?,?,?,?)",
                patient.getpId(),
                patient.getName(),
                patient.getAddress(),
                patient.getPhone(),
                patient.getAge(),
                patient.getDisease(),
                patient.getGender()

        );
    }
    @Override
    public ArrayList<Patient> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM patient");

        ArrayList<Patient> patientList=new ArrayList<>();
        while (rst.next()){
            Patient patient=new Patient(rst.getString("pId"),rst.getString("name"),rst.getString("address"),rst.getString("phone"),rst.getInt("age"),rst.getString("disease"),rst.getString("gender"));
            patientList.add(patient);
        }
        return patientList;
    }
    @Override
    public ArrayList<String> getAllPatientCode() throws SQLException, ClassNotFoundException {
        String sql="SELECT pId FROM patient";
        ResultSet result=CrudUtil.execute(sql);

        ArrayList<String> codeList=new ArrayList<>();

        while (result.next()){
            codeList.add(result.getString(1));
        }
        return codeList;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM patient WHERE pId=?", id);

    }
    @Override
    public boolean update(Patient patient) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update patient set name=?,address=?,phone=?,age=?,disease=?,gender=? where pId=?",
                patient.getName(),
                patient.getAddress(),
                patient.getPhone(),
                patient.getAge(),
                patient.getDisease(),
                patient.getGender(),
                patient.getpId()

        );
    }
    @Override
    public Patient search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM patient WHERE pId = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Patient(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getInt(5),
                    result.getString(6),
                    result.getString(7)
            );
        }
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
