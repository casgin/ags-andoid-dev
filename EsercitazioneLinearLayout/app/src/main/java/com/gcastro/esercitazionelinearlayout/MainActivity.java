package com.gcastro.esercitazionelinearlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // === Dichiarare il TAG per il logcat
    private static final String TAG = "MainActivity";

    TextView fldNominativo;
    TextView fldNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // === Vado ad impostare un determinato layout
        // === alla mia Activity
        setContentView(R.layout.esercitazione_vertical_linear);
    }


    public void recuperaValori(View view) {
        // === Recupero le istanze dei due textView
        fldNominativo = (TextView)findViewById(R.id.fldNominativo);
        fldNote = (TextView)findViewById(R.id.fldNote);

        // === Recupero il contenuto di fldNominativo
        Log.d(TAG, "Nominativo: ".concat(fldNominativo.getText().toString()));
        Log.d(TAG, "Note: ".concat(fldNote.getText().toString()));

        Toast.makeText(
                this,                       // context
                "Dati cliente Inseriti",    // Messaggio
                Toast.LENGTH_SHORT          // Tipo Messaggio
        ).show();

    }


}
