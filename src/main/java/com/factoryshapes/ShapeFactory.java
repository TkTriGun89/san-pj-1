package com.factoryshapes;

public class ShapeFactory implements IShapeFactory {

    public Shapes createShape(String shapeType, long shapeSize1, long shapeSize2) {
        if (shapeType == null) {
            return null;
        }
        String type = shapeType.toUpperCase();
        switch (type) {
            case "CIRCLE":
                return new Circle(shapeSize1);
            case "RECTANGLE":
                return new Rectangle(shapeSize1, shapeSize2);
            case "SQUARE":
                return new Square(shapeSize1);
            default:
                throw new IllegalArgumentException("Invalid Shapetype" + shapeType);
        }
    }
}