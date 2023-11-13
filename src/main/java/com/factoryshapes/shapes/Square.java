package com.factoryshapes.shapes;

public class Square implements Shapes {

    protected double size;

    public Square(double size) {
        this.size = size;
    }

    @Override
    public double calculateArea() {
        return size * size;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * size;
    }
}