package com.factoryshapes;

public class Square implements Shapes {

    double size;

    Square(double size) {
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