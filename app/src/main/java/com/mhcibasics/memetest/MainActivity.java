package com.mhcibasics.memetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mhcibasics.memetest.Fragments.Home;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.welcome);
        button = findViewById(R.id.button_start);

        button.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("/-----Started-----/");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("/-----Stopped-----/");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("/-----Paused-----/");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("/-----Resume-----/");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("/-----Destroyed-----/");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_start){
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
    }
}
