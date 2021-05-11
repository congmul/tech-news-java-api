package com.technews.testModel;

import java.util.Objects;

public class Demo {
    // Constructor : there is no type before Demo and after public in the constructor definition.
    public Demo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // @Override annotation means that the method is coming to the class from a superclass (or parent class)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demo demo = (Demo) o;
        return age == demo.age && Objects.equals(name, demo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // Private variables can only be accessed from within the same class where they were created and initialized
    // However, Getters and setters access private variables using public get() and set() methods.
    private String name;
    private int age;

}
