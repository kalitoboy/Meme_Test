package com.mhcibasics.memetest.fragments.training;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mhcibasics.memetest.R;

public class Neck extends AppCompatActivity {

    final String TAG = "Neck";

    TextView timer, step_1, step_2, step_3;

    Button start;

    int countdown, counter;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neck);

        timer = findViewById(R.id.timer);
        step_1 = findViewById(R.id.neck_step_1);
        step_2 = findViewById(R.id.neck_step_2);
        step_3 = findViewById(R.id.neck_step_3);

        start = findViewById(R.id.training_start);

        countdown = 3000;

        step_1.setText("Tilt your neck up and down");
        step_1.setTextColor(Color.GRAY);

        step_2.setText("Turn your head from left to right");
        step_2.setTextColor(Color.GRAY);

        step_3.setText("Hold chin leveled, push head for and backwards");
        step_3.setTextColor(Color.GRAY);

        counter = 0;


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == start.getId() && counter == 0) {

                    start.setText("Stop");
                    counter = 1;

                    countDownTimer = new CountDownTimer(countdown, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText("seconds remaining: " + millisUntilFinished / 1000);

                            if (millisUntilFinished > 20000) {
                                step_1.setTextColor(Color.RED);
                            } else if (millisUntilFinished > 10000 && millisUntilFinished <= 20000) {
                                step_2.setTextColor(Color.RED);
                                step_1.setTextColor(Color.GRAY);
                            } else if (millisUntilFinished <= 10000) {
                                step_3.setTextColor(Color.RED);
                                step_2.setTextColor(Color.GRAY);
                            }
                        }

                        @Override
                        public void onFinish() {
                            timer.setText("Done!");
                            start.setText("Restart?");
                            step_3.setTextColor(Color.GRAY);
                            counter = 0;
                        }
                    }.start();
                    Log.d(TAG, "Timer started");
                }
                else if (v.getId() == start.getId() && counter == 1){
                    start.setText("Start");
                    timer.setText("Interrupted!");
                    counter = 0;

                    countDownTimer.cancel();
                    Log.d(TAG, "Timer finished");


                }
            }
        });
    }
}
