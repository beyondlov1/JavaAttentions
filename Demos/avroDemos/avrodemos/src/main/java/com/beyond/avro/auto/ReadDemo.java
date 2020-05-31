package com.beyond.avro.auto;

import com.beyond.avro.manual.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.File;
import java.io.IOException;

public class ReadDemo {
    public static void main(String[] args) throws IOException {
        DatumReader<User2> datumReader = new SpecificDatumReader<>(User2.class);
        DataFileReader<User2> dataFileReader = new DataFileReader<User2>(
                new File("D:\\document\\GitHub\\JavaAttentions\\Demos\\avroDemos\\avrodemos\\src\\main\\resources\\user2.obj"),
                datumReader
        );
        while (dataFileReader.hasNext()){
            User2 user = dataFileReader.next();
            System.out.println(user);
        }
    }
}
