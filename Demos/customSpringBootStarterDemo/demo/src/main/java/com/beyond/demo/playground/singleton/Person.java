package com.beyond.demo.playground.singleton;

public class Person implements Human {
    private String name;

    @SingletonInject
    private Tool tool;

    @SingletonInject
    private Tool tool2;

    @SingletonInject
    private MyInnerClass myInnerClass;

    public Person(){
        System.out.println("no param constructor");
        BeanInjectUtils.inject(this,new Material());
    }

    public Person(Tool tool) {
        System.out.println("param constructor");
        this.tool = tool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", tool=" + tool +
                ", tool2=" + tool2 +
                ", myInnerClass=" + myInnerClass +
                '}';
    }

    @Override
    public void move() {
        System.out.println("moving");
    }

    public Tool getTool2() {
        return tool2;
    }

    public void setTool2(Tool tool2) {
        this.tool2 = tool2;
    }

    class MyInnerClass{

    }
}
