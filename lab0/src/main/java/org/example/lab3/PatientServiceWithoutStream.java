package org.example.lab3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PatientServiceWithoutStream implements PatientServiceInterface {
    private List<Patient> patientList;

    public PatientServiceWithoutStream(List<Patient> myList) {
        this.patientList = myList;
    }

    public List<Patient> getPatient() {
        return patientList;
    }

    public void setPatient(List<Patient> myList) {
        this.patientList = myList;
    }

    public List<Patient> getPatientByFirstName(String firstName) {
        List<Patient> result = new ArrayList<>();
        for (Patient patient : patientList) {
            if (firstName.equals(patient.getFirstName())) {
                result.add(patient);
            }
        }
        return result;
    }

    public List<Patient> getPatientBySecondName(String secondName) {
        List<Patient> result = new ArrayList<>();
        for (Patient patient : patientList) {
            if (secondName.equals(patient.getSecondName())) {
                result.add(patient);
            }
        }
        return result;
    }

    @Override
    public List<Patient> getPatientByHospital(String numberOfHospital) {
        List<Patient> result = new ArrayList<>();
        for (Patient patient : patientList) {
            if (numberOfHospital.equals(patient.getHospital().getNumberOfHospital())) {
                result.add(patient);
            }
        }
        return result;
    }

    @Override
    public List<Patient> getPatientByNumberOfRoom(String numberOfRoom) {
        List<Patient> result = new ArrayList<>();
        for (Patient patient : patientList) {
            if (numberOfRoom.equals(patient.getHospital().getNumberOfRoom())) {
                result.add(patient);
            }
        }
        return result;
    }

    @Override
    public List<Patient> sortByDateOfBirth(LocalDate dateOfBirth) {
        List<Patient> result = getPatient();
        Collections.sort(result, Comparator.comparing(Patient::getDateOfBirth));
        return result;
    }
}
