package com.mhcibasics.memetest.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.mhcibasics.memetest.R;
import com.mhcibasics.memetest.SectionsPageAdapter;

public class Home extends AppCompatActivity implements SendMessage {

    private static final String TAG ="Home";

    public int status = 1;

    public int steps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG, "onCreate: Starting");

        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.viewPager);

        setUpViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.TabLayout);

    }


    private void setUpViewPager(ViewPager viewPager){
        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        sectionsPageAdapter.addFragment(new Overview(status, steps), "Home");
        sectionsPageAdapter.addFragment(new Health(), "Health");
        sectionsPageAdapter.addFragment(new Settings(), "Settings");
        viewPager.setAdapter(sectionsPageAdapter);

    }

    //todo
    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
        Health health = (Health) getSupportFragmentManager().findFragmentByTag(tag);
        health.displayReceivedData(message);

    }
}
