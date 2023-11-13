package com.factoryshapes;

import com.factoryshapes.dao.InputData;
import com.factoryshapes.output.OutputterImpl;
import com.factoryshapes.shapes.ShapeFactoryImpl;
import com.factoryshapes.shapes.Shapes;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

class ShapesMain {
    public static void main(String[] args) throws IOException {
        String projectDir = System.getProperty("user.dir");
        String input = projectDir + "/resources/inputValues.txt";
        String output = projectDir + "/resources/outputValues.txt";
        ShapesProcessor processShapes = new ShapesProcessor(new ShapeFactoryImpl());
        ShapesProcessor outputWriter = new ShapesProcessor(new OutputterImpl());

        //1. Reads the input
        Scanner inputScan = processShapes.readInput(input);
        //1a. Extracts the input data
        List<InputData> extractedValues = processShapes.extractInput(inputScan);

        //2. Builds the output
        List<Shapes> outputList = processShapes.buildOutPut(extractedValues);

        //3. Writes the output
        outputWriter.writeOutput(outputList, output);
    }
}