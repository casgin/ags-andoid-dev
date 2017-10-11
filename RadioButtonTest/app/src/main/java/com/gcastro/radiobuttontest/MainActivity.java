package com.gcastro.radiobuttontest;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // === Recupero il contesto relativo al layout attualmente in esecuzione
        context = getApplicationContext();

        // === Recupero il RadioGroup
        RadioGroup elencoCitta = (RadioGroup)findViewById(R.id.elencoCitta);

        // === Gli associo a runtime un EventListener e una funzione di callback
        // === per gestire la selezione
        elencoCitta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            // === vedi meccanismo delle Closures
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                String citta = "";
                switch (i)
                {
                    case R.id.fldCitta1:
                        citta = "Milano";
                        break;
                    case R.id.fldCitta2:
                        citta = "Brescia";
                        break;
                    case R.id.fldCitta3:
                        citta = "Bergamo";
                        break;
                    case R.id.fldCitta4:
                        citta = "Pavia";
                        break;
                    case R.id.fldCitta5:
                        citta = "Roma";
                        break;
                }

                // --- Andiamo a visualizzare il Toast
                Toast.makeText(
                        MainActivity.context,
                        "Hai selezionato la città ".concat(citta),
                        Toast.LENGTH_LONG
                ).show();

            }

        });

    }
}
