package com.demo_graph_view.pulkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_bar_chart, btn_horizontal_bar_chart, btn_line_chart, btn_color_filled_line_chart,
            btn_line_chart_with_cubic_lines, btn_grouped_line_chart, btn_combined_line_and_bar_chart, btn_pi_chart,
            btn_scatter_chart, btn_candlestick_chart, btn_radar_chart, btn_real_time_accelerometer_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findIds();
        init();

    }

    private void findIds() {

        btn_bar_chart = (Button) findViewById(R.id.btn_bar_chart);
        btn_horizontal_bar_chart = (Button) findViewById(R.id.btn_horizontal_bar_chart);
        btn_line_chart = (Button) findViewById(R.id.btn_line_chart);
        btn_color_filled_line_chart = (Button) findViewById(R.id.btn_color_filled_line_chart);
        btn_line_chart_with_cubic_lines = (Button) findViewById(R.id.btn_line_chart_with_cubic_lines);
        btn_grouped_line_chart = (Button) findViewById(R.id.btn_grouped_line_chart);
        btn_combined_line_and_bar_chart = (Button) findViewById(R.id.btn_combined_line_and_bar_chart);
        btn_pi_chart = (Button) findViewById(R.id.btn_pi_chart);
        btn_scatter_chart = (Button) findViewById(R.id.btn_scatter_chart);
        btn_candlestick_chart = (Button) findViewById(R.id.btn_candlestick_chart);
        btn_radar_chart = (Button) findViewById(R.id.btn_radar_chart);
        btn_real_time_accelerometer_data = (Button) findViewById(R.id.btn_real_time_accelerometer_data);
    }

    private void init() {

        btn_bar_chart.setOnClickListener(this);
        btn_horizontal_bar_chart.setOnClickListener(this);
        btn_line_chart.setOnClickListener(this);
        btn_color_filled_line_chart.setOnClickListener(this);
        btn_line_chart_with_cubic_lines.setOnClickListener(this);
        btn_grouped_line_chart.setOnClickListener(this);
        btn_combined_line_and_bar_chart.setOnClickListener(this);
        btn_pi_chart.setOnClickListener(this);
        btn_scatter_chart.setOnClickListener(this);
        btn_candlestick_chart.setOnClickListener(this);
        btn_radar_chart.setOnClickListener(this);
        btn_real_time_accelerometer_data.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.btn_bar_chart) {

            simpleBarChart();

        } else if (id == R.id.btn_horizontal_bar_chart) {

            horizontalBarChart();

        } else if (id == R.id.btn_line_chart) {

            simpleLineChart();

        } else if (id == R.id.btn_color_filled_line_chart) {

            colorFilledLineChart();

        } else if (id == R.id.btn_line_chart_with_cubic_lines) {

            lineWithCubicChart();

        } else if (id == R.id.btn_grouped_line_chart) {

            groupedLineChart();

        } else if (id == R.id.btn_combined_line_and_bar_chart) {

            combinedLineBarChart();

        } else if (id == R.id.btn_pi_chart) {

            piChart();

        } else if (id == R.id.btn_scatter_chart) {

            scatterChart();

        } else if (id == R.id.btn_candlestick_chart) {

            candlestickChart();

        } else if (id == R.id.btn_radar_chart) {

            radarChart();
        } else if(id == R.id.btn_real_time_accelerometer_data){

            realTimeAccelerometerData();
        }
    }

    private void simpleBarChart() {

        Intent intent = new Intent(getApplicationContext(), BarChartActivity.class);
        startActivity(intent);
    }

    private void horizontalBarChart() {

//        Intent intent = new Intent(getApplicationContext(), PiChartActivity.class);
//        startActivity(intent);
    }

    private void simpleLineChart() {

        Intent intent = new Intent(getApplicationContext(), SimpleLineChartActivity.class);
        startActivity(intent);
    }

    private void colorFilledLineChart() {

        Intent intent = new Intent(getApplicationContext(), ColorFilledLineChartActivity.class);
        startActivity(intent);
    }

    private void lineWithCubicChart() {

//        Intent intent = new Intent(getApplicationContext(), PiChartActivity.class);
//        startActivity(intent);
    }

    private void groupedLineChart() {

//        Intent intent = new Intent(getApplicationContext(), PiChartActivity.class);
//        startActivity(intent);
    }

    private void combinedLineBarChart() {

//        Intent intent = new Intent(getApplicationContext(), PiChartActivity.class);
//        startActivity(intent);
    }

    private void piChart() {

        Intent intent = new Intent(getApplicationContext(), PiChartActivity.class);
        startActivity(intent);
    }

    private void scatterChart() {

//        Intent intent = new Intent(getApplicationContext(), PiChartActivity.class);
//        startActivity(intent);
    }

    private void candlestickChart() {

//        Intent intent = new Intent(getApplicationContext(), PiChartActivity.class);
//        startActivity(intent);
    }

    private void radarChart() {

//        Intent intent = new Intent(getApplicationContext(), PiChartActivity.class);
//        startActivity(intent);
    }


    private void realTimeAccelerometerData() {

        Intent intent = new Intent(getApplicationContext(), RealTimeAccelerometerChartActivity.class);
        startActivity(intent);
    }


}
