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

    @Override
    public String getName() {
        return "Circle";
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

    public static void setSumOfArea(double sumOfArea) {
        Circle.sumOfArea = sumOfArea;
    }

    public static void setSumOfParameter(double sumOfParameter) {
        Circle.sumOfParameter = sumOfParameter;
    }

    public static void setCircleCount(int circleCount) {
        CircleCount = circleCount;
    }
}

