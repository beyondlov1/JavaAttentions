package com.beyond;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.jackrabbit.webdav.client.methods.HttpMkcol;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        //init credentials
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("username", "appPassword");
        provider.setCredentials(AuthScope.ANY,credentials);

        //init HttpClient
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCredentialsProvider(provider);
        CloseableHttpClient client = builder.build();

        //init method
        HttpMkcol mkcol = new HttpMkcol("https://dav.jianguoyun.com/dav/MyFiles/image");
        CloseableHttpResponse response = client.execute(mkcol);

        HttpPut put = new HttpPut("https://dav.jianguoyun.com/dav/MyFiles/image/test.jpg");
        put.setEntity(new FileEntity(new File("C:\\Users\\Administrator\\Desktop\\IMG_20170619_094701.jpg")));

        CloseableHttpResponse response2 = client.execute(put);
        System.out.println(response.toString());
        System.out.println(response2.toString());
        System.out.println("success");
    }
}
