package com.example.sklab.bedlach.comparators;

import com.example.sklab.bedlach.shapesfactory.Shape;

import java.util.Comparator;

public class FigureParamDscComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape, Shape t1) {
        double figureArea1 = shape.getParameter();
        double figureArea2 = t1.getParameter();

        if(figureArea2 < figureArea1){
            return -1;
        }

        if(figureArea2 > figureArea1){
            return 1;
        }

        return 0;
    }
}
