package com.example.sklab.bedlach.comparators;

import com.example.sklab.bedlach.shapesfactory.Shape;

import java.util.Comparator;

public class FigureNameAscComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape, Shape t1) {
        return shape.getClass().toString().compareTo(t1.getClass().toString());
    }
}
