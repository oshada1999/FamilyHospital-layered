package lk.ijse.hospitel.service.custom.impl;

import lk.ijse.hospitel.service.custom.PharmacistService;
import lk.ijse.hospitel.dao.DAOFactory;
import lk.ijse.hospitel.dao.custom.PharmacistDAO;
import lk.ijse.hospitel.dto.PharmacistDTO;
import lk.ijse.hospitel.entity.Pharmacist;
import lk.ijse.hospitel.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;

public class PharmacistServiceImpl implements PharmacistService {
    private final PharmacistDAO pharmacistDAO= (PharmacistDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PHARMACIST);
    private final Convertor convertor=new Convertor();
    @Override
    public boolean addPharmacist(PharmacistDTO pharmacist) throws SQLException, ClassNotFoundException{
        return pharmacistDAO.add(convertor.toPharmacist(pharmacist));
    }
    @Override
    public ArrayList<PharmacistDTO> getAllPharmacist() throws SQLException, ClassNotFoundException{
        ArrayList<Pharmacist> all = pharmacistDAO.getAll();
        ArrayList<PharmacistDTO> allPharmacist=new ArrayList<>();
        for (Pharmacist pharmacist : all) {
            allPharmacist.add(convertor.fromPharmacist(pharmacist));
        }
        return allPharmacist;
    }
    @Override
    public boolean deletePharmacist(String id) throws SQLException, ClassNotFoundException{
        return pharmacistDAO.delete(id);
    }
    @Override
    public boolean updatePharmacist(PharmacistDTO pharmacist) throws SQLException, ClassNotFoundException{
        return pharmacistDAO.update(convertor.toPharmacist(pharmacist));
    }
}
