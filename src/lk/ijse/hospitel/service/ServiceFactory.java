package lk.ijse.hospitel.service;

import lk.ijse.hospitel.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){

    }
    public static ServiceFactory getServiceFactory(){
       return  (null== serviceFactory) ? serviceFactory = new ServiceFactory(): serviceFactory;
    }

    public enum ServiceTypes {
        APPOINTMENT,DOCTOR,MEDICINE,NURSE,PATIENT,PAYMENT,PHARMACIST,PLACEORDER,PLACERECEIPT,RECEIPT,RECEPTIONIST
    }

    public SuperService getService(ServiceTypes types){
        switch (types){
            case DOCTOR :
                return new DoctorServiceImpl();
            case APPOINTMENT:
                return new AppointmentServiceImpl();
            case MEDICINE:
                return new MedicineServiceImpl();
            case NURSE:
                return new NurseServiceImpl();
            case PATIENT:
                return new PatientServiceImpl();
            case PAYMENT:
                return new PaymentServiceImpl();
            case PHARMACIST:
                return new PharmacistServiceImpl();
            case PLACEORDER:
                return new PlaceOrderServiceImpl();
            case PLACERECEIPT:
                return new PlaceReceiptServiceImpl();
            case RECEPTIONIST:
                return new ReceptionistServiceImpl();
            default:
                return null;
        }
    }
}
