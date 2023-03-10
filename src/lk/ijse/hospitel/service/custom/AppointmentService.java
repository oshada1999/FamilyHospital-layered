package lk.ijse.hospitel.service.custom;

import lk.ijse.hospitel.service.SuperService;
import lk.ijse.hospitel.dto.AppointmentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentService extends SuperService {

    ArrayList<AppointmentDTO> getAllAppointment() throws SQLException, ClassNotFoundException;

    boolean deleteAppointment(String id) throws SQLException, ClassNotFoundException;

    boolean addAppointment(AppointmentDTO appointment) throws SQLException, ClassNotFoundException;

    String getNextAppointmentID() throws SQLException, ClassNotFoundException;

    boolean updateAppointment(AppointmentDTO appointment) throws SQLException, ClassNotFoundException;

    AppointmentDTO searchAppointment(String id) throws SQLException, ClassNotFoundException;
}
