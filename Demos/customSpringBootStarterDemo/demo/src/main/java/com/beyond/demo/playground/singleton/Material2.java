package com.beyond.demo.playground.singleton;

public class Material implements IMaterial{
    private String name;

    @SingletonInject
    private Person person;

    public Material() {
        System.out.println("material is making");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
