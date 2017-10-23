package com.gcastro.passingvalueactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String nominativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inviaDati(View view) {

        TextView editText = (TextView)findViewById(R.id.editText);
        nominativo = editText.getText().toString();

        // Recupero
        // String nomeAcrobatico = (String)((TextView)findViewById(R.id.editText)).getText().toString();

        // === Adesso ci serve la Intent
        Intent destination = new Intent(this, DestinationActivity.class);

        // === con putExtra, passo delle coppie key/value alla Intent
        // === il nostro "value" può contenere dati serializzati
        destination.putExtra("fldNominativo", nominativo);

        startActivity(destination);

    }
}
