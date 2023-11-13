package com.factoryshapes.dao;

public class InputData {
    private String shapeType;
    private long dimensionOne;
    private long dimensionTwo;

    public InputData() {
    }

    public String getShapeType() {
        return shapeType;
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public long getDimensionOne() {
        return dimensionOne;
    }

    public void setDimensionOne(long dimensionOne) {
        this.dimensionOne = dimensionOne;
    }

    public long getDimensionTwo() {
        return dimensionTwo;
    }

    public void setDimensionTwo(long dimensionTwo) {
        this.dimensionTwo = dimensionTwo;
    }
}
