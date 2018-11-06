package com.example.sklab.bedlach.shapesfactory;

public abstract class Shape {


    private double area;
    private double parameter;


    public Shape(double parameter) {
        this.parameter = parameter;
    }

    public abstract void computeArea();


    public double getArea() {
        return area;
    }

    public double getParameter() {
        return parameter;
    }

    public void setArea(double area) {
        this.area = area;
    }


}
