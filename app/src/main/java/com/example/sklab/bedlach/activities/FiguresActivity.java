package com.example.sklab.bedlach.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;

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

public class FiguresActivity extends AppCompatActivity {

    private List<Shape> shapes;
    private LinearLayoutWithClear linearLayoutWithClear;
    private ScrollView scrollView;

    private ShapesGenerator figures;
    private boolean isSortByFigureNameClicked, isSortByAreaClicked, isSortByParameterClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayoutWithClear = findViewById(R.id.linearLayout);
        registerForContextMenu(linearLayoutWithClear);

        generateDataFromPreferences();
        inflateLinearLayout();
    }

    private void generateDataFromPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FiguresActivity.this);
        int amountToGenerate = Integer.parseInt(preferences.getString("amountToGenerate", "50"));
        double min = Double.parseDouble(preferences.getString("min", "0"));
        double max = Double.parseDouble(preferences.getString("max", "1"));
        figures = new ShapesGenerator(amountToGenerate, min, max);
        figures.generate();
        shapes = figures.getShapes();
    }

    private void generateSingleRandomFigure() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FiguresActivity.this);
        int amountToGenerate = Integer.parseInt(preferences.getString("amountToGenerate", "50"));
        double min = Double.parseDouble(preferences.getString("min", "0"));
        double max = Double.parseDouble(preferences.getString("max", "1"));
        List<Shape> tempShapes = shapes;
        figures = new ShapesGenerator(amountToGenerate, min, max);
        figures.setShapes(tempShapes);
        figures.singleItemGenerate();
        shapes = figures.getShapes();
    }


    void inflateLinearLayout() {
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                linearLayoutWithClear.InsertCircleToLayout(shape);
            } else if (shape instanceof Square) {
                linearLayoutWithClear.InsertSquareToLayout(shape);
            } else {
                linearLayoutWithClear.InsertTriangleToLayout(shape);
            }
        }
    }

    public void sortByAreaButton(View view) {
        linearLayoutWithClear.clearContent();
        if (!isSortByAreaClicked) {
            FigureAreaAscComparator figureAreaAscComparator = new FigureAreaAscComparator();
            Collections.sort(shapes, figureAreaAscComparator);
            isSortByAreaClicked = true;
        } else {
            FigureAreaDscComparator figureAreaDscComparator = new FigureAreaDscComparator();
            Collections.sort(shapes, figureAreaDscComparator);
            isSortByAreaClicked = false;
        }

        inflateLinearLayout();
    }

    public void sortByParameterButton(View view) {
        linearLayoutWithClear.clearContent();
        if (!isSortByParameterClicked) {
            FigureParamAscComparator figureParamAscComparator = new FigureParamAscComparator();
            Collections.sort(shapes, figureParamAscComparator);
            isSortByParameterClicked = true;
        } else {
            FigureParamDscComparator figureParamDscComparator = new FigureParamDscComparator();
            Collections.sort(shapes, figureParamDscComparator);
            isSortByParameterClicked = false;
        }

        inflateLinearLayout();
    }

    public void sortByFigureNameButton(View view) {
        linearLayoutWithClear.clearContent();
        if (!isSortByFigureNameClicked) {
            FigureNameAscComparator figureNameAscComparator = new FigureNameAscComparator();
            Collections.sort(shapes, figureNameAscComparator);
            isSortByFigureNameClicked = true;
        } else {
            FigureNameDscComparator figureNameDscComparator = new FigureNameDscComparator();
            Collections.sort(shapes, figureNameDscComparator);
            isSortByFigureNameClicked = false;
        }

        inflateLinearLayout();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent_settings = new Intent(FiguresActivity.this, SettingsActivity.class);
                startActivity(intent_settings);
                return true;
            case R.id.reset_list:
                generateDataFromPreferences();
                linearLayoutWithClear.clearContent();
                inflateLinearLayout();
                return true;
            case R.id.statistics:
                Intent intent = new Intent(FiguresActivity.this, StatsActivity.class);
                startActivity(intent);
                return true;
            case R.id.add_item:
                generateSingleRandomFigure();
                linearLayoutWithClear.clearContent();
                inflateLinearLayout();
                return true;
            default:
              return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); //todo it return null pointer
        switch (item.getItemId()) {
            case R.id.duplicate:
                duplicateFigure(info.id);
                return true;
            case R.id.delete:
                deleteFigure(info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteFigure(long id) {
        shapes.remove((int) id);
        linearLayoutWithClear.clearContent();
        inflateLinearLayout();
    }

    private void duplicateFigure(long id) {
        Shape duplicatedShape = shapes.get((int) id);
        shapes.add(duplicatedShape);
        linearLayoutWithClear.clearContent();
        inflateLinearLayout();
    }
}
