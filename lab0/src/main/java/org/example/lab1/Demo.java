package org.example.lab1;
import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        Patient.PatientBuilder patient1 = new Patient.PatientBuilder();
        patient1.setFirstName("Sasha");
        patient1.setSecondName("Shapovalov");
        patient1.setDateOfBirth(LocalDate.of(2004,5,2));
        patient1.setHospital(new Hospital(2, "53b", "Golovna 167"));


        Patient.PatientBuilder patient2 = new Patient.PatientBuilder();
        Hospital hospital1 = new Hospital.HospitalBuilder()
                .setHospitalNumber(23)
                .setRoomNumber("33H")
                .setLocation("Prospect 167").build();

        patient2.setFirstName("Vasya");
        patient2.setSecondName("Pupkin");
        patient2.setDateOfBirth(LocalDate.of(2006,3,15));
        patient2.setHospital(hospital1);

        System.out.println(patient1.build());
        System.out.println(patient2.build());
    }
}