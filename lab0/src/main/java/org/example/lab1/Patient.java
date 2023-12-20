package org.example.lab1;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Patient {
    public String firstName;
    public String secondName;
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate dateOfBirth;
    @JsonUnwrapped
    public Hospital hospital;

    public Patient() {}

    public Patient(String firstName, String secondName, LocalDate dateOfBirth, Hospital hospital) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.hospital = hospital;
    }
    public Patient(Patient.PatientBuilder pb) {
        this.firstName = pb.firstName;
        this.secondName = pb.secondName;
        this.dateOfBirth = pb.dateOfBirth;
        this.hospital = pb.hospital;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(dateOfBirth, patient.dateOfBirth) &&
                Objects.equals(firstName, patient.firstName) &&
                Objects.equals(secondName, patient.secondName) &&
                Objects.equals(hospital, patient.hospital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, dateOfBirth, hospital);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", hospital=" + hospital +
                '}';
    }

    public static class PatientBuilder
    {
        public String firstName;
        public String secondName;
        public LocalDate dateOfBirth;
        public Hospital hospital;

        public Patient.PatientBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Patient.PatientBuilder setSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public Patient.PatientBuilder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Patient.PatientBuilder setHospital(Hospital hospital) {
            this.hospital = hospital;
            return this;
        }

        public Patient build() {
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Patient.PatientBuilder>> constraintViolations = validator.validate(this);
            StringBuilder exceptions = new StringBuilder("\n");
            for(ConstraintViolation constraintViolation : constraintViolations) {
                String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                exceptions.append(fieldName).append(" ").append(constraintViolation.getMessage()).append("\n");
            }
            if(constraintViolations.size() > 0)throw new IllegalArgumentException(String.valueOf(exceptions));
            return new Patient(this);
        }
    }
}
