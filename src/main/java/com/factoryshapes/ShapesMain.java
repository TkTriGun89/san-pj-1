package com.factoryshapes;

import com.factoryshapes.dao.InputData;
import com.factoryshapes.output.BaseOutputter;
import com.factoryshapes.output.ConsoleOutputter;
import com.factoryshapes.output.FileOutputter;
import com.factoryshapes.shapes.ShapeFactoryImpl;
import com.factoryshapes.shapes.Shapes;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class ShapesMain {
    public static void main(String[] args) throws IOException {
        String projectDir = System.getProperty("user.dir");
        String input = projectDir + "/resources/inputValues.txt";
        String output = projectDir + "/resources/outputValues.txt";
        BaseOutputter fileOutputter = new FileOutputter();
        BaseOutputter consoleOutputter = new ConsoleOutputter();
        ShapesProcessor processShapes = new ShapesProcessor(new ShapeFactoryImpl());
        ShapesProcessor processFileOutput = new ShapesProcessor(fileOutputter);
        ShapesProcessor processConsoleOutput = new ShapesProcessor(consoleOutputter);

        //1. Reads the input
        Scanner inputScan = processShapes.readInput(input);
        //1a. Extracts the input data
        List<InputData> extractedValues = processShapes.extractInput(inputScan);

        //2. Builds the output
        List<Shapes> outputList = processShapes.buildOutPut(extractedValues);

        //2a Sort output list
        Collections.sort(outputList, new ShapesSizeComparator());

        //3. Writes the file output
        processFileOutput.writeOutput(outputList, output);

        //4. Writes the console output
        processConsoleOutput.writeOutput(outputList,null);
    }
}