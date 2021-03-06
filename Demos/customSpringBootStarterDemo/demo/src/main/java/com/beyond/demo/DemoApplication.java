package com.beyond.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@MapperScan("com.beyond.demo.mapper")
@SpringBootApplication
@Component
@EnableSpringConfigured
@EnableJms
public class DemoApplication implements CommandLineRunner{


    @Bean
    IntegrationFlow integrationFlow(){
        return IntegrationFlows.from(Files.inboundAdapter(new File("/tmp/in")).autoCreateDirectory(true)
                .preventDuplicates(false),
                e-> e.poller(Pollers.fixedDelay(5000)))
                .transform(File.class, new GenericTransformer<File, String>() {
                    @Override
                    public String transform(File file) {
                        try(FileInputStream fileInputStream = new FileInputStream(file);
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)){
                            byte[] bytes = new byte[1024];
                            String s = "";
                            int len;
                            while ((len = bufferedInputStream.read(bytes))>0){
                                s+=new String(bytes,0,len);
                            }
                            s+="auto generated";
                            return s;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .handle(Files.outboundAdapter("'/tmp/out'").autoCreateDirectory(true))
                .get();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }


}
