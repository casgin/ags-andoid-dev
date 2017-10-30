package com.gcastro.rubricasqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    public void makeInsersci(View view) {
        // --- istanziare DatabaseHandler
        DatabaseHandler dbh = new DatabaseHandler(this);

        // --- Recuperare i valori della maschera
        TextView fldNome = (TextView)findViewById(R.id.fldNome);
        TextView fldTelefono = (TextView)findViewById(R.id.fldTelefono);

        // --- Validarli
        String _nome = fldNome.getText().toString();
        String _telefono = fldTelefono.getText().toString();

        if(_nome.length()==0 || _nome.isEmpty())
        {
            Toast.makeText(this, "Devi inserire il nome", Toast.LENGTH_LONG).show();
            return;
        }

        if(_telefono.length()==0 || _telefono.isEmpty())
        {
            Toast.makeText(this, "Devi inserire il Telefono", Toast.LENGTH_LONG).show();
            return;
        }

        // --- Inserisire il nuovo record
        dbh.aggiungiAnagrafica(new Contatto(_nome, _telefono));

        // --- Tornare alla MainActivity
        Intent back = new Intent(FormActivity.this, MainActivity.class);
        startActivity(back);

    }
}
