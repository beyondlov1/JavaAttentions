package com.beyond.demo;

import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        String source = ". dfaeg\n";
        boolean matches = source.matches("\\d+\\..*\\n?");
        System.out.println(matches);
    }
}
