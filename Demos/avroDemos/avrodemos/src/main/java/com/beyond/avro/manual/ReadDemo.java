package com.beyond.avro.manual;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.File;
import java.io.IOException;

public class ReadDemo {
    public static void main(String[] args) throws IOException {
        DatumReader<User> datumReader = new SpecificDatumReader<>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(
                new File("D:\\document\\GitHub\\JavaAttentions\\Demos\\avroDemos\\avrodemos\\src\\main\\resources\\user.obj"),
                datumReader
        );
        while (dataFileReader.hasNext()){
            User user = dataFileReader.next();
            System.out.println(user);
        }
    }
}
