package com.factoryshapes.output;

import com.factoryshapes.ShapesSizeComparator;
import com.factoryshapes.shapes.Circle;
import com.factoryshapes.shapes.Rectangle;
import com.factoryshapes.shapes.Shapes;
import com.factoryshapes.shapes.Square;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class OutputterImpl implements IOutputter {
    @Override
    public void writeOutput(List<Shapes> outputList, String outputFile) throws IOException {
        Collections.sort(outputList, new ShapesSizeComparator());
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
                    if (shape instanceof Circle) {
                        //CONSOLE
                        System.out.println(shapeTypeOut + "CIRCLE" + areaOut + ((Circle) shape).calculateArea() + perimeterOut
                                + ((Circle) shape).calculatePerimeter() + System.lineSeparator());
                        //FILE
                        br.write(shapeTypeOut + "CIRCLE" + areaOut + ((Circle) shape).calculateArea() + perimeterOut
                                + ((Circle) shape).calculatePerimeter() + System.lineSeparator());
                        br.newLine();
                    }
                    if (shape instanceof Square) {
                        //CONSOLE
                        System.out.println(shapeTypeOut + "SQUARE" + areaOut + ((Square) shape).calculateArea() + perimeterOut
                                        + ((Square) shape).calculatePerimeter() + System.lineSeparator());
                        //FILE
                        br.write(shapeTypeOut + "SQUARE" + areaOut + ((Square) shape).calculateArea() + perimeterOut
                                + ((Square) shape).calculatePerimeter() + System.lineSeparator());
                        br.newLine();
                    }
                    if (shape instanceof Rectangle) {
                        //CONSOLE
                        System.out.println(shapeTypeOut + "RECTANGLE" + areaOut + ((Rectangle) shape).calculateArea() + perimeterOut
                                + ((Rectangle) shape).calculatePerimeter() + System.lineSeparator());
                        //FILE
                        br.write(shapeTypeOut + "RECTANGLE" + areaOut + ((Rectangle) shape).calculateArea() + perimeterOut
                                + ((Rectangle) shape).calculatePerimeter() + System.lineSeparator());
                        br.newLine();
                    }
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
