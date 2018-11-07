package com.example.sklab.bedlach.comparators;

import com.example.sklab.bedlach.shapesfactory.Shape;

import java.util.Comparator;

public class FigureNameDscComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape, Shape t1) {
        return t1.getClass().toString().compareTo(shape.getClass().toString());
    }
}
