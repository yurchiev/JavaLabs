package org.example.lab4;

import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        Patient.PatientBuilder obj1 = new Patient.PatientBuilder();

        Hospital r = new Hospital.HospitalBuilder()
                .setNumberOfHospital("5")
                .setRoomNumber("5A")
                .setLocation("Golovna 167").build();

        Patient p = new Patient.PatientBuilder()
        .setFirstName("Dima")
        .setSecondName("Gomeha")
        .setDateOfBirth(LocalDate.of(2004,9,5))
        .setHospital(r).build();

        obj1.setFirstName("Victor");
        obj1.setSecondName("Petrovich");
        obj1.setDateOfBirth(LocalDate.of(2003,2,23));
        obj1.setHospital(new Hospital("5","3C","Synogoge 322"));

        System.out.println(obj1.build());
        System.out.println(p);
    }
}