package lk.ijse.hospitel.service.custom.impl;

import lk.ijse.hospitel.service.custom.NurseService;
import lk.ijse.hospitel.dao.DAOFactory;
import lk.ijse.hospitel.dao.custom.NurseDAO;
import lk.ijse.hospitel.dto.NurseDTO;
import lk.ijse.hospitel.entity.Nurse;
import lk.ijse.hospitel.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;

public class NurseServiceImpl implements NurseService {
    private final NurseDAO nurseDAO= (NurseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.NURSE);
    private final Convertor convertor=new Convertor();

    @Override
    public boolean addNurse(NurseDTO nurse) throws SQLException, ClassNotFoundException{
        return nurseDAO.add(convertor.toNurse(nurse));
    }
    @Override
    public ArrayList<NurseDTO> getAllNurse() throws SQLException, ClassNotFoundException{

        ArrayList<Nurse> list = nurseDAO.getAll();
        ArrayList<NurseDTO> allNurse=new ArrayList<>();
        for (Nurse nurse : list) {
            allNurse.add(convertor.fromNurse(nurse));
        }
        return allNurse;
    }
    @Override
    public boolean deleteNurse(String id) throws SQLException, ClassNotFoundException{
        return nurseDAO.delete(id);
    }
    @Override
    public boolean updateNurse(NurseDTO nurse) throws SQLException, ClassNotFoundException{
        return nurseDAO.update(convertor.toNurse(nurse));
    }
}
