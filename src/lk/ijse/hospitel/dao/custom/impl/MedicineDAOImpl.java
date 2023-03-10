package lk.ijse.hospitel.dao.custom.impl;

import lk.ijse.hospitel.dao.custom.MedicineDAO;
import lk.ijse.hospitel.dto.MedicineDTO;
import lk.ijse.hospitel.entity.Medicine;
import lk.ijse.hospitel.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineDAOImpl implements MedicineDAO {
    @Override
    public boolean add(Medicine medicine) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO medicine VALUES(?,?,?,?,?,?)",
                medicine.getmId() ,
                medicine.getName(),
                medicine.getType(),
                medicine.getDose(),
                medicine.getQtyOnHand(),
                medicine.getUnitPrice()
        );
    }
    @Override
    public ArrayList<Medicine> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM medicine");

        ArrayList<Medicine> medicineList=new ArrayList<>();
        while (rst.next()){
            Medicine medicine=new Medicine(rst.getString("mId"),rst.getString("name"),rst.getString("type"),rst.getString("dose"),rst.getInt("qtyOnHand"),rst.getDouble("unitPrice"));
            medicineList.add(medicine);
        }
        return medicineList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM medicine WHERE mId=?", id);

    }
    @Override
    public boolean update(Medicine medicine) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update medicine set name=?,type=?,dose=?,qtyOnHand=?,unitPrice=? where mId=?",
                medicine.getName(),
                medicine.getType(),
                medicine.getDose(),
                medicine.getQtyOnHand(),
                medicine.getUnitPrice(),
                medicine.getmId()
        );
    }
    @Override
    public ArrayList<String> getAllMedicineCode() throws SQLException, ClassNotFoundException {
        String sql="SELECT mId FROM MEDICINE";
        ResultSet result=CrudUtil.execute(sql);

        ArrayList<String> codeList=new ArrayList<>();

        while (result.next()){
            codeList.add(result.getString(1));
        }
        return codeList;
    }

    @Override
    public Medicine search(String code) throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM MEDICINE WHERE mId=?";
        ResultSet result=CrudUtil.execute(sql,code);

        if (result.next()){
            return new Medicine(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getInt(5),
                    result.getDouble(6)

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

    @Override
    public boolean updateMedicineQty(Medicine medicine) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Medicine SET qtyOnHand = qtyOnHand - ? WHERE mId = ?";
        return CrudUtil.execute(sql,medicine.getQtyOnHand(),medicine.getmId());
    }
}
