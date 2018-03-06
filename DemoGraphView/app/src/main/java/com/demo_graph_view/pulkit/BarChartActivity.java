package com.demo_graph_view.pulkit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        findIds();
        init();

    }

    private void findIds() {

        barChart = (BarChart) findViewById(R.id.bar_chart);
    }

    private void init() {

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        //Todo: Add Data here
        ArrayList<BarEntry> entryArrayList = new ArrayList<>();

        entryArrayList.add(new BarEntry(1, 50f));
        entryArrayList.add(new BarEntry(2, 70f));
        entryArrayList.add(new BarEntry(3, 30f));
        entryArrayList.add(new BarEntry(4, 50f));

        ArrayList<BarEntry> entryArrayList1 = new ArrayList<>();
        entryArrayList1.add(new BarEntry(1, 20f));
        entryArrayList1.add(new BarEntry(2, 50f));
        entryArrayList1.add(new BarEntry(3, 10f));
        entryArrayList1.add(new BarEntry(4, 70f));

        BarDataSet barDataSet = new BarDataSet(entryArrayList, "Data Set 1");
        BarDataSet barDataSet1 = new BarDataSet(entryArrayList1, "Data Set 2");

        //Todo: for template color
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet1.setColors(ColorTemplate.JOYFUL_COLORS);

        BarData barData = new BarData(barDataSet, barDataSet1);

        //Todo: Group Bar
        float groupSpace = 0.1f;
        float barSpace = 0.02f;
        float barWidth = 0.43f;

        barChart.setData(barData);

        barData.setBarWidth(barWidth);
        barChart.groupBars(1, groupSpace, barSpace);

        //Todo: Set the X-Axis Values
        String[] values = new String[]{"Jan", "Feb", "Mar", "Apr", "May"};

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new AxisValueFormatter(values));
        xAxis.setGranularity(1);
//        xAxis.setCenterAxisLabels(true);
//        xAxis.setAxisMinimum(1);

        //Todo: for both sides postion
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);


    }

    public class AxisValueFormatter implements IAxisValueFormatter {

        private String mvalues[];

        public AxisValueFormatter(String[] values) {
            this.mvalues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mvalues[(int) value];
        }

    }

}
