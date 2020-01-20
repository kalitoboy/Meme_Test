package com.mhcibasics.memetest.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mhcibasics.memetest.fragments.healthSub.Blinks;
import com.mhcibasics.memetest.fragments.healthSub.Concentration;
import com.mhcibasics.memetest.R;
import com.mhcibasics.memetest.fragments.healthSub.Steps;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;


public class Health extends Fragment {

    private static final String TAG = "HEALTH";

    int steps;

    PieChartView pieChartView;

    List<SliceValue> pieData;

    Button concentration_button, blinks_button, steps_button;

    SliceValue goal, current;

    String[] xAxis  = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"};
    int[] concentration_yAxis = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};
    float[] blinks_yAxis = {10.2f, 9.8f, 8.6f, 9.8f, 11.2f, 12.4f, 8.7f, 9.8f, 10.2f, 6.5f, 7.6f};


    public void displayReceivedData(String message) {
        reinitPieChart(pieChartView, pieData, message);
        Log.d(TAG, message);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_health, container, false);

        initPieChart(view);
        initLineChart(view);

        concentration_button = view.findViewById(R.id.concentration_button);
        blinks_button = view.findViewById(R.id.blinks_button);
        steps_button = view.findViewById(R.id.steps_button);

        steps_button.setBackgroundColor(Color.WHITE);

        concentration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

                if (v.getId() == concentration_button.getId()){
                    Intent intent = new Intent(Health.super.getContext(), Concentration.class);
                    startActivity(intent);
                }
            }
        });

        blinks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

                if (v.getId() == blinks_button.getId()){
                    Intent intent = new Intent(Health.super.getContext(), Blinks.class);
                    startActivity(intent);
                }
            }
        });

        steps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

                if (v.getId() == steps_button.getId()){
                    Intent intent = new Intent(Health.super.getContext(), Steps.class);
                    startActivity(intent);
                }
            }
        });



        return view;
    }


    public void initPieChart(View view){

        pieChartView = view.findViewById(R.id.steps_piechart);
        pieData = new ArrayList<>();

        goal = new SliceValue(42, Color.parseColor("#008577"));
        current = new SliceValue(steps, Color.DKGRAY);

        pieData.add(0, goal);
        pieData.add(1, current);

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true);
        pieChartData.setHasCenterCircle(true);
        pieChartData.setCenterText1("Steps").setCenterText1FontSize(20);

        pieChartView.setPieChartData(pieChartData);

    }


    private void reinitPieChart(PieChartView pieChartView, List<SliceValue> pieData, String message){

        this.pieChartView = pieChartView;
        this.pieData = pieData;

        current.setLabel(message);
        current.setValue(Integer.parseInt(message));
        pieData.set(1, current);

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true);
        pieChartData.setHasCenterCircle(true);
        //pieChartData.setCenterText1("Steps").setCenterText1FontSize(20);

        pieChartView.setPieChartData(pieChartData);
    }


    public void initLineChart(View view){

        LineChartView concentration_lineChartView = view.findViewById(R.id.concentration_linechart);
        LineChartView blinks_linechartView = view.findViewById(R.id.blinks_linechart);

        setConcentrationData(concentration_lineChartView);
        setBlinksData(blinks_linechartView);

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
