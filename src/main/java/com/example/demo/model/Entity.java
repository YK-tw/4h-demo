package com.example.demo.model;

public abstract class Entity {
    private String name;

    private double mark;

    public Entity() {
    }

    public Entity(String name, double mark) {
        this.name = name;
        this.mark = mark;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
