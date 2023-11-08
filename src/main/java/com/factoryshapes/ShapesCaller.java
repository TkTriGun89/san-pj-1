package com.factoryshapes;

class ShapesCaller {
    public static void main(String[] args) {
        String projectDir = System.getProperty("user.dir");
        System.out.println("projectDir"+projectDir);
        String inputFile = projectDir + "/resources/inputValues.txt";
        String outputFile = projectDir + "/resources/outputValues.txt";
        ShapesCalculator sc = new ShapesCalculator(new ShapeFactory());
        sc.processShapesAndDimensions(inputFile, outputFile); // separate into 3 parts Read, Calculate, Write
    }
}