package com.example.sklab.bedlach.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sklab.bedlach.R;
import com.example.sklab.bedlach.shapesfactory.ShapesGenerator;
import com.example.sklab.bedlach.shapesfactory.Shape;
import com.example.sklab.bedlach.shapesfactory.model.Circle;
import com.example.sklab.bedlach.shapesfactory.model.Square;
import com.example.sklab.bedlach.shapesfactory.model.Triangle;

import java.text.DecimalFormat;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

    private ShapesGenerator figures;
    private List<Shape> shapes;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        figures = MainActivity.getFigures();
        decimalFormat = figures.getDecimalFormat();

        TextView CircleCount = findViewById(R.id.CircleCount);
        TextView SquareCount = findViewById(R.id.SquareCount);
        TextView TriangleCount = findViewById(R.id.TriangleCount);

        TextView CircleSumofArea = findViewById(R.id.CircleSumofArea);
        TextView SquareSumofArea = findViewById(R.id.SquareSumofArea);
        TextView TriangleSumofArea = findViewById(R.id.TriangleSumofArea);

        TextView CircleSumofParameter = findViewById(R.id.CircleSumofParameter);
        TextView SquareSumofParameter = findViewById(R.id.SquareSumofParameter);
        TextView TriangleSumofParameter = findViewById(R.id.TriangleSumofParameter);

        CircleCount.setText(Circle.getCircleCount()+ " ");
        SquareCount.setText(Square.getSquareCount()+ " ");
        TriangleCount.setText(Triangle.getTriangleCount()+ " ");


        CircleSumofArea.setText(decimalFormat.format(Circle.getSumOfArea()) + "");
        SquareSumofArea.setText(decimalFormat.format(Square.getSumOfArea()) + "");
        TriangleSumofArea.setText(decimalFormat.format(Triangle.getSumOfArea()) + "");

        CircleSumofParameter.setText(decimalFormat.format(Circle.getSumOfParameter()) + " ");
        SquareSumofParameter.setText(decimalFormat.format(Square.getSumOfParameter()) + " ");
        TriangleSumofParameter.setText(decimalFormat.format(Triangle.getSumOfParameter()) + " ");







    }
}
