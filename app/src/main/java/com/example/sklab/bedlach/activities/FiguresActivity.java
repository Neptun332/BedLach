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
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.sklab.bedlach.LinearLayoutWithClear;
import com.example.sklab.bedlach.R;
import com.example.sklab.bedlach.activities.adapters.ListViewAdapter;
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
import com.example.sklab.bedlach.shapesfactory.model.Triangle;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class FiguresActivity extends AppCompatActivity {
    private List<Shape> shapes;
    private ListView listView;
    private ListViewAdapter listViewAdapter;

    private ShapesGenerator figures;
    private boolean isSortByFigureNameClicked, isSortByAreaClicked, isSortByParameterClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        registerForContextMenu(listView);

        generateDataFromPreferences();

        listViewAdapter = new ListViewAdapter(this, R.layout.list_item_layout, shapes);
        listViewAdapter.setNotifyOnChange(true);
        listView.setAdapter(listViewAdapter);
    }

    private void generateDataFromPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FiguresActivity.this);
        int amountToGenerate = Integer.parseInt(preferences.getString("amountToGenerate", "50"));
        double min = Double.parseDouble(preferences.getString("min", "0"));
        double max = Double.parseDouble(preferences.getString("max", "1"));
        resetStatistics();
        figures = new ShapesGenerator(amountToGenerate, min, max);
        figures.generate();
        shapes = figures.getShapes();
    }

    private void resetStatistics() {
        Circle.setCircleCount(0);
        Circle.setSumOfArea(0);
        Circle.setSumOfParameter(0);

        Triangle.setTriangleCount(0);
        Triangle.setSumOfArea(0);
        Triangle.setSumOfParameter(0);

        Square.setSquareCount(0);
        Square.setSumOfArea(0);
        Square.setSumOfParameter(0);
    }

    private void generateSingleRandomFigure() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FiguresActivity.this);
        double min = Double.parseDouble(preferences.getString("min", "0"));
        double max = Double.parseDouble(preferences.getString("max", "1"));
        figures = new ShapesGenerator(min, max);
        figures.singleItemGenerate();

        listViewAdapter.add(figures.getSingleShape());
    }


    public void sortByAreaButton(View view) {
        if (!isSortByAreaClicked) {
            FigureAreaAscComparator figureAreaAscComparator = new FigureAreaAscComparator();
            listViewAdapter.sort(figureAreaAscComparator);
            isSortByAreaClicked = true;
        } else {
            FigureAreaDscComparator figureAreaDscComparator = new FigureAreaDscComparator();
            listViewAdapter.sort(figureAreaDscComparator);
            isSortByAreaClicked = false;
        }
    }

    public void sortByParameterButton(View view) {
        if (!isSortByParameterClicked) {
            FigureParamAscComparator figureParamAscComparator = new FigureParamAscComparator();
            listViewAdapter.sort(figureParamAscComparator);
            isSortByParameterClicked = true;
        } else {
            FigureParamDscComparator figureParamDscComparator = new FigureParamDscComparator();
            listViewAdapter.sort(figureParamDscComparator);
            isSortByParameterClicked = false;
        }
    }

    public void sortByFigureNameButton(View view) {
        if (!isSortByFigureNameClicked) {
            FigureNameAscComparator figureNameAscComparator = new FigureNameAscComparator();
            listViewAdapter.sort(figureNameAscComparator);
            isSortByFigureNameClicked = true;
        } else {
            FigureNameDscComparator figureNameDscComparator = new FigureNameDscComparator();
            listViewAdapter.sort(figureNameDscComparator);
            isSortByFigureNameClicked = false;
        }
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
                listViewAdapter.clear();
                listViewAdapter.addAll(shapes);
                return true;
            case R.id.statistics:
                Intent intent = new Intent(FiguresActivity.this, StatsActivity.class);
                startActivity(intent);
                return true;
            case R.id.add_item:
                generateSingleRandomFigure();
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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
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
        Shape shapeToDelete = listViewAdapter.getItem((int) id);
        lowerShapeCounter(shapeToDelete);
        listViewAdapter.remove(shapeToDelete);
    }

    private void lowerShapeCounter(Shape shapeToDelete) {
        if(shapeToDelete instanceof Circle) {
            Circle.setCircleCount(Circle.getCircleCount() - 1);
        } else if(shapeToDelete instanceof Triangle) {
            Triangle.setTriangleCount(Triangle.getTriangleCount() - 1);
        } else {
            Square.setSquareCount(Square.getSquareCount() - 1);
        }
    }

    private void duplicateFigure(long id) {
        Shape duplicatedShape = listViewAdapter.getItem((int) id);
        listViewAdapter.add(duplicatedShape);
    }
}
