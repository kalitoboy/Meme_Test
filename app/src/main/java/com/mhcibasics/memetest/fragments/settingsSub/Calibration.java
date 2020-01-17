package com.mhcibasics.memetest.fragments.settingsSub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mhcibasics.memetest.R;

public class Calibration extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "Calibration";

    private TextView x, y, z;
    private Button calibrate;

    int xoffset, yoffset, zoffset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);

        x = findViewById(R.id.text_x);
        y = findViewById(R.id.text_y);
        z = findViewById(R.id.text_z);

        calibrate = findViewById(R.id.button_calibrate);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onSensorChanged(final SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x.setText(String.format("X: %2.2f", event.values[0]));
            y.setText(String.format("Y: %2.2f", event.values[1]));
            z.setText(String.format("Z: %2.2f", event.values[2]));
        }

        calibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == calibrate.getId()){
                    /*xoffset = (int) event.values[0];
                    yoffset = (int) event.values[1];
                    zoffset = (int) event.values[2];

                    x.setText(String.format("X: %2.2f", event.values[0]));
                    y.setText(String.format("Y: %2.2f", event.values[1]));
                    z.setText(String.format("Z: %2.2f", event.values[2]));*/

                    Log.d(TAG, "calibrated");

                }
            }
        });

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
