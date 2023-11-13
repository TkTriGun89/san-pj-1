package com.factoryshapes;

import com.factoryshapes.shapes.Shapes;

import java.util.Comparator;

public class ShapesSizeComparator implements Comparator<Shapes> {
    @Override
    public int compare(Shapes o1, Shapes o2) {
        return Double.compare(o2.calculateArea(),o1.calculateArea());
    }
}
