package org.example.lab2.serialize;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.lab2.entities.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SerializeToXml implements Serialize<Patient> {
    @Override
    public void writeToFile(List<Patient> objs, String fileName) throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(new File(fileName),objs);
    }

    @Override
    public List<Patient> readFromFile(String fileName) throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(new File(fileName), new TypeReference<List<Patient>>(){});
    }
}
