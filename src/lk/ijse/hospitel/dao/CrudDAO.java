package lk.ijse.hospitel.dao;

import lk.ijse.hospitel.entity.SuperEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T extends SuperEntity,ID>extends SupperDAO{
    boolean add(T dto) throws SQLException, ClassNotFoundException;

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    T search(ID id) throws SQLException, ClassNotFoundException;

    String getNextID() throws SQLException, ClassNotFoundException;

    boolean existByPk(ID pk) ;

}
