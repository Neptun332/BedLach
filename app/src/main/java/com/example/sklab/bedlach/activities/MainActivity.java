package com.example.sklab.bedlach.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sklab.bedlach.LinearLayoutWithClear;
import com.example.sklab.bedlach.R;
import com.example.sklab.bedlach.shapesfactory.ShapesGenerator;
import com.example.sklab.bedlach.shapesfactory.Shape;
import com.example.sklab.bedlach.shapesfactory.model.Circle;
import com.example.sklab.bedlach.shapesfactory.model.Square;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Shape> shapes;
    private LinearLayoutWithClear linearLayoutWithClear;
    private DecimalFormat decimalFormat;
    private SharedPreferences preferences;

    private static ShapesGenerator figures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        int amountToGenerate = Integer.parseInt(preferences.getString("amountToGenerate", "50"));
        int min = Integer.parseInt(preferences.getString("min", "0"));
        int max = Integer.parseInt(preferences.getString("max", "1"));
        figures = new ShapesGenerator();
        figures.setAmountToGenerate(amountToGenerate);
        figures.setMin(min);
        figures.setMin(max);

        figures.GenerateFigures();
        shapes = figures.getShapes();
        linearLayoutWithClear = findViewById(R.id.linearLayout);
        decimalFormat = figures.getDecimalFormat();
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            if (shape instanceof Circle) {
                linearLayoutWithClear.InsertCircleToLayout(shape);

            } else if (shape instanceof Square) {
               linearLayoutWithClear.InsertSquareToLayout(shape);
            } else {
                linearLayoutWithClear.InsertTriangleToLayout(shape);
            }

            listenButtons();

        }
    }

    void listenButtons(){
    final Button settingsButton = findViewById(R.id.SettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
    });
        final Button statsButton = findViewById(R.id.StatsButton);
        statsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });

        final Button sortFigureButton = findViewById(R.id.SortFigureButton);
        sortFigureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                linearLayoutWithClear.clearContent();
                shapes.sort(new Comparator<Shape>() {
                    @Override
                    public int compare(Shape figure1, Shape figure2) {
                        String figureName1 = figure1.getClass().toString();
                        String figureName2 = figure2.getClass().toString();
                        return figureName1.compareTo(figureName2);
                    }
                });
                for (int i = 0; i < shapes.size(); i++) {
                    Shape shape = shapes.get(i);
                    if (shape instanceof Circle) {
                        linearLayoutWithClear.InsertCircleToLayout(shape);

                    } else if (shape instanceof Square) {
                        linearLayoutWithClear.InsertSquareToLayout(shape);
                    } else {
                        linearLayoutWithClear.InsertTriangleToLayout(shape);
                    }

                    listenButtons();

                }

            }
        });
        final Button sortAreaButton = findViewById(R.id.SortAreaButton);
        sortAreaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                linearLayoutWithClear.clearContent();
                shapes.sort(new Comparator<Shape>() {
                    @Override
                    public int compare(Shape figure1, Shape figure2) {
                        double figureArea1 = figure1.getArea();
                        double figureArea2 = figure2.getArea();
                        if(figureArea1 == figureArea2){
                            return 0;
                        }

                        if(figureArea1 > figureArea2){
                            return 1;
                        }
                        else{
                            return -1;
                        }
                    }
                });
                for (int i = 0; i < shapes.size(); i++) {
                    Shape shape = shapes.get(i);
                    if (shape instanceof Circle) {
                        linearLayoutWithClear.InsertCircleToLayout(shape);

                    } else if (shape instanceof Square) {
                        linearLayoutWithClear.InsertSquareToLayout(shape);
                    } else {
                        linearLayoutWithClear.InsertTriangleToLayout(shape);
                    }

                    listenButtons();

                }
            }
        });
        final Button sortParameterButton = findViewById(R.id.SortParameterButton);
        sortParameterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                linearLayoutWithClear.clearContent();
                shapes.sort(new Comparator<Shape>() {
                    @Override
                    public int compare(Shape figure1, Shape figure2) {
                        double figureArea1 = figure1.getParameter();
                        double figureArea2 = figure2.getParameter();
                        if(figureArea1 == figureArea2){
                            return 0;
                        }

                        if(figureArea1 > figureArea2){
                            return 1;
                        }
                        else{
                            return -1;
                        }
                    }
                });
                for (int i = 0; i < shapes.size(); i++) {
                    Shape shape = shapes.get(i);
                    if (shape instanceof Circle) {
                        linearLayoutWithClear.InsertCircleToLayout(shape);

                    } else if (shape instanceof Square) {
                        linearLayoutWithClear.InsertSquareToLayout(shape);
                    } else {
                        linearLayoutWithClear.InsertTriangleToLayout(shape);
                    }

                    listenButtons();

                }
            }
        });
    }

    public static ShapesGenerator getFigures() {
        return figures;
    }

}
