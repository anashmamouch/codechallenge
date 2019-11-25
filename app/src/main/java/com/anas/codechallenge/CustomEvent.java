package com.anas.codechallenge;

import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomEvent {


    public void populateListView(Context context, DatabaseHelper mDatabaseHelper, ListView listview){
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();

        while(data.moveToNext()){
            listData.add("ID" + data.getString(0) + " : " + data.getString(1));
        }


        ListAdapter adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listData );
        listview.setAdapter(adapter);
    }


}
