package com.factoryshapes.output;

import com.factoryshapes.shapes.Shapes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileOutputter extends BaseOutputter {
    @Override
    public void writeOutput(List<Shapes> outputList, String outputFile) throws IOException {
        BufferedWriter br = null;
        FileWriter fw = null;
        String shapeTypeOut = "Shape Type : ";
        String areaOut = ", Area : ";
        String perimeterOut = ", Perimeter : ";
        try {
            fw = new FileWriter(outputFile);
            br = new BufferedWriter(fw);
            if (!outputList.isEmpty()) {
                for (Shapes shape : outputList) {
                    br.write(shapeTypeOut + shape.getShapesName() + areaOut + shape.calculateArea() + perimeterOut
                            + shape.calculatePerimeter() + System.lineSeparator());
                    br.newLine();
                }
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (br != null) {
                br.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
    }
}
