package org.example.lab2.serialize;

import java.io.IOException;
import java.util.List;

public interface Serialize<T>
{
    void writeToFile(List<T> objs, String fileName) throws IOException;
    List<T> readFromFile(String fileName) throws IOException, ClassNotFoundException;
}
