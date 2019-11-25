package com.anas.codechallenge;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;


public class MyService extends IntentService {

    DatabaseHelper mDatabaseHelper;


    public MyService(){
        super("MyService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {

        String[] array = {

                "Corrin", "Elizebeth", "Suzi", "Jeneva", "Galina", "Corliss", "Carey",
                "Josef", "Becky", "Irena", "Chantay", "Margot", "Sandi", "Madeline",
                "Refugia", "Sam", "Kurtis", "Aracelis", "Lonnie", "Marine", "Dawn",
                "Antone", "Harriet", "Theodore", "Christie", "Freddie", "Claudie",
                "Digna", "Jeraldine", "Edgar", "Gustavo", "Marlyin", "Mark",
                "Jumil", "Curtis", "Hendrix", "Clover", "Dove", "Nancy",
                "Becky", "Joe", "Steven", "Geraldine", "Benjamine", "Luffy", "Zoro", "Sanji",
                "Wendy", "Nami", "Theodore",

                "Dorolice", "Dorotea", "Dorree", "Dorthy", "Dotti", "Drona", "Drucci", "Dulce",
                "Dusty", "Dyan", "Dyana", "Oda", "Fanta", "Easter", "Ebony", "Corosa", "Corolla",
                "Fancy", "Frola", "Concordia", "Chealsea", "Magnum", "Courtney", "Eddeline", "Eddy",
                "Effie", "Carla", "Samba", "Trolly", "Alberta", "Gina", "Alexa", "Groomy", "Mandy",
                "Ralph", "Sean", "Parker", "Jodi", "Raul", "Cortez", "Spike", "Rudolf", "Proly", "Mauk",

                "Olga", "Cintia", "Naomi", "Yoda", "Jankize", "Fiona", "Hendy", "Valeria", "Kordi",
                "Sandeberg", "Kony", "Welly", "Boss", "Onion", "Retalia", "Kimberley", "Shania", "Rebecca",
                "Python", "Ferdinand", "Lampard", "Dorcy", "Julien", "Derek", "Jimbey", "Lorence", "Pablo",
                "Escobar", "Moulin","Kirk", "Swarmly", "Narnia", "Colorado", "Dumbeldor", "Harry", "Siri",
                "Rola", "Diana", "Ginger", "Billy", "Wesley", "Hermione", "Vandelberg", "Patrick",

                "Rotary", "Unity", "Norland"

        };

        Log.d("MyService ====>" , ""  + array.length);


        Random rand = new Random();
        int index = rand.nextInt(array.length - 1);


        mDatabaseHelper = new DatabaseHelper(MyService.this);
        mDatabaseHelper.addData(array[index]);

        if (mDatabaseHelper.getNumberOfRows() > 50 ){
            mDatabaseHelper.onUpgrade(mDatabaseHelper.getWritableDatabase(), 0 ,0 );

            Log.d("MyService", "Drop database when rows reach 50 ");

            Toast.makeText(getApplicationContext(), "Database Cleared!", Toast.LENGTH_SHORT).show();
        }


        Log.d("MainActivity", "" + mDatabaseHelper.getNumberOfRows());


        CustomEvent event = new CustomEvent();
        EventBus.getDefault().post(event);


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d("MainActivity", "cool stuff");

    }
}
