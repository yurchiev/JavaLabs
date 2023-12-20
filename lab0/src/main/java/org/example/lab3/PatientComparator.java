package org.example.lab3;

import java.util.Comparator;
import java.util.List;

public class PatientComparator implements Comparator<Patient> {
    private List<Comparator<Patient>> comparators;

    public PatientComparator(List<Comparator<Patient>> comparators) {
        this.comparators = comparators;
    }

    @Override
    public int compare(Patient o1, Patient o2) {
        int res;
        for(Comparator<Patient> comparator : comparators) {
            if ((res = comparator.compare(o1, o2)) != 0) {
                return res;
            }
        }
        return 0;
    }
}
