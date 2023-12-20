package org.example.lab4;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Set;

public class Patient implements Comparable<Patient> {
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
    public int getDaysFromBirth() {
        return (int) ChronoUnit.DAYS.between(this.dateOfBirth, LocalDate.now());
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
    @Override
    public int compareTo(Patient o) {
        int lastNameComparison = this.secondName.compareTo(o.secondName);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }
        return this.firstName.compareTo(o.firstName);
    }

    public static class PatientBuilder
    {
        @NotEmpty
        public String firstName;
        @NotEmpty
        public String secondName;
        @Past
        @NotNull
        public LocalDate dateOfBirth;
        @NotNull
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