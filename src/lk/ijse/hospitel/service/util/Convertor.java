package lk.ijse.hospitel.service.util;

import lk.ijse.hospitel.dto.*;
import lk.ijse.hospitel.entity.*;

public class Convertor {
    public AppointmentDTO formAppointment(Appointment appointment){
        return new AppointmentDTO(appointment.getaId(),appointment.getpName(),appointment.getPatientID(),appointment.getAge(),appointment.getpAddress(),appointment.getdName(),appointment.getDoctorID(),appointment.getDate());
    }
    public Appointment toAppointment(AppointmentDTO appointmentDTO){
        return new Appointment(appointmentDTO.getaId(),appointmentDTO.getpName(),appointmentDTO.getPatientID(),appointmentDTO.getAge(),appointmentDTO.getpAddress(),appointmentDTO.getdName(),appointmentDTO.getDoctorID(),appointmentDTO.getDate());
    }
    public DoctorDTO fromDoctor(Doctor doctor){
        return new DoctorDTO(doctor.getDocID(),doctor.getName(),doctor.getAddress(),doctor.getContact(),doctor.getDay(),doctor.getStartTime(), doctor.getEndTime(),doctor.getDepartment(),doctor.getGender());
    }
    public Doctor toDoctor(DoctorDTO doctorDTO){
        return new Doctor(doctorDTO.getDoctorId(),doctorDTO.getName(),doctorDTO.getAddress(),doctorDTO.getContact(),doctorDTO.getDay(),doctorDTO.getStartTime(),doctorDTO.getEndTime(),doctorDTO.getDepartment(),doctorDTO.getGender());
    }
    public MedicineDTO formMedicine(Medicine medicine){
        return new MedicineDTO(medicine.getmId(),medicine.getName(),medicine.getType(),medicine.getDose(),medicine.getQtyOnHand(),medicine.getUnitPrice());
    }
    public Medicine toMedicine(MedicineDTO medicineDTO){
        return new Medicine(medicineDTO.getId(),medicineDTO.getName(),medicineDTO.getType(),medicineDTO.getDose(),medicineDTO.getQty(),medicineDTO.getUnitPrice());
    }
    public NurseDTO fromNurse(Nurse nurse){
        return new NurseDTO(nurse.getnId(),nurse.getName(),nurse.getAddress(),nurse.getContact(),nurse.getDepartment(),nurse.getGender());
    }
    public Nurse toNurse(NurseDTO nurseDTO){
        return new Nurse(nurseDTO.getId(),nurseDTO.getName(),nurseDTO.getAddress(),nurseDTO.getContact(),nurseDTO.getDepartment(),nurseDTO.getGender());
    }
    public PatientDTO fromPatient(Patient patient){
        return new PatientDTO(patient.getpId(),patient.getName(),patient.getAddress(),patient.getPhone(),patient.getAge(),patient.getDisease(),patient.getGender());
    }
    public Patient toPatient(PatientDTO patientDTO){
        return new Patient(patientDTO.getId(),patientDTO.getName(),patientDTO.getAddress(),patientDTO.getPhone(),patientDTO.getAge(),patientDTO.getDisease(),patientDTO.getGender());
    }
    public PaymentDTO formPayment(Payment payment){
        return new PaymentDTO(payment.getPayId(),payment.getDate(),payment.getChannelling_cost(),payment.getOther_cost(),payment.getTotal(),payment.getaId());
    }
    public Payment toPayment(PaymentDTO paymentDTO){
        return new Payment(paymentDTO.getPaymentID(),paymentDTO.getDate(),paymentDTO.getChannelling_cost(),paymentDTO.getOther_cost(),paymentDTO.getTotal(),paymentDTO.getAppointmentID());
    }
    public PharmacistDTO fromPharmacist(Pharmacist pharmacist){
        return new PharmacistDTO(pharmacist.getpId(),pharmacist.getName(),pharmacist.getAddress(),pharmacist.getContact(),pharmacist.getDob(),pharmacist.getGender());
    }
    public Pharmacist toPharmacist(PharmacistDTO pharmacistDTO){
        return new Pharmacist(pharmacistDTO.getId(),pharmacistDTO.getName(),pharmacistDTO.getAddress(),pharmacistDTO.getContact(),pharmacistDTO.getDob(),pharmacistDTO.getGender());
    }
    public ReceptionistDTO formReceptionist(Receptionist receptionist){
        return new ReceptionistDTO(receptionist.getrId(),receptionist.getName(),receptionist.getAddress(),receptionist.getContact(),receptionist.getDob(),receptionist.getGender());
    }
    public Receptionist toReceptionist(ReceptionistDTO receptionistDTO){
        return new Receptionist(receptionistDTO.getId(),receptionistDTO.getName(),receptionistDTO.getAddress(),receptionistDTO.getContact(),receptionistDTO.getDob(),receptionistDTO.getGender());
    }
}
