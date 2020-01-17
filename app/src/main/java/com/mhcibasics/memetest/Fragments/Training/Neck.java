package com.mhcibasics.memetest.Fragments.Training;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mhcibasics.memetest.R;

public class Neck extends AppCompatActivity {

    TextView timer;

    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neck);

        timer = findViewById(R.id.timer);
        start = findViewById(R.id.training_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == start.getId()) {
                    CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText("seconds remaining: " + millisUntilFinished / 1000);
                        }

                        @Override
                        public void onFinish() {
                            timer.setText("Done!");
                        }
                    }.start();
                }
            }
        });
    }
}
