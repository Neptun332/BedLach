package com.example.sklab.bedlach.shapesfactory;

import com.example.sklab.bedlach.utils.RandomNumberGenerator;
import com.example.sklab.bedlach.shapesfactory.Shape;
import com.example.sklab.bedlach.shapesfactory.model.Circle;
import com.example.sklab.bedlach.shapesfactory.model.Square;
import com.example.sklab.bedlach.shapesfactory.model.Triangle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShapesGenerator {

    private Shape singleShape;
    private List<Shape> shapes = new ArrayList<Shape>();
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    private double min;
    private double max;

    private int amountToGenerate;


    private final DecimalFormat decimalFormat = new DecimalFormat("#.###");

    public ShapesGenerator(int amountToGenerate, double min, double max){
        this.amountToGenerate =  amountToGenerate;
        this.min = min;
        this.max = max;
    }
    public ShapesGenerator(double min, double max){
        this.min = min;
        this.max = max;
    }

    public void generate(){
        for(int i =0; i < amountToGenerate;i++) {
            int randomIntNumber = randomNumberGenerator.randomInteger(1,3);
            double randomDoubleNumber = randomNumberGenerator.randomDouble(min,max);
            switch (randomIntNumber) {
                case 1:
                    Shape circle = new Circle(randomDoubleNumber);
                    circle.computeArea();
                    shapes.add(circle);
                    System.out.println("Circle with area of: " + decimalFormat.format(circle.getArea()) + " and diameter of " + decimalFormat.format(circle.getParameter()));
                    break;
                case 2:
                    Shape triangle = new Triangle(randomDoubleNumber);
                    triangle.computeArea();
                    shapes.add(triangle);
                    System.out.println("Triangle with area of: " + decimalFormat.format(triangle.getArea()) + " and height of " + decimalFormat.format(triangle.getParameter()));
                    break;
                case 3:
                    Shape square = new Square(randomDoubleNumber);
                    square.computeArea();
                    shapes.add(square);
                    System.out.println("Square with area of: " + decimalFormat.format(square.getArea()) + " and diagonal of " + decimalFormat.format(square.getParameter()));
                    break;
            }
        }

    }

    public void singleItemGenerate() {
        int randomIntNumber = randomNumberGenerator.randomInteger(1,3);
        double randomDoubleNumber = randomNumberGenerator.randomDouble(min,max);
        switch (randomIntNumber) {
            case 1:
                Shape circle = new Circle(randomDoubleNumber);
                circle.computeArea();
                singleShape = circle;
                System.out.println("Circle with area of: " + decimalFormat.format(circle.getArea()) + " and diameter of " + decimalFormat.format(circle.getParameter()));
                break;
            case 2:
                Shape triangle = new Triangle(randomDoubleNumber);
                triangle.computeArea();
                singleShape = triangle;
                System.out.println("Triangle with area of: " + decimalFormat.format(triangle.getArea()) + " and height of " + decimalFormat.format(triangle.getParameter()));
                break;
            case 3:
                Shape square = new Square(randomDoubleNumber);
                square.computeArea();
                singleShape = square;
                System.out.println("Square with area of: " + decimalFormat.format(square.getArea()) + " and diagonal of " + decimalFormat.format(square.getParameter()));
                break;
        }
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Shape getSingleShape() {
        return singleShape;
    }

    public List<Shape> getShapes() {
        return shapes;
    }
    public int getAmountToGenerate() {
        return amountToGenerate;
    }
    public DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }
}
