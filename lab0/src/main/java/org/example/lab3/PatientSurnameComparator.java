package org.example.lab3;

import java.util.Comparator;
public class PatientSurnameComparator implements Comparator<Patient>
{
    @Override
    public int compare(Patient o1, Patient o2) {
        return o1.getSecondName().compareTo(o2.getSecondName());
    }
}
