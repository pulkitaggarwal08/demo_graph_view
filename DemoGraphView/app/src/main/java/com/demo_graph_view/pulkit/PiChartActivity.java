package com.demo_graph_view.pulkit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PiChartActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_pi_chart);

        findIds();
        init();
    }

    private void findIds() {

        pieChart = (PieChart) findViewById(R.id.pie_chart);
    }

    private void init() {

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(true);

        //Todo: ExtraOffsets is used for taking gap from all sides
        pieChart.setExtraOffsets(5, 10, 5, 5);

        //Todo: It is used to move the graph in circle
        pieChart.setDragDecelerationFrictionCoef(0.95f);

        //Todo: For center holw
        pieChart.setDrawHoleEnabled(false);

        //Todo: For color of center hole
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);


        //Todo: Add Data here
        ArrayList<PieEntry> entryArrayList = new ArrayList<>();

        entryArrayList.add(new PieEntry(20f, "Pak"));
        entryArrayList.add(new PieEntry(15f, "USA"));
        entryArrayList.add(new PieEntry(14f, "UK"));
        entryArrayList.add(new PieEntry(21f, "INDIA"));
        entryArrayList.add(new PieEntry(30f, "RUSSIA"));

        //Todo: Animated view for graph
        pieChart.animateY(3000, Easing.EasingOption.EaseInOutQuad);

        //Todo: for description Label
        Description description = new Description();
        description.setText("Pi graph");
        description.setTextSize(13);
        pieChart.setDescription(description);

        PieDataSet dataSet = new PieDataSet(entryArrayList, " Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //Todo: for template color
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(10f);

        //Todo: color for value
        pieData.setValueTextColor(Color.YELLOW);

        pieChart.setData(pieData);

    }

}
