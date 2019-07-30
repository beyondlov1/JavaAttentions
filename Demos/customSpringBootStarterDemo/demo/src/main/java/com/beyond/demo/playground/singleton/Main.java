package com.beyond.demo.playground.singleton;

public class Main {
    public static void main(String[] args) {
//        BeanFactoryImpl factory = new BeanFactoryImpl();
//        Human bean = factory.getBean(Human.class);
//        bean.move();
//        System.out.println(bean);
//
//        Object bean1 = factory.getBean(Tool.class);
//        System.out.println(bean1);


        Person person = new Child();
        BeanInjectUtils.inject(person);
        Tool tool = person.getTool();
        Tool tool2 = person.getTool2();
        System.out.println(tool);
        System.out.println(tool2);

        person = new Child();
        BeanInjectUtils.inject(person);
        tool = person.getTool();
        tool2 = person.getTool2();
        System.out.println(tool);
        System.out.println(tool2);

        System.out.println(person);
    }
}
