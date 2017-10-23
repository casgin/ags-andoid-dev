package com.gcastro.activitytransation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void vaiAltraActivity(View view) {

        // === Definisco una intent "i"
        Intent i = new Intent(
               this, // il contesto
               DestinationActivity.class // istanza classe Activity da aprire
        );

        // === Avvio la visualizzazione della seconda Activity
        startActivity(i);

    }
}
