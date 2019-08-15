package com.beyond.avro.auto;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class WriteDemo {
    public static void main(String[] args) throws IOException {
        User2 user = User2.newBuilder()
                .setName("username1")
                .setAge(23)
                .setPhone("3434343")
                .build();
        DatumWriter<User2> datumWriter = new SpecificDatumWriter<>(User2.class);
        DataFileWriter<User2> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(user.getSchema(), new File("D:\\document\\GitHub\\JavaAttentions\\Demos\\avroDemos\\avrodemos\\src\\main\\resources\\user2.obj"));
        dataFileWriter.append(user);
        dataFileWriter.close();
    }
}
