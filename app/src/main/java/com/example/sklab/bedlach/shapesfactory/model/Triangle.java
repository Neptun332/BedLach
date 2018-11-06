package com.example.sklab.bedlach.shapesfactory.model;

import com.example.sklab.bedlach.shapesfactory.Shape;

public class Triangle extends Shape {



    private static double sumOfArea = 0;
    private static double sumOfParameter = 0;
    private static int TriangleCount = 0;

    public Triangle(double parameter) {
        super(parameter);
        this.sumOfParameter+= parameter;
        TriangleCount++;
    }

    @Override
    public void computeArea() {
        setArea((Math.pow(getParameter(),2) * Math.sqrt(2)) / 3);
        this.sumOfArea += this.getArea();
    }

    public static double getSumOfArea() {
        return sumOfArea;
    }

    public static double getSumOfParameter() {
        return sumOfParameter;
    }

    public static int getTriangleCount() {
        return TriangleCount;
    }
}
