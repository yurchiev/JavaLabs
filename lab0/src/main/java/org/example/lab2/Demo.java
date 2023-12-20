package org.example.lab2;

import org.example.lab2.entities.*;
import org.example.lab2.serialize.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws IOException {
        Patient.PatientBuilder patient1 = new Patient.PatientBuilder();
        patient1.setFirstName("Sasha");
        patient1.setSecondName("Shapovalov");
        patient1.setDateOfBirth(LocalDate.of(2004,5,2));
        patient1.setHospital(new Hospital(2, "22A", "Golovna 167"));


        Patient.PatientBuilder patient2 = new Patient.PatientBuilder();
        Hospital hospital2 = new Hospital.HospitalBuilder()
                .setNumberOfHospital(23)
                .setRoomNumber("20B")
                .setLocation("Prospect 167").build();

        patient2.setFirstName("Vasya");
        patient2.setSecondName("Pupkin");
        patient2.setDateOfBirth(LocalDate.of(2006,3,15));
        patient2.setHospital(hospital2);

        List<Patient> myList = new ArrayList<>();
        myList.add(patient1.build());
        myList.add(patient2.build());

        SerializeToJSON serializerJSON = new SerializeToJSON();
        SerializeToXml serializerToXml = new SerializeToXml();
        SerializeToTxt serializerToTxt = new SerializeToTxt();


        System.out.println("JSON:");
        serializerJSON.writeToFile(myList,"test123.json");
        System.out.println(serializerJSON.readFromFile("test123.json"));

        System.out.println("XML:");
        serializerToXml.writeToFile(myList,"test1234.xml");
        System.out.println(serializerToXml.readFromFile("test1234.xml"));

        System.out.println("TXT:");
        serializerToTxt.writeToFile(myList,"test1235.txt");
        System.out.println(serializerToTxt.readFromFile("test1235.txt"));
    }
}