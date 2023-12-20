package org.example.lab3;

import java.time.LocalDate;
import java.util.*;


public class Demo {
    public static void main(String[] args) {
        TreeSet<Patient> patient1 = new TreeSet<>();
        Patient.PatientBuilder obj1 = new Patient.PatientBuilder();
        Patient.PatientBuilder obj2 = new Patient.PatientBuilder();
        Patient.PatientBuilder obj3 = new Patient.PatientBuilder();

        obj1.setFirstName("Dima");
        obj1.setSecondName("Kent");
        obj1.setDateOfBirth(LocalDate.of(2004, 9, 5));
        obj1.setHospital(new Hospital("2", "2A", "Golovna 167"));

        obj2.setFirstName("Vasya");
        obj2.setSecondName("Pupkin");
        obj2.setDateOfBirth(LocalDate.of(2003, 2, 23));
        obj2.setHospital(new Hospital("3", "4B", "Synogoge 322"));

        obj3.setFirstName("Bogdan");
        obj3.setSecondName("Petruk");
        obj3.setDateOfBirth(LocalDate.of(2003, 6, 6));
        obj3.setHospital(new Hospital("4", "3C", "Kyivska 12"));

        patient1.add(obj1.build());
        patient1.add(obj2.build());
        patient1.add(obj3.build());
        for (Patient p : patient1) {
            System.out.println(p);
        }
        System.out.println("\n");


        List<Patient> patient2 = new ArrayList<>();
        Patient.PatientBuilder obj4 = new Patient.PatientBuilder();
        Patient.PatientBuilder obj5 = new Patient.PatientBuilder();
        Patient.PatientBuilder obj6 = new Patient.PatientBuilder();

        obj4.setFirstName("Kostya");
        obj4.setSecondName("D");
        obj4.setDateOfBirth(LocalDate.of(2002, 9, 5));
        obj4.setHospital(new Hospital("6", "5B", "Golovna 167"));

        obj5.setFirstName("Misha");
        obj5.setSecondName("B");
        obj5.setDateOfBirth(LocalDate.of(2003, 3, 2));
        obj5.setHospital(new Hospital("8", "10A", "Synogoge 322"));

        obj6.setFirstName("Grigoriy");
        obj6.setSecondName("A");
        obj6.setDateOfBirth(LocalDate.of(2007, 5, 23));
        obj6.setHospital(new Hospital("9", "6C", "Kyivska 12"));

        patient2.add(obj4.build());
        patient2.add(obj5.build());
        patient2.add(obj6.build());
        Comparator surnameComparator = new PatientSurnameComparator();
        Collections.sort(patient2, surnameComparator);
        for (Patient p : patient2) {
            System.out.println(p);
        }
        System.out.println("\n");

        List<Patient> patient3 = new ArrayList<>();
        Patient.PatientBuilder obj7 = new Patient.PatientBuilder();
        Patient.PatientBuilder obj8 = new Patient.PatientBuilder();
        Patient.PatientBuilder obj9 = new Patient.PatientBuilder();

        obj7.setFirstName("Max");
        obj7.setSecondName("Proskurnyak");
        obj7.setDateOfBirth(LocalDate.of(2004, 9, 5));
        obj7.setHospital(new Hospital("3", "5B", "Golovna 167"));

        obj8.setFirstName("Max");
        obj8.setSecondName("Martin");
        obj8.setDateOfBirth(LocalDate.of(2003, 9, 25));
        obj8.setHospital(new Hospital("5", "10A", "Synogoge 322"));

        obj9.setFirstName("Herald");
        obj9.setSecondName("Proskurnyak");
        obj9.setDateOfBirth(LocalDate.of(2001, 7, 20));
        obj9.setHospital(new Hospital("4", "7C", "Kyivska 12"));

        patient3.add(obj7.build());
        patient3.add(obj8.build());
        patient3.add(obj9.build());
        patient3.stream().sorted(Comparator.comparingInt(Patient::getDaysFromBirth)).forEach(System.out::println);

        System.out.println("\n");

        List<Patient> patient4 = new ArrayList<>();
        Patient.PatientBuilder obj10 = new Patient.PatientBuilder();
        Patient.PatientBuilder obj11 = new Patient.PatientBuilder();
        Patient.PatientBuilder obj12 = new Patient.PatientBuilder();

        obj10.setFirstName("a");
        obj10.setSecondName("z");
        obj10.setDateOfBirth(LocalDate.of(2004, 10, 5));
        obj10.setHospital(new Hospital("3", "5B", "Golovna 167"));

        obj11.setFirstName("a");
        obj11.setSecondName("b");
        obj11.setDateOfBirth(LocalDate.of(2004, 11, 5));
        obj11.setHospital(new Hospital("5", "10A", "Synogoge 322"));

        obj12.setFirstName("a");
        obj12.setSecondName("c");
        obj12.setDateOfBirth(LocalDate.of(2004, 9, 5));
        obj12.setHospital(new Hospital("4", "7C", "Kyivska 12"));

        patient4.add(obj10.build());
        patient4.add(obj11.build());
        patient4.add(obj12.build());
        Comparator<Patient> sortByAge = Comparator.comparing(Patient::getDateOfBirth);
        Comparator<Patient> sortByFirstName = Comparator.comparing(Patient::getFirstName);
        Comparator<Patient> sortByLastName = Comparator.comparing(Patient::getSecondName);
        List<Comparator<Patient>> comparators = new ArrayList<>();
        comparators.add(sortByAge);
        comparators.add(sortByFirstName);
        comparators.add(sortByLastName);
        patient4.sort(new PatientComparator(comparators));
        for (Patient p : patient4) {
            System.out.println(p);
        }

        System.out.println("\ntest");
        List<Patient> test = new ArrayList<>();
        test.addAll(patient1);
        test.addAll(patient2);
        test.addAll(patient3);
        test.addAll(patient4);
        PatientServiceWithStream ps = new PatientServiceWithStream(test);
        System.out.println("Full list");
        for (Patient p : test) {
            System.out.println(p);
        }

        System.out.println("\ngetPatientsByNumberOfRoom:");
        for (Patient p : ps.getPatientByNumberOfRoom("23D")) {
            System.out.println(p);
        }

        System.out.println("\ngetPatientsByFirstName");
        for (Patient p : ps.getPatientByFirstName("Max")) {
            System.out.println(p);
        }

        System.out.println("\ngetPatientsBySecondName");
        for (Patient p : ps.getPatientBySecondName("Proskurnyak")) {
            System.out.println(p);
        }

        System.out.println("\ngetPatientsByHospital");
        for (Patient p : ps.getPatientByHospital("3")) {
            System.out.println(p);
        }

        PatientServiceWithStream ps1 = new PatientServiceWithStream(patient4);
        List<Patient> sortedByDateOfBirth = ps1.sortByDateOfBirth(LocalDate.of(2000, 1, 1));

        System.out.println("\nSorted By Date of Birth:");
        for (Patient p : sortedByDateOfBirth) {
            System.out.println(p);
        }
    }
}
