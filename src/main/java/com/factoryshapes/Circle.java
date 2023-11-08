package com.factoryshapes;

public class Circle implements Shapes {
    double rad;

    Circle(double rad) {
        this.rad = rad;
    }

    @Override
    public double calculateArea() {
        return Math.PI * rad * rad;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * rad;
    }
}