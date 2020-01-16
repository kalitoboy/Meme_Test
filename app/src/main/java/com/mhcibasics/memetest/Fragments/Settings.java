package com.mhcibasics.memetest.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mhcibasics.memetest.Calibration;
import com.mhcibasics.memetest.R;


public class Settings extends Fragment implements AdapterView.OnItemSelectedListener, SendMessage {

    public SendMessage sendMessage;

    private static final String TAG = "SETTINGS";

    private Button connect, calibration;

    private TextView text_status, battery_level;

    private Spinner spinner_steps;

    int status, bat_level, steps;

    @Override
    public void sendData(String message) {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            sendMessage = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_settings, container, false);

        connect = view.findViewById(R.id.button_connect);
        calibration = view.findViewById(R.id.button_calibration);

        text_status = view.findViewById(R.id.text_connect);

        battery_level = view.findViewById(R.id.text_battery_level);

        spinner_steps = view.findViewById(R.id.spinner_steps);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(super.getContext(), R.array.array_steps, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_steps.setAdapter(arrayAdapter);
        spinner_steps.setOnItemSelectedListener(this);


        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status == 0) {
                    status++;
                    Toast.makeText(getActivity(), "Connecting", Toast.LENGTH_SHORT).show();
                    text_status.setText("Connected");
                    connect.setText("Disconnect");

                } else if (status == 1) {
                    status--;
                    Toast.makeText(getActivity(), "Disconnecting", Toast.LENGTH_SHORT).show();
                    text_status.setText("Disconnected");
                    connect.setText("Connect");
                }
            }
        });

        calibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

                if (v.getId() == calibration.getId()){
                    Intent intent = new Intent(view.getContext(), Calibration.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String message =  parent.getSelectedItem().toString();
        sendMessage.sendData(message);
        Log.d(TAG, message);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}