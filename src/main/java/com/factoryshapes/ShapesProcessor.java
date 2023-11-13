package com.factoryshapes;

import com.factoryshapes.dao.InputData;
import com.factoryshapes.output.IOutputter;
import com.factoryshapes.shapes.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ShapesProcessor {
    protected IShapeFactory shapeFactory;
    public ShapesProcessor(IShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    protected IOutputter output;
    public ShapesProcessor(IOutputter output) {
        this.output = output;
    }

    //1. Reads the Input
    public Scanner readInput(String input) {
        Scanner getInput = null;
        File inPut = new File(input);
        try {
            getInput = new Scanner(inPut);
            return getInput;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    //1a. Extracts the Input Data
    public List<InputData> extractInput(Scanner getInput) {
        InputData idata = null;
        List<InputData> inputValuesList = new ArrayList<InputData>();
        try {
            while (getInput.hasNextLine()) {
                String[] values = getInput.nextLine().split(",");
                idata = new InputData();
                idata.setShapeType(values[0].trim());
                idata.setDimensionOne(Long.parseLong(values[1].trim()));
                idata.setDimensionTwo(Long.parseLong(values[2].trim()));
                inputValuesList.add(idata);
            }
            return inputValuesList;
        }catch (Exception e){
            return inputValuesList;
        } finally {
            if (getInput != null) {
                getInput.close();
            }
        }
    }

    //2. Builds the output
    public List<Shapes> buildOutPut(List<InputData> extractedInputVal) {
        String type = "";
        List<Shapes> outputList = new ArrayList<>();
        try {
            if(!extractedInputVal.isEmpty()) {
                for (InputData inputVals : extractedInputVal) {
                    if (inputVals.getShapeType() != null) {
                        type = inputVals.getShapeType().toUpperCase();
                    }
                    outputList.add(shapeFactory.createShape(type, inputVals.getDimensionOne(), inputVals.getDimensionTwo()));
                }
            }
            return outputList;
        } catch (Exception e) {
            return outputList;
        }
    }

    // Writes the calculated values to an output file
    public void writeOutput(List<Shapes> outputList, String outputFile) throws IOException {
            output.writeOutput(outputList,outputFile);
    }
}