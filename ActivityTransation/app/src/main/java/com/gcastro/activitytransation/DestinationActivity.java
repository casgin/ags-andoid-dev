package com.gcastro.activitytransation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
    }

    public void vaiIndietro(View view) {

        Intent back = new Intent(this, MainActivity.class);

        startActivity(back);
    }
}
