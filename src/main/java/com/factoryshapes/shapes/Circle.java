package com.factoryshapes.shapes;

public class Circle implements Shapes {
    protected double rad;
    public Circle(double rad) {
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

    @Override
    public String getShapesName() {
        return "CIRCLE";
    }
}