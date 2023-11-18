package com.factoryshapes.output;

import com.factoryshapes.shapes.Shapes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConsoleOutputter extends  BaseOutputter  {
    @Override
    public void writeOutput(List<Shapes> outputList, String outputFile) throws IOException {
        String shapeTypeOut = "Shape Type : ";
        String areaOut = ", Area : ";
        String perimeterOut = ", Perimeter : ";
        try {
            if (!outputList.isEmpty()) {
                for (Shapes shape : outputList) {
                    //CONSOLE
                    System.out.println(shapeTypeOut + shape.getShapesName() + areaOut + shape.calculateArea() + perimeterOut
                            + shape.calculatePerimeter() + System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
