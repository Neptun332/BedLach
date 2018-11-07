package com.example.sklab.bedlach.shapesfactory.model;

import com.example.sklab.bedlach.shapesfactory.Shape;

public class Square extends Shape {



    private static double sumOfArea = 0;
    private static double sumOfParameter = 0;
    private static int SquareCount = 0;

    public Square(double parameter) {
        super(parameter);
        this.sumOfParameter+= parameter;
        SquareCount++;
    }

    @Override
    public void computeArea() {
        setArea( (getParameter()*getParameter()) / 2);
        this.sumOfArea += this.getArea();
    }

    @Override
    public String getName() {
        return "Square";
    }

    public static double getSumOfArea() {
        return sumOfArea;
    }

    public static double getSumOfParameter() {
        return sumOfParameter;
    }

    public static int getSquareCount() {
        return SquareCount;
    }

    public static void setSumOfArea(double sumOfArea) {
        Square.sumOfArea = sumOfArea;
    }

    public static void setSumOfParameter(double sumOfParameter) {
        Square.sumOfParameter = sumOfParameter;
    }

    public static void setSquareCount(int squareCount) {
        SquareCount = squareCount;
    }
}
