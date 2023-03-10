package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.AppointmentDAO;
import lk.ijse.hospitel.dao.exception.ConstraintViolationException;
import lk.ijse.hospitel.dto.AppointmentDTO;
import lk.ijse.hospitel.entity.Appointment;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public  boolean add(Appointment appointment)throws ConstraintViolationException {
        try {
            return CrudUtil.execute("INSERT INTO appointment VALUES(?,?,?,?,?,?,?,?)",
                    appointment.getaId(),
                    appointment.getpName(),
                    appointment.getPatientID(),
                    appointment.getAge(),
                    appointment.getpAddress(),
                    appointment.getdName(),
                    appointment.getDoctorID(),
                    appointment.getDate()
            );
        } catch (SQLException e) {
           throw new ConstraintViolationException(e);
        }
    }
    @Override
    public ArrayList<Appointment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM appointment");

        ArrayList<Appointment> appointmentList=new ArrayList<>();
        while (rst.next()){
            Appointment appointment=new Appointment(rst.getString("aId"),rst.getString("pName"),rst.getString("patientID"),rst.getInt("age"),rst.getString("pAddress"),rst.getString("dName"),rst.getString("doctorID"),rst.getString("Date"));
            appointmentList.add(appointment);
        }
        return appointmentList;
    }

    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM appointment WHERE aId=?", id);

    }

    public  boolean update(Appointment appointment)throws ConstraintViolationException{
        try {
            return CrudUtil.execute("Update appointment set pName=?,disease=?,age=?,pAddress=?,dName=?,dDepartment=?,Date=? where aId=?",
                    appointment.getpName(),
                    appointment.getPatientID(),
                    appointment.getAge(),
                    appointment.getpAddress(),
                    appointment.getdName(),
                    appointment.getDoctorID(),
                    appointment.getDate(),
                    appointment.getaId()
            );
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }
    @Override
    public  Appointment search(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT  * FROM appointment WHERE aId = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if (result.next()) {
            return new Appointment(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    result.getString(8)


            );
        }
        return null;
    }
    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =CrudUtil.
                execute("SELECT aId FROM appointment ORDER BY aId DESC LIMIT 1");
        String lastAppointmenrID="";
        if (resultSet.next()){
            lastAppointmenrID=resultSet.getString(1);
        }
        String nextAppointId= generateNextAppointmentId(lastAppointmenrID);
        return nextAppointId;
    }

    @Override
    public boolean existByPk(String aId) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM appointment WHERE aId=?", aId);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextAppointmentId(String lastAppointmentID) {
        String date="";
        String newDate="";
        date=new SimpleDateFormat("yyyy/MM").format(new Date());
        newDate=date;

        if((lastAppointmentID.equals(""))==false) {
            String[] ids = lastAppointmentID.split("A");
            int id = Integer.parseInt(ids[1]);
            id += 1;


            boolean isEquals=isDateEquals(ids[0],newDate);
            if(!isEquals){
                ids[0]=newDate;
                id=1;
            }

            String newOrderId=String.format("A%04d",id);
            return ids[0] + newOrderId;
        }

        return newDate+"A0001";
    }

    private boolean isDateEquals(String id, String date) {
        if(id.equals(date)){
            return true;
        }
        return false;
    }
}
