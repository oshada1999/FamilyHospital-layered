package lk.ijse.hospitel.service.custom.impl;

import lk.ijse.hospitel.service.custom.MedicineService;
import lk.ijse.hospitel.dao.DAOFactory;
import lk.ijse.hospitel.dao.custom.MedicineDAO;
import lk.ijse.hospitel.dto.MedicineDTO;
import lk.ijse.hospitel.entity.Medicine;
import lk.ijse.hospitel.service.util.Convertor;

import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineServiceImpl implements MedicineService {
    private final MedicineDAO medicineDAO= (MedicineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEDICINE);
    private final Convertor convertor=new Convertor();

    @Override
    public boolean addMedicine(MedicineDTO medicine) throws SQLException, ClassNotFoundException{
        return medicineDAO.add(convertor.toMedicine(medicine));
    }
    @Override
    public ArrayList<MedicineDTO> getAllMedicine() throws SQLException, ClassNotFoundException{
        ArrayList<Medicine> all = medicineDAO.getAll();
        ArrayList<MedicineDTO> allMedicine=new ArrayList<>();
        for (Medicine medicine : all) {
            allMedicine.add(convertor.formMedicine(medicine));
        }
        return allMedicine;
    }
    @Override
    public boolean deleteMedicine(String id) throws SQLException, ClassNotFoundException{
        return medicineDAO.delete(id);
    }
    @Override
    public boolean updateMedicine(MedicineDTO medicine) throws SQLException, ClassNotFoundException{
        return medicineDAO.update(convertor.toMedicine(medicine));
    }

}
