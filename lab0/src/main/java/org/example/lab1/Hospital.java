package org.example.lab1;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Set;

public class Hospital {
    public int numberOfHospital;
    public String numberOfRoom;
    public String location;

    public Hospital(){}

    public Hospital(int numberOfHospital, String numberOfRoom, String location) {
        this.numberOfHospital = numberOfHospital;
        this.numberOfRoom = numberOfRoom;
        this.location = location;
    }
    public Hospital(Hospital.HospitalBuilder ab) {
        this.numberOfHospital = ab.numberOfHospital;
        this.numberOfRoom = ab.numberOfRoom;
        this.location = ab.location;
    }

    public int getHospitalNumber() {
        return numberOfHospital;
    }

    public void setHospitalNumber(int numberOfHospital) {
        this.numberOfHospital = numberOfHospital;
    }

    public String getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(String numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital)) return false;
        Hospital hospital = (Hospital) o;
        return Objects.equals(numberOfHospital, hospital.numberOfHospital) && Objects.equals(numberOfRoom, hospital.numberOfRoom) && Objects.equals(location, hospital.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfHospital, numberOfRoom, location);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "Hospital number='" + numberOfHospital + '\'' +
                ", room number='" + numberOfRoom + '\'' +
                ", Location='" + location + '\'' +
                '}';
    }

    public static class HospitalBuilder
    {
        public int numberOfHospital;
        public String numberOfRoom;
        public String location;

        public Hospital.HospitalBuilder setHospitalNumber(int numberOfHospital) {
            this.numberOfHospital = numberOfHospital;
            return this;
        }
        public Hospital.HospitalBuilder setRoomNumber(String numberOfRoom) {
            this.numberOfRoom = numberOfRoom;
            return this;
        }
        public Hospital.HospitalBuilder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Hospital build() {
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<Hospital.HospitalBuilder>> constraintViolations = validator.validate(this);
            StringBuilder exceptions = new StringBuilder("\n");
            for(ConstraintViolation constraintViolation : constraintViolations) {
                String fieldName = constraintViolation.getPropertyPath().toString().toUpperCase();
                exceptions.append(fieldName).append(" ").append(constraintViolation.getMessage()).append("\n");
            }
            if(constraintViolations.size() > 0)throw new IllegalArgumentException(String.valueOf(exceptions));
            return new Hospital(this);
        }
    }
}
