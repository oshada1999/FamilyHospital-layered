package lk.ijse.hospitel.service.custom.impl;

import lk.ijse.hospitel.service.custom.AppointmentService;
import lk.ijse.hospitel.dao.DAOFactory;
import lk.ijse.hospitel.dao.custom.AppointmentDAO;
import lk.ijse.hospitel.dto.AppointmentDTO;
import lk.ijse.hospitel.entity.Appointment;
import lk.ijse.hospitel.service.util.Convertor;


import java.sql.SQLException;
import java.util.ArrayList;

public class  AppointmentServiceImpl implements AppointmentService {
    private final AppointmentDAO appointmentDAO= (AppointmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.APPOINTMENT);
    private final Convertor convertor=new Convertor();
    @Override
    public ArrayList<AppointmentDTO> getAllAppointment() throws SQLException, ClassNotFoundException{
        ArrayList<Appointment> all = appointmentDAO.getAll();
        ArrayList<AppointmentDTO> allAppointment=new ArrayList<>();

        for (Appointment appointment : all) {
            allAppointment.add(convertor.formAppointment(appointment));
        }
        return allAppointment;
    }
    @Override
    public boolean deleteAppointment(String id) throws SQLException, ClassNotFoundException{
        return appointmentDAO.delete(id);
    }
    @Override
    public boolean addAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException{
        return appointmentDAO.add(convertor.toAppointment(appointmentDTO));
    }
    @Override
    public String getNextAppointmentID() throws SQLException, ClassNotFoundException{
        return appointmentDAO.getNextID();
    }
    @Override
    public boolean updateAppointment(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException{
        return appointmentDAO.update(convertor.toAppointment(appointmentDTO));
    }
    @Override
    public AppointmentDTO searchAppointment(String id) throws SQLException, ClassNotFoundException{
        Appointment search = appointmentDAO.search(id);
        return convertor.formAppointment(search);
    }

}
