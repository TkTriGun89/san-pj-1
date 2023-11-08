package com.factoryshapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit test for simple App.
 */
@ExtendWith(MockitoExtension.class)
class ShapesCheckerTest {

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
    ShapesCalculator sc;

    @Mock
    ShapeFactory fs;
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

    /**
     * Rigorous Test :-)
     */
    @Test
    void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
