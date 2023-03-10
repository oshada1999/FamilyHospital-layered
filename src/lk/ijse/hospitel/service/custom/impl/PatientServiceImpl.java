package lk.ijse.hospitel.service.custom.impl;

import lk.ijse.hospitel.service.custom.PatientService;
import lk.ijse.hospitel.dao.DAOFactory;
import lk.ijse.hospitel.dao.custom.PatientDAO;
import lk.ijse.hospitel.dto.PatientDTO;
import lk.ijse.hospitel.entity.Patient;
import lk.ijse.hospitel.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;

public class PatientServiceImpl implements PatientService {
    private final PatientDAO patientDAO= (PatientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PATIENT);
    private final Convertor convertor=new Convertor();
    @Override
    public boolean addPatient(PatientDTO patient) throws SQLException, ClassNotFoundException{
        return patientDAO.add(convertor.toPatient(patient));
    }
    @Override
    public ArrayList<PatientDTO> getAllPatient() throws SQLException, ClassNotFoundException{
        ArrayList<Patient> all = patientDAO.getAll();
        ArrayList<PatientDTO> allPatient=new ArrayList<>();
        for (Patient patient : all) {
            allPatient.add(convertor.fromPatient(patient));
        }
        return allPatient;
    }
    @Override
    public boolean deletePatient(String id) throws SQLException, ClassNotFoundException{
        return patientDAO.delete(id);
    }
    @Override
    public boolean updatePatient(PatientDTO patient) throws SQLException, ClassNotFoundException{
        return patientDAO.update(convertor.toPatient(patient));
    }
    @Override
    public PatientDTO searchPatient(String id) throws SQLException, ClassNotFoundException{
        Patient search = patientDAO.search(id);
        return convertor.fromPatient(search);
    }

}
