package org.example.lab3;

import java.time.LocalDate;
import java.util.List;

public interface PatientServiceInterface {
    List<Patient> getPatient();
    void setPatient(List<Patient> patientList);

    List<Patient> getPatientByFirstName(String firstName);
    List<Patient> getPatientBySecondName(String secondName);
    List<Patient> getPatientByHospital(String numberOfHospital);
    List<Patient> getPatientByNumberOfRoom(String numberOfRoom);
    List<Patient> sortByDateOfBirth(LocalDate dateOfBirth);
}