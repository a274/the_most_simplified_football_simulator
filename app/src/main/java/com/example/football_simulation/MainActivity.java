package com.example.football_simulation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MyView view = new MyView(this);
        super.onCreate(savedInstanceState);
        setContentView(view);
        LiveData<Score> liveData = view.getData();
        liveData.observe(this, new Observer<Score>() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onChanged(@Nullable Score value) {
                assert value != null;
                Objects.requireNonNull(getSupportActionBar())
                        .setTitle(String.format("RED %d:%d BLUE", value.getCountTeamRED(), value.getCountTeamBLUE()));
                Log.v("Score", "RED: " + value.getCountTeamRED() + "\nBLUE: " + value.getCountTeamBLUE());
            }
        });

    }
}
