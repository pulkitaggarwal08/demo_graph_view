package com.demo_graph_view.pulkit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class SimpleLineChartActivity extends AppCompatActivity implements

        //Todo:If you want to implement Gesture and Value Selection Listener

        OnChartGestureListener,
        OnChartValueSelectedListener {

    private static final String TAG = "MAinActivity";

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_line_chart);

        findIds();
        init();

    }

    private void findIds() {

        lineChart = (LineChart) findViewById(R.id.line_chart);
    }

    private void init() {

        lineChart.setOnChartGestureListener(this);
        lineChart.setOnChartValueSelectedListener(this);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        //Todo: Limit line
        lineLimit();

        //Todo: Add Data here
        ArrayList<Entry> entryArrayList = new ArrayList<>();

        entryArrayList.add(new Entry(0, 60f));
        entryArrayList.add(new Entry(1, 50f));
        entryArrayList.add(new Entry(2, 70f));
        entryArrayList.add(new Entry(3, 30f));
        entryArrayList.add(new Entry(4, 50f));
        entryArrayList.add(new Entry(5, 60f));
        entryArrayList.add(new Entry(6, 65f));

        LineDataSet lineDataSet = new LineDataSet(entryArrayList, "Data Set 1");

        lineDataSet.setFillAlpha(210);

        //Todo: set Color of line
        lineDataSet.setColor(Color.RED);

        //Todo: Set the width of line
        lineDataSet.setLineWidth(3f);

        //Todo: set the size of text
        lineDataSet.setValueTextSize(10f);

        //Todo: set the text Color
        lineDataSet.setValueTextColor(Color.GRAY);

        //Todo: set the color of circles
        lineDataSet.setCircleColor(Color.GRAY);

        ArrayList<ILineDataSet> iSetArrayList = new ArrayList<>();
        iSetArrayList.add(lineDataSet);

        LineData lineData = new LineData(iSetArrayList);

        lineChart.setData(lineData);

    }

    private void lineLimit() {

        LimitLine upperLine = new LimitLine(65, "Danger  ");
        upperLine.setLineWidth(2f);
        upperLine.enableDashedLine(15f, 20f, 0f);
        upperLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upperLine.setTextSize(8f);

        LimitLine lowerLine = new LimitLine(35, "Too Low");
        lowerLine.setLineWidth(2f);
        lowerLine.enableDashedLine(15f, 10f, 10f);
        lowerLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lowerLine.setTextSize(8f);

        //Todo: Hide Y-Axis
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upperLine);
        leftAxis.addLimitLine(lowerLine);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(25f);
        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawLimitLinesBehindData(true);

        //Todo: To hide the right points
        lineChart.getAxisRight().setEnabled(false);

        //Todo: Set the X-Axis Values
        String[] values = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"};

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new AxisValueFormatter(values));
        xAxis.setGranularity(1);

        //Todo: for both sides postion
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureStart: " + me.getX() + "Y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i(TAG, "onChartGestureEnd: " + lastPerformedGesture);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG, "onChartLongPressed: ");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG, "onChartDoubleTapped: ");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG, "onChartSingleTapped: ");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG, "onChartFling: velo X: " + velocityX + "velocity Y: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG, "onChartScale: scale X: " + scaleX + "Scale Y:" + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG, "onChartTranslate: dx: " + dX + "dy: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG, "onValueSelected: " + e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i(TAG, "onNothingSelected: ");
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
