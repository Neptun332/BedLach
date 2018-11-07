package com.example.sklab.bedlach;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.sklab.bedlach.shapesfactory.Shape;

import java.text.DecimalFormat;

public class LinearLayoutWithClear extends LinearLayout {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.###");

    public LinearLayoutWithClear(Context context) {
        super(context);
    }

    public LinearLayoutWithClear(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutWithClear(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LinearLayoutWithClear(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void InsertCircleToLayout (Shape shape) {
        View figure_layout = LayoutInflater.from(getContext()).inflate(R.layout.figure_layout, null);
        TextView circleTextViewFigureName = figure_layout.findViewById(R.id.Figure_name);
        circleTextViewFigureName.setText("Circle: ");

        TextView circleTextViewArea = figure_layout.findViewById(R.id.area);
        circleTextViewArea.setText(decimalFormat.format(shape.getArea()) + "");

        TextView circleTextViewParameter = figure_layout.findViewById(R.id.parameter);
        circleTextViewParameter.setText(decimalFormat.format(shape.getParameter()) + "");
        this.addView(figure_layout);
    }

    public void InsertSquareToLayout (Shape shape) {
        View figure_layout = LayoutInflater.from(getContext()).inflate(R.layout.figure_layout, null);
        TextView circleTextViewFigureName = figure_layout.findViewById(R.id.Figure_name);
        circleTextViewFigureName.setText("Square: ");

        TextView circleTextViewArea = figure_layout.findViewById(R.id.area);
        circleTextViewArea.setText(decimalFormat.format(shape.getArea())+ "");

        TextView circleTextViewParameter = figure_layout.findViewById(R.id.parameter);
        circleTextViewParameter.setText(decimalFormat.format(shape.getParameter()) + "");
        this.addView(figure_layout);
    }
    public void InsertTriangleToLayout (Shape shape) {
        View figure_layout = LayoutInflater.from(getContext()).inflate(R.layout.figure_layout, null);
        TextView circleTextViewFigureName = figure_layout.findViewById(R.id.Figure_name);
        circleTextViewFigureName.setText("Triangle: ");

        TextView circleTextViewArea = figure_layout.findViewById(R.id.area);
        circleTextViewArea.setText(decimalFormat.format(shape.getArea())+ "");

        TextView circleTextViewParameter = figure_layout.findViewById(R.id.parameter);
        circleTextViewParameter.setText(decimalFormat.format(shape.getParameter()) + "");
        this.addView(figure_layout);
    }

    public void clearContent() {
        this.removeAllViews();
    }
}
