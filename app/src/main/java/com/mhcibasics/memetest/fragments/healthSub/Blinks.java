package com.mhcibasics.memetest.fragments.healthSub;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.mhcibasics.memetest.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class Blinks extends AppCompatActivity {

    LineChartView blinks_lineChartView;

    String[] xAxis  = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"};

    int[] blinks_yAxis = {10, 10, 9, 10, 11, 12, 9, 10, 10, 7, 8, 4};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blinks);

        initLineChart();

    }

    public void initLineChart(){

        blinks_lineChartView = findViewById(R.id.blinks_linechart);

        setBlinksData(blinks_lineChartView);

    }

    public void initAxis(List xVal, LineChartData lineChartData){

        Axis xAxis = new Axis();
        xAxis.setValues(xVal);
        xAxis.setTextColor(Color.DKGRAY);
        lineChartData.setAxisXBottom(xAxis);

        Axis yAxis = new Axis().setLineColor(Color.DKGRAY);
        yAxis.setTextColor(Color.DKGRAY);
        lineChartData.setAxisYLeft(yAxis);

    }

    private void setBlinksData(LineChartView lineChartView){

        List xValue = new ArrayList();
        List yValues = new ArrayList();

        Line yVal_line = new Line(yValues).setColor(Color.parseColor("#008577"));

        for(int i = 0; i < xAxis.length; i++){
            xValue.add(i, new AxisValue(i).setLabel(xAxis[i]));
        }

        for (int i = 0; i < blinks_yAxis.length; i++){
            yValues.add(new PointValue(i, blinks_yAxis[i]));
        }

        List blinks_line = new ArrayList();
        blinks_line.add(yVal_line);

        LineChartData data = new LineChartData();
        data.setLines(blinks_line);

        lineChartView.setLineChartData(data);

        initAxis(xValue, data);

    }
}
