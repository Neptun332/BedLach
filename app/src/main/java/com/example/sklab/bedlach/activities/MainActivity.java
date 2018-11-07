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
import com.example.sklab.bedlach.comparators.FigureAreaAscComparator;
import com.example.sklab.bedlach.comparators.FigureAreaDscComparator;
import com.example.sklab.bedlach.comparators.FigureNameAscComparator;
import com.example.sklab.bedlach.comparators.FigureNameDscComparator;
import com.example.sklab.bedlach.comparators.FigureParamAscComparator;
import com.example.sklab.bedlach.comparators.FigureParamDscComparator;
import com.example.sklab.bedlach.shapesfactory.ShapesGenerator;
import com.example.sklab.bedlach.shapesfactory.Shape;
import com.example.sklab.bedlach.shapesfactory.model.Circle;
import com.example.sklab.bedlach.shapesfactory.model.Square;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Shape> shapes;
    private LinearLayoutWithClear linearLayoutWithClear;
    private DecimalFormat decimalFormat;
    private SharedPreferences preferences;

    private ShapesGenerator figures;
    private boolean isSortByFigureNameClicked, isSortByAreaClicked, isSortByParameterClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        int amountToGenerate = Integer.parseInt(preferences.getString("amountToGenerate", "50"));
        double min = Double.parseDouble(preferences.getString("min", "0"));
        double max = Double.parseDouble(preferences.getString("max", "1"));
        figures = new ShapesGenerator(amountToGenerate,min,max);
        figures.generate();
        shapes = figures.getShapes();
        linearLayoutWithClear = findViewById(R.id.linearLayout);
        decimalFormat = figures.getDecimalFormat();
        inflateLinearLayout();
    }

    void inflateLinearLayout() {
        for (Shape shape: shapes) {
            if (shape instanceof Circle) {
                linearLayoutWithClear.InsertCircleToLayout(shape);
            } else if (shape instanceof Square) {
                linearLayoutWithClear.InsertSquareToLayout(shape);
            } else {
                linearLayoutWithClear.InsertTriangleToLayout(shape);
            }
        }
    }

    public void statsButton(View view) {
        Intent intent = new Intent(MainActivity.this, StatsActivity.class);
        startActivity(intent);

    }

    public void settingsButton(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void sortByAreaButton(View view) {
        linearLayoutWithClear.clearContent();
        if(!isSortByAreaClicked) {
            FigureAreaAscComparator figureAreaAscComparator = new FigureAreaAscComparator();
            Collections.sort(shapes,figureAreaAscComparator);
            isSortByAreaClicked = true;
        } else {
            FigureAreaDscComparator figureAreaDscComparator = new FigureAreaDscComparator();
            Collections.sort(shapes,figureAreaDscComparator);
            isSortByAreaClicked = false;
        }

        inflateLinearLayout();
    }

    public void sortByParameterButton(View view) {
        linearLayoutWithClear.clearContent();
        if(!isSortByParameterClicked) {
            FigureParamAscComparator figureParamAscComparator = new FigureParamAscComparator();
            Collections.sort(shapes,figureParamAscComparator);
            isSortByParameterClicked = true;
        } else {
            FigureParamDscComparator figureParamDscComparator = new FigureParamDscComparator();
            Collections.sort(shapes,figureParamDscComparator);
            isSortByParameterClicked = false;
        }

        inflateLinearLayout();
    }

    public void sortByFigureNameButton(View view) {
        linearLayoutWithClear.clearContent();
        if(!isSortByFigureNameClicked) {
            FigureNameAscComparator figureNameAscComparator = new FigureNameAscComparator();
            Collections.sort(shapes,figureNameAscComparator);
            isSortByFigureNameClicked = true;
        } else {
            FigureNameDscComparator figureNameDscComparator = new FigureNameDscComparator();
            Collections.sort(shapes,figureNameDscComparator);
            isSortByFigureNameClicked = false;
        }

        inflateLinearLayout();
    }
}
