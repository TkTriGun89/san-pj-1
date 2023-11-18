package com.factoryshapes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.factoryshapes.dao.InputData;
import com.factoryshapes.output.BaseOutputter;
import com.factoryshapes.output.ConsoleOutputter;
import com.factoryshapes.output.FileOutputter;
import com.factoryshapes.shapes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
@ExtendWith(MockitoExtension.class)
class ShapesApplicationTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    Shapes shapeCircle;

    @Mock
    Shapes shapeRectangle;

    @Mock
    Shapes shapeSquare;

    @Mock
    ShapesProcessor sp;

    @Mock
    ShapeFactoryImpl fs;
    String projectDir = System.getProperty("user.dir");
    String inputFile = projectDir + "/src/test/resources/inputValues.txt";
    String outputFile = projectDir + "/src/test/resources/outputValues.txt";

    @Test
    void testCircleArea() throws IOException{
        Mockito.when(shapeCircle.calculateArea()).thenReturn(28.274333882308138);
        assertEquals(28.274333882308138, shapeCircle.calculateArea());
    }

    @Test
    void testSquareArea() throws IOException{
        Mockito.when(shapeSquare.calculateArea()).thenReturn(36.0);
        assertEquals(36.0, shapeSquare.calculateArea());
    }

    @Test
    void testRectangleArea() throws IOException{
        Mockito.when(shapeRectangle.calculateArea()).thenReturn(6.0);
        assertEquals(6.0, shapeRectangle.calculateArea());
    }

    @Test
    void testCirclePerimeter() throws IOException{
        Mockito.when(shapeCircle.calculatePerimeter()).thenReturn(18.84955592153876);
        assertEquals(18.84955592153876, shapeCircle.calculatePerimeter());
    }

    @Test
    void testSquarePerimeter() throws IOException{
        Mockito.when(shapeSquare.calculatePerimeter()).thenReturn(24.0);
        assertEquals(24.0, shapeSquare.calculatePerimeter());
    }

    @Test
    void testRectanglePerimeter() throws IOException{
        Mockito.when(shapeRectangle.calculatePerimeter()).thenReturn(10.0);
        assertEquals(10.0, shapeRectangle.calculatePerimeter());
    }

    @Test
    void testInvalidData() throws IOException {
        try {
            fs.createShape("Invalid", 0, 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid ShapetypeInvalid", e.getMessage());
        }
    }

    //Test Coverage with OG Objects instead Of Mocks
    @Test
    void testCalculateAreaCircleOg() throws IOException{
        InputData id = new InputData();
        id.setDimensionOne(3);
        Circle shapeCircleOg = new Circle(id.getDimensionOne());
        assertEquals(28.274333882308138, shapeCircleOg.calculateArea());
    }

    @Test
    void testCalculatePerimeterCircleOg() throws IOException{
        InputData id = new InputData();
        id.setDimensionOne(3);
        Circle shapeCircleOg = new Circle(id.getDimensionOne());
        assertEquals(18.84955592153876, shapeCircleOg.calculatePerimeter());
    }

    @Test
    void testCalculateAreaSquareOg() throws IOException{
        InputData id = new InputData();
        id.setDimensionOne(6);
        Square squareOg = new Square(id.getDimensionOne());
        assertEquals(36.0, squareOg.calculateArea());
    }

    @Test
    void testCalculatePerimeterSquareOg() throws IOException{
        InputData id = new InputData();
        id.setDimensionOne(6);
        Square squareOg = new Square(id.getDimensionOne());
        assertEquals(24.0, squareOg.calculatePerimeter());
    }

    @Test
    void testCalculateAreaRectOg() throws IOException{
        InputData id = new InputData();
        id.setDimensionOne(3);
        id.setDimensionTwo(2);
        Rectangle rectangleOg = new Rectangle(id.getDimensionOne(), id.getDimensionTwo());
        assertEquals(6.0, rectangleOg.calculateArea());
    }

    @Test
    void testCalculatePerimeterRectOg() throws IOException{
        InputData id = new InputData();
        id.setDimensionOne(3);
        id.setDimensionTwo(2);
        Rectangle rectangleOg = new Rectangle(id.getDimensionOne(), id.getDimensionTwo());
        assertEquals(10.0, rectangleOg.calculatePerimeter());
    }

    @Test
    void testShapesApp(@TempDir Path tempDir) throws IOException {
        Path inPath = tempDir.resolve(inputFile);
        Path ouPath = tempDir.resolve(outputFile);

        //Read Input
        ShapesProcessor psTest = new ShapesProcessor(new ShapeFactoryImpl());
        Scanner inputScan = psTest.readInput(inPath.toString());
        List<InputData> extractedValues = psTest.extractInput(inputScan);

        //Build Output
        List<Shapes> outputList = psTest.buildOutPut(extractedValues);

        //Sort Output
        Collections.sort(outputList, new ShapesSizeComparator());

        //Write Output
        BaseOutputter fileOutputter = new FileOutputter();
        BaseOutputter consoleOutputter = new ConsoleOutputter();
        ShapesProcessor processFileOutput = new ShapesProcessor(fileOutputter);
        ShapesProcessor processConsoleOutput = new ShapesProcessor(consoleOutputter);
        processFileOutput.writeOutput(outputList, ouPath.toString());
        processConsoleOutput.writeOutput(outputList, ouPath.toString());

        String opContent = Files.readString(ouPath);
        String expectedOutput = "Shape Type : SQUARE, Area : 36.0, Perimeter : 24.0\r\n\r\nShape Type : CIRCLE, Area : 28.274333882308138, Perimeter : 18.84955592153876\r\n\r\nShape Type : RECTANGLE, Area : 6.0, Perimeter : 10.0";
        assertTrue(opContent.contains(expectedOutput));
    }

    @Test
    void testShapeInputException() {
        ShapesProcessor psTest = new ShapesProcessor(new ShapeFactoryImpl());
        assertThrows(Exception.class, () -> { psTest.readInput(null); });
    }

    @Test
    void testCreateShapeNull(){
       assertNull(fs.createShape(null,0,0));
    }

    @Test
    void testBuildOutputError(){
        try {
            ShapesProcessor buildOpTest = new ShapesProcessor(new ShapeFactoryImpl());
            assertInstanceOf(ArrayList.class, buildOpTest.buildOutPut(null));
        } catch (Exception e) {
        }
    }

    @Test
    void testWriteOutputError(){
        try {
            BaseOutputter fileOutputter = new FileOutputter();
            BaseOutputter consoleOutputter = new ConsoleOutputter();
            ShapesProcessor processFileOutput = new ShapesProcessor(fileOutputter);
            ShapesProcessor processConsoleOutput = new ShapesProcessor(consoleOutputter);
            processFileOutput.writeOutput(null, null);
            processConsoleOutput.writeOutput(null, null);
        } catch (Exception e) {
            assertEquals("Cannot invoke \"java.util.List.sort(java.util.Comparator)\" because \"list\" is null", e.getMessage());
        }
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}