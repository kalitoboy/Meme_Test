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

public class Concentration extends AppCompatActivity {

    LineChartView concentration_lineChartView;

    String[] xAxis  = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"};

    int[] concentration_yAxis = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concentration);

         initLineChart();


    }

    public void initLineChart(){

        concentration_lineChartView = findViewById(R.id.concentration_linechart);

        setConcentrationData(concentration_lineChartView);

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

    private void setConcentrationData(LineChartView lineChartView){

        List xValue = new ArrayList();
        List yValues = new ArrayList();

        Line yVal_line = new Line(yValues).setColor(Color.parseColor("#008577"));

        for(int i = 0; i < xAxis.length; i++){
            xValue.add(i, new AxisValue(i).setLabel(xAxis[i]));
        }

        for (int i = 0; i < concentration_yAxis.length; i++){
            yValues.add(new PointValue(i, concentration_yAxis[i]));
        }

        List concentration_line = new ArrayList();
        concentration_line.add(yVal_line);

        LineChartData data = new LineChartData();
        data.setLines(concentration_line);

        lineChartView.setLineChartData(data);

        initAxis(xValue, data);

    }

}
