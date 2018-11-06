package com.example.sklab.bedlach.shapesfactory.model;

import com.example.sklab.bedlach.shapesfactory.Shape;

public class Circle extends Shape {

    private static double sumOfArea = 0;
    private static double sumOfParameter = 0;
    private static int CircleCount = 0;

    public Circle(double parameter) {
        super(parameter);
        this.sumOfParameter+= parameter;
        CircleCount++;
    }

    @Override
    public void computeArea() {
       setArea( Math.PI *getParameter()*getParameter());
       this.sumOfArea += this.getArea();
    }


    public static double getSumOfArea() {
        return sumOfArea;
    }

    public static double getSumOfParameter() {
        return sumOfParameter;
    }

    public static int getCircleCount() {
        return CircleCount;
    }

}

