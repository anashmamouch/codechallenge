package com.anas.codechallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.widget.ListView;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private ListView listView;

    DatabaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        mDatabaseHelper = new DatabaseHelper(MainActivity.this);

        updateUI();

        EventBus.getDefault().register(this);

    }

    public void updateUI(){

        final Handler handler = new Handler();

        Runnable runnableCode = new Runnable() {
            @Override
            public void run() {

                startService(new Intent(MainActivity.this, MyService.class));

                handler.postDelayed(this, 30000);
            }
        };

        handler.post(runnableCode);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseHelper.onUpgrade(mDatabaseHelper.getWritableDatabase(), 0, 0 );
    }

    @Subscribe
    public void onEvent(CustomEvent event){
        Log.d("MainActivity", "Event Fired");
        event.populateListView(getApplicationContext(), mDatabaseHelper, listView);

    }
}
