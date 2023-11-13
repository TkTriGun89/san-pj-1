package com.factoryshapes.output;

import com.factoryshapes.shapes.Shapes;

import java.io.IOException;
import java.util.List;

public interface IOutputter {
    public void writeOutput(List<Shapes> outputList, String outputFile) throws IOException;
}
