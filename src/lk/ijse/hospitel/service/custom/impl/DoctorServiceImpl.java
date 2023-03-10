package lk.ijse.hospitel.service.custom.impl;

import lk.ijse.hospitel.service.custom.DoctorService;
import lk.ijse.hospitel.dao.DAOFactory;
import lk.ijse.hospitel.dao.custom.DoctorDAO;
import lk.ijse.hospitel.dto.DoctorDTO;
import lk.ijse.hospitel.entity.Doctor;
import lk.ijse.hospitel.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorServiceImpl implements DoctorService {
    private final DoctorDAO doctorDAO= (DoctorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DOCTOR);
    private final Convertor convertor=new Convertor();
    @Override
    public  boolean addDoctor(DoctorDTO doctor) throws SQLException, ClassNotFoundException{
        if (doctorDAO.existByPk(doctor.getDoctorId())){
            return false;
        }
        return doctorDAO.add(convertor.toDoctor(doctor));
    }
    @Override
    public ArrayList<DoctorDTO> getAllDoctor() throws SQLException, ClassNotFoundException{
        ArrayList<Doctor> all = doctorDAO.getAll();
        ArrayList<DoctorDTO> allDoctor=new ArrayList<>();

        for (Doctor doctor : all) {
            allDoctor.add(convertor.fromDoctor(doctor));
        }
        return allDoctor;
    }
    @Override
    public boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException{
        return doctorDAO.delete(id);
    }
    @Override
    public boolean updateDoctor(DoctorDTO doctor) throws SQLException, ClassNotFoundException{
        return doctorDAO.update(convertor.toDoctor(doctor));
    }
    @Override
    public DoctorDTO searchDoctor(String id) throws SQLException, ClassNotFoundException{
        Doctor searchDoctor = doctorDAO.search(id);
        return convertor.fromDoctor(searchDoctor);
    }
}
