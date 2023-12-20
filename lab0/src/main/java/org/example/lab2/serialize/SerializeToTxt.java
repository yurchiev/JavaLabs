package org.example.lab2.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.example.lab2.entities.Hospital;
import org.example.lab2.entities.Patient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SerializeToTxt implements Serialize<Patient> {

    private final ObjectMapper mapper;

    public SerializeToTxt() {
        mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        mapper.registerModule(new JavaTimeModule());

        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new ToStringSerializer());
        module.addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE);
        mapper.registerModule(module);
    }

    @Override
    public void writeToFile(List<Patient> patients, String fileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        for (Patient patient : patients) {
            String patientString = String.format(
                    "Name: %s %s\nDate of Birth: %s\nHospital: %s, Room: %s\nLocation: %s\n",
                    patient.getFirstName(), patient.getSecondName(), patient.getDateOfBirth(),
                    patient.getHospital().getNumberOfHospital(), patient.getHospital().getNumberOfRoom(), patient.getHospital().getLocation()
            );
            stringBuilder.append(patientString).append("\n");
        }

        Files.write(Paths.get(fileName), stringBuilder.toString().getBytes());
    }

    @Override
    public List<Patient> readFromFile(String fileName) throws IOException {
        List<Patient> patients = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get(fileName));

        Patient patient = null;
        for (String line : lines) {
            if (line.startsWith("Name: ")) {
                if (patient != null) {
                    patients.add(patient);
                }
                patient = new Patient();
                patient.setHospital(new Hospital());
                String[] nameParts = line.replace("Name: ", "").split(" ");
                patient.setFirstName(nameParts[0]);
                patient.setSecondName(nameParts[1]);
            } else if (line.startsWith("Date of Birth: ")) {
                String[] dateOfBirthParts = line.replace("Date of Birth: ", "").split("-");
                int year = Integer.parseInt(dateOfBirthParts[0]);
                int month = Integer.parseInt(dateOfBirthParts[1]);
                int day = Integer.parseInt(dateOfBirthParts[2]);
                patient.setDateOfBirth(LocalDate.of(year, month, day));
            } else if (line.startsWith("Hospital: ")) {
                String hospitalLine = line.replace("Hospital: ", "");
                String[] hospitalParts = hospitalLine.split(", Room: ");
                int hospitalNumber = Integer.parseInt(hospitalParts[0]);
                patient.getHospital().setHospitalNumber(hospitalNumber);

                if (hospitalParts.length > 1) {
                    String roomNumber = hospitalParts[1];
                    patient.getHospital().setNumberOfRoom(roomNumber);
                }
            } else if (line.startsWith("Room: ")) {
                String patientRoom = line.replace("Room: ", "");
                patient.getHospital().setNumberOfRoom(patientRoom);
            } else if (line.startsWith("Location: ")) {
                String location = line.replace("Location: ", "");
                patient.getHospital().setLocation(location);
            }
        }

        if (patient != null) {
            patients.add(patient);
        }

        return patients;
    }

}