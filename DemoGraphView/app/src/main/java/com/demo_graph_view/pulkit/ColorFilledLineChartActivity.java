package com.demo_graph_view.pulkit;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class ColorFilledLineChartActivity extends AppCompatActivity {

    private LineChart[] lineChart = new LineChart[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_filled_line_chart);

        findIds();
        init();
    }

    private void findIds() {

        lineChart[0] = (LineChart) findViewById(R.id.line_chart1);
        lineChart[1] = (LineChart) findViewById(R.id.line_chart2);
    }

    private void init() {

        for (int i = 0; i < lineChart.length; i++) {

            LineData data = getData(16, 30); // count 16, Range 30
            setUpChart(lineChart[i], data, mColors[i]);
        }
    }

    private void setUpChart(LineChart chart, LineData data, int color) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleColorHole(color);

        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);

        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);

        chart.setBackgroundColor(color);
        chart.setViewPortOffsets(10, 0, 10, 0);

        //Todo: It is used to disable the Legends(Showing Data Set in the graph)
        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        chart.setData(data);
    }


    private LineData getData(int count, int range) {

        ArrayList<Entry> entryArrayList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range + 3);

            entryArrayList.add(new Entry(i, val));
        }

        LineDataSet lineDataSet = new LineDataSet(entryArrayList, "Data Set");

        //Todo: It is used to set the width of the line
        lineDataSet.setLineWidth(3f);

        //Todo: It is used to set the radius of the circle
        lineDataSet.setCircleRadius(5f);

        //Todo: It is used to set the hole radius of the circle
        lineDataSet.setCircleHoleRadius(2.5f);

        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setCircleColor(Color.GRAY);
        lineDataSet.setHighLightColor(Color.GRAY);

        //Todo: It is used to draw the line
        lineDataSet.setDrawValues(true);

        //Todo: It is used to hide the circles of the graph
        lineDataSet.setDrawCircles(false);

        //Todo: It is used to set the line in design or curving
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCubicIntensity(0.2f);

        //Todo: It is used to fill the color below the lines automatic
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(Color.CYAN);

        //Todo: It is used to set the grid of the line
        lineDataSet.setFillAlpha(50);

        //Todo: It is used to fill the color below the lines manually
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.gradient);
        lineDataSet.setFillDrawable(drawable);

        LineData lineData = new LineData(lineDataSet);
        return lineData;

    }

    private int[] mColors = new int[]{

            Color.rgb(137, 230, 81), Color.rgb(240, 230, 30)
    };


}
