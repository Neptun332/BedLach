package com.example.sklab.bedlach;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.sklab.bedlach.shapesfactory.Shape;
import com.example.sklab.bedlach.shapesfactory.model.Circle;
import com.example.sklab.bedlach.shapesfactory.model.Square;
import com.example.sklab.bedlach.shapesfactory.model.Triangle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class program {



    private List<Shape> shapes = new ArrayList<Shape>();
    private  RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    private int min;
    private  int max;

    private int amountToGenerate;


    private DecimalFormat decimalFormat = new DecimalFormat("#.###");

    public void program(int amountToGenerate){

        this.amountToGenerate =  amountToGenerate;
    }
    public void GenerateFigures(){


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

    public List<Shape> getShapes() {
        return shapes;
    }

    public RandomNumberGenerator getRandomNumberGenerator() {
        return randomNumberGenerator;
    }

    public int getAmountToGenerate() {
        return amountToGenerate;
    }
    public DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }
    public void setAmountToGenerate(int amountToGenerate) {
        this.amountToGenerate = amountToGenerate;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }


}
