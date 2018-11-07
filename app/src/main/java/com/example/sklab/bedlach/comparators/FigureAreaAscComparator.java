package com.example.sklab.bedlach.comparators;

import com.example.sklab.bedlach.shapesfactory.Shape;


import java.util.Comparator;

public class FigureAreaAscComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape shape, Shape t1) {
        double figureArea1 = shape.getArea();
        double figureArea2 = t1.getArea();

        if(figureArea1 < figureArea2){
            return -1;
        }

        if(figureArea1 > figureArea2){
            return 1;
        }

        return 0;
    }

        //return String.valueOf(shape.getArea()).compareTo(String.valueOf(t1.getArea()));
}

