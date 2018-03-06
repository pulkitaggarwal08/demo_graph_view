package com.demo_graph_view.pulkit;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public class RealTimeAccelerometerChartActivity extends AppCompatActivity implements SensorEventListener {

    private LineChart lineChart;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Thread thread;
    private boolean plotData = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_accelerometer_chart);

        findIds();
        init();
    }

    private void findIds() {

        lineChart = (LineChart) findViewById(R.id.line_chart);

    }

    private void init() {

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        }

        lineChart.getDescription().setEnabled(true);
        lineChart.getDescription().setText("Real Time Data");

        lineChart.setTouchEnabled(false);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setPinchZoom(false);

        lineChart.setBackgroundColor(Color.WHITE);

        LineData lineData = new LineData();
        lineData.setValueTextColor(Color.WHITE);
        lineChart.setData(lineData);

        // Todo: get the legend (only possible after setting data)
        Legend l = lineChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis xl = lineChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMaximum(10f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);

        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.setDrawBorders(false);

        startPlot();
    }

    private void addEntry(SensorEvent sensorEvent) {

        LineData lineData = lineChart.getData();

        if (lineData != null) {

            ILineDataSet dataSet = lineData.getDataSetByIndex(0);

            if (dataSet == null) {
                dataSet = creatSet();
                lineData.addDataSet(dataSet);
            }

            lineData.addEntry(new Entry(dataSet.getEntryCount(), sensorEvent.values[0] + 5), 0);

            lineData.notifyDataChanged();

            lineChart.notifyDataSetChanged();
            //Todo: sensor when give the value, it will show below 150, not more than 150
            lineChart.setVisibleXRangeMaximum(150);
            lineChart.moveViewToX(lineData.getEntryCount());

        }

    }

    private LineDataSet creatSet() {

        LineDataSet lineDataSet = new LineDataSet(null, "Dynamic Data");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setLineWidth(3f);
        lineDataSet.setColor(Color.MAGENTA);

        lineDataSet.setHighlightEnabled(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircles(false);

        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCubicIntensity(0.2f);

        return lineDataSet;
    }

    private void startPlot() {
        if (thread != null) {
            thread.interrupt();
        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    plotData = true;

                    try {

                        Thread.sleep(10);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (plotData) {
            addEntry(sensorEvent);
            plotData = false;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (thread != null) {
            thread.interrupt();
        }

        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onDestroy() {

        sensorManager.unregisterListener(this);
        thread.interrupt();
        super.onDestroy();
    }
}
