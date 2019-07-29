package com.beyond.demo.playground.singleton;

public class Material {
    private String name;

    public Material() {
        System.out.println("material is making");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
