package com.beyond.demo.playground.singleton;

public class Tool {
    private Fire fire;

    private String name;

    private Material material;

    public Tool(Fire fire,Material material) {
        this.material = material;
        this.fire = fire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "fire=" + fire +
                ", name='" + name + '\'' +
                ", material=" + material +
                '}';
    }
}
