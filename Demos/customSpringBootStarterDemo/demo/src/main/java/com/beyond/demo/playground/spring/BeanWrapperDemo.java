package com.beyond.demo.playground.spring;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyEditorSupport;
import java.util.StringTokenizer;

public class BeanWrapperDemo {
    public static void main(String[] args) {
        Person person = new Person();
        BeanWrapper beanWrapper = new BeanWrapperImpl(person);
        beanWrapper.setPropertyValue("name","myname");
        beanWrapper.setPropertyValue("age","123");
        beanWrapper.registerCustomEditor(Person.class,new PersonPropertyEditor());
        beanWrapper.setPropertyValue("father","fathername,343");
        System.out.println(person);
        Object propertyValue = beanWrapper.getPropertyValue("father");
        System.out.println(propertyValue);

    }
}

class PersonPropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        return getValue().toString();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        StringTokenizer stringTokenizer = new StringTokenizer(text,",");
        String name = stringTokenizer.nextToken();
        String age = stringTokenizer.nextToken();
        Person person = new Person();
        person.setName(name);
        person.setAge(Integer.valueOf(age));
        setValue(person);
    }

}
