package com.mhcibasics.memetest.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mhcibasics.memetest.Fragments.Training.Neck;
import com.mhcibasics.memetest.R;


public class Overview extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "OVERVIEW";

    private Button button;

    private TextView text_status, battery_level, text_steps_total;

    int status, steps, bat_level;

    private Spinner spinner_training;


    public Overview(int status, int steps) {
        this.status = status;
        this.steps = steps;
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            bat_level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            battery_level.setText(String.valueOf(bat_level) + "%");
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        button = view.findViewById(R.id.button_connect);
        text_status = view.findViewById(R.id.text_connect);
        battery_level = view.findViewById(R.id.text_battery_level);
        text_steps_total = view.findViewById(R.id.text_steps_total);
        spinner_training = view.findViewById(R.id.spinner_steps);

        text_steps_total.setText(String.valueOf(steps));

        getActivity().registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        if (status == 0) {
            text_status.setText("Connected");
            button.setText("Disconnect");
        }
        else if (status == 1) {
            text_status.setText("Disconnected");
            button.setText("Connect");
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status == 0) {
                    status++;
                    Toast.makeText(getActivity(), "Connecting", Toast.LENGTH_SHORT).show();
                    text_status.setText("Connected");
                    button.setText("Disconnect");
                }
                else if (status == 1) {
                    status--;
                    Toast.makeText(getActivity(), "Disconnecting", Toast.LENGTH_SHORT).show();
                    text_status.setText("Disconnected");
                    button.setText("Connect");
                }
            }
        });

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(super.getContext(), R.array.array_training, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_training.setAdapter(arrayAdapter);
        spinner_training.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getSelectedItem().equals("Neck")){
            Intent intent = new Intent(view.getContext(), Neck.class);
            startActivity(intent);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
