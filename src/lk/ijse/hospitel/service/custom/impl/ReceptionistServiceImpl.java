package lk.ijse.hospitel.service.custom.impl;

import lk.ijse.hospitel.service.custom.ReceptionistService;
import lk.ijse.hospitel.dao.DAOFactory;
import lk.ijse.hospitel.dao.custom.ReceptionistDAO;
import lk.ijse.hospitel.dto.ReceptionistDTO;
import lk.ijse.hospitel.entity.Receptionist;
import lk.ijse.hospitel.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReceptionistServiceImpl implements ReceptionistService {
    private final ReceptionistDAO receptionistDAO= (ReceptionistDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RECEPTIONIST);
    private final Convertor convertor=new Convertor();
    @Override
    public boolean addReceptionist(ReceptionistDTO receptionist) throws SQLException, ClassNotFoundException{
        return receptionistDAO.add(convertor.toReceptionist(receptionist));
    }
    @Override
    public ArrayList<ReceptionistDTO> getAllReceptionist() throws SQLException, ClassNotFoundException{
        ArrayList<Receptionist> all = receptionistDAO.getAll();
        ArrayList<ReceptionistDTO> allReceptionist=new ArrayList<>();
        for (Receptionist receptionist : all) {
            allReceptionist.add(convertor.formReceptionist(receptionist));
        }
        return allReceptionist;
    }
    @Override
    public boolean deleteReceptionist(String id) throws SQLException, ClassNotFoundException{
        return receptionistDAO.delete(id);
    }
    @Override
    public boolean updateReceptionist(ReceptionistDTO receptionist) throws SQLException, ClassNotFoundException{
        return receptionistDAO.update(convertor.toReceptionist(receptionist));
    }
}
