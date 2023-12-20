package org.example.lab2.serialize;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.lab2.entities.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SerializeToJSON implements Serialize<Patient> {
    @Override
    public void writeToFile(List<Patient> objs, String fileName) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(new File(fileName),objs);
    }

    @Override
    public List<Patient> readFromFile(String fileName) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        List<Patient> res = mapper.readValue(new File(fileName), new TypeReference<List<Patient>>(){});
        return res;
    }
}
