package com.beyond.demo.playground.singleton;

public class Tool {

    @SingletonInject
    private Fire fire;

    private String name;

    @Qualifier(implementClass = Material.class)
    @SingletonInject
    private IMaterial material;

    @Qualifier(implementClass = Material2.class)
    @PrototypeInject
    private IMaterial material2;

    public Tool() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IMaterial getMaterial() {
        return material;
    }

    public void setMaterial(IMaterial material) {
        this.material = material;
    }

    public Fire getFire() {
        return fire;
    }

    public void setFire(Fire fire) {
        this.fire = fire;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "fire=" + fire +
                ", name='" + name + '\'' +
                ", material=" + material +
                ", material2=" + material2 +
                '}';
    }
}
