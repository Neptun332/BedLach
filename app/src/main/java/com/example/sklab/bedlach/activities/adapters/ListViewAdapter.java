package com.example.sklab.bedlach.activities.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sklab.bedlach.R;

import com.example.sklab.bedlach.shapesfactory.Shape;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Shape> {
    private Context context;
    private int resource;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.###");

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<Shape> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String figureName = getItem(position).getName();
        double area = getItem(position).getArea();
        double parameter = getItem(position).getParameter();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource,parent,false);

        TextView figureNameText = (TextView) convertView.findViewById(R.id.Figure_name);
        TextView areaText = (TextView) convertView.findViewById(R.id.area);
        TextView parameterText = (TextView) convertView.findViewById(R.id.parameter);

        figureNameText.setText(figureName);
        areaText.setText(decimalFormat.format(area));
        parameterText.setText(decimalFormat.format(parameter));

        return convertView;
    }

    @Override
    public void sort(@NonNull Comparator<? super Shape> comparator) {
        super.sort(comparator);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void addAll(Shape... items) {
        super.addAll(items);
    }

    @Override
    public void add(@Nullable Shape object) {
        super.add(object);
    }



    @Override
    public void remove(@Nullable Shape object) {
        super.remove(object);
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        super.setNotifyOnChange(notifyOnChange);
    }
}
