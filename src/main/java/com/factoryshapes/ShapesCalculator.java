package com.factoryshapes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShapesCalculator {

    protected IShapeFactory fsi;

    public ShapesCalculator(IShapeFactory fsi) {
        this.fsi = fsi;
    }

    // Reads the input file
    public void processShapesAndDimensions(String inputFile, String outputFile) {
        Scanner getInputFile = null;
        File inPut = new File(inputFile);
        try {
            getInputFile = new Scanner(inPut);
            List<String> outputList = new ArrayList<>();
            while (getInputFile.hasNextLine()) {
                String[] values = getInputFile.nextLine().split(",");
                String shape = values[0].trim();
                long dimension1 = Long.parseLong(values[1].trim());
                long dimension2 = Long.parseLong(values[2].trim());
                outputList.add(calculateAreaAndPerimeter(shape, dimension1, dimension2));
            }
            writeOutput(outputList, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (getInputFile != null) {
                getInputFile.close();
            }
        }
    }

    // Calculates the Surface Area and Perimeter
    public String calculateAreaAndPerimeter(String shapeType, long dimension1, long dimension2)
            throws IOException {
        String type = "";
        try {
            if (shapeType != null) {
                type = shapeType.toUpperCase();
            }
            Shapes r = fsi.createShape(type, dimension1, dimension2);
            String output = "Shape Type : " + type + ", Area : " + r.calculateArea() + ", Perimeter : "
                    + r.calculatePerimeter();
            return output;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Writes the calculated values to an output file
    public void writeOutput(List<String> outputList, String outputFile) throws IOException {
        BufferedWriter br = null;
        FileWriter fw = new FileWriter(outputFile);
        try {
            br = new BufferedWriter(fw);
            for (String op : outputList) {
                br.write(op + System.lineSeparator());
                br.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}