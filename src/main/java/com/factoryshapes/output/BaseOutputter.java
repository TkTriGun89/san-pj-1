package com.factoryshapes.output;

import com.factoryshapes.shapes.Shapes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class BaseOutputter implements IOutputter {
    @Override
    public abstract void writeOutput(List<Shapes> outputList, String outputFile) throws IOException;
}