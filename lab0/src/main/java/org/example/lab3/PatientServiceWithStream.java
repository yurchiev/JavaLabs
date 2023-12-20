package org.example.lab3;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PatientServiceWithStream implements PatientServiceInterface {
    public List<Patient> patientList;

    public PatientServiceWithStream(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public List<Patient> getPatient() {
        return patientList;
    }
    public void setPatient(List<Patient> patientList) {
        this.patientList = patientList;
    }


    public List<Patient> getPatientByFirstName(String firstName)
    {
        return getPatient().stream().filter(getFirstName(firstName)).collect(Collectors.toList());
    }
    public List<Patient> getPatientBySecondName(String secondName)
    {
        return getPatient().stream().filter(getSecondName(secondName)).collect(Collectors.toList());
    }

    @Override
    public List<Patient> getPatientByHospital(String numberOfHospital) {
        return getPatient().stream().filter(getByHospital(numberOfHospital)).collect(Collectors.toList());
    }

    @Override
    public List<Patient> getPatientByNumberOfRoom(String numberOfRoom) {
        return getPatient().stream().filter(getByNumberOfRoom(String.valueOf(numberOfRoom))).collect(Collectors.toList());
    }

    @Override
    public List<Patient> sortByDateOfBirth(LocalDate dateOfBirth) {
        return getPatient().stream()
                .sorted(Comparator.comparing(Patient::getDateOfBirth))
                .collect(Collectors.toList());
    }

    private Predicate<Patient> getFirstName (String firstName)
    {
        return patient -> firstName.equals(patient.getFirstName());
    }

    private Predicate<Patient> getSecondName (String secondName)
    {
        return patient -> secondName.equals(patient.getSecondName());
    }

    private Predicate<Patient> getByHospital (String numberOfHospital)
    {
        return patient -> numberOfHospital.equals(patient.getHospital().getNumberOfHospital());
    }

    private Predicate<Patient> getByNumberOfRoom (String numberOfHospital)
    {
        return patient -> numberOfHospital.equals(patient.getHospital().getNumberOfRoom());
    }
}
