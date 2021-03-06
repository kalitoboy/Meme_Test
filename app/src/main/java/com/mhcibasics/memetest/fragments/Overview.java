package com.mhcibasics.memetest.fragments;

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

import com.mhcibasics.memetest.fragments.training.Neck;
import com.mhcibasics.memetest.R;


public class Overview extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "OVERVIEW";

    private Button connect;

    private TextView text_status;
    private TextView battery_level;

    int connected, steps, bat_level;

    private Spinner spinner_training;


    public Overview(int connected, int steps) {
        this.connected = connected;
        this.steps = steps;
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            bat_level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            battery_level.setText(bat_level + "%");
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        connect = view.findViewById(R.id.button_connect);
        text_status = view.findViewById(R.id.text_connect);
        battery_level = view.findViewById(R.id.text_battery_level);
        TextView text_steps_total = view.findViewById(R.id.text_steps_total);
        spinner_training = view.findViewById(R.id.spinner_steps);

        text_steps_total.setText(String.valueOf(steps));

        getActivity().registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        if (connected == 0) {
            text_status.setText("Connected");
            connect.setText("Disconnect");
        }
        else if (connected == 1) {
            text_status.setText("Disconnected");
            connect.setText("Connect");
        }


        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connected == 0) {
                    connected++;
                    Toast.makeText(getActivity(), "Connecting", Toast.LENGTH_SHORT).show();
                    text_status.setText("Connected");
                    connect.setText("Disconnect");
                }
                else if (connected == 1) {
                    connected--;
                    Toast.makeText(getActivity(), "Disconnecting", Toast.LENGTH_SHORT).show();
                    text_status.setText("Disconnected");
                    connect.setText("Connect");
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
            Intent intent = new Intent(getContext(), Neck.class);
            startActivity(intent);
            spinner_training.setSelection(0);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
