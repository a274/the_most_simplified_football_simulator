package com.example.simpleanimation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   // private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MyView view = new MyView(this);
        super.onCreate(savedInstanceState);
        setContentView(view);

        LiveData<Pair<Integer, Integer>> liveData = view.getData();

        liveData.observe(this, new Observer<Pair<Integer, Integer>>() {
            @Override
            public void onChanged(@Nullable Pair<Integer, Integer> value) {
                //toast = Toast.makeText(getApplicationContext(), "RED: " + value, Toast.LENGTH_SHORT);
                //toast.show();
                assert value != null;
                Log.v("Score", "RED: " + value.first + "\nBLUE: " + value.second);
            }
        });

    }
}
