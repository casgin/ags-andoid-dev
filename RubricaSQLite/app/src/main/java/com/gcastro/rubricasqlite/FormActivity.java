package com.gcastro.rubricasqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FormActivity extends AppCompatActivity {

    private String formType;
    static private DatabaseHandler dbh;
    static public Contatto contattoPassed;

    TextView fldNome, fldTelefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // === Recupero il bundle
        formType = getIntent().getExtras().getString("formType");


        // === Verifico di che operazione si tratta
        // if(formType.indexOf("Modifica") != -1)
        if(formType.equals("Modifica"))
        {
            setContentView(R.layout.activity_form_modifica);

            // --- Istanziare DBH
            this.dbh = new DatabaseHandler(this);
            int recordId =getIntent().getExtras().getInt("idRecord");

            // --- Recuperare il record
            this.contattoPassed = this.dbh.getAnagrafica_by_Id(recordId);

            // --- Popolo la Maschera
            fldNome = (TextView)findViewById(R.id.fldNome);
            fldNome.setText(this.contattoPassed.getNome());

            fldTelefono = (TextView)findViewById(R.id.fldTelefono);
            fldTelefono.setText(this.contattoPassed.getTelefono());


        } else {
            setContentView(R.layout.activity_form);
        }

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


    public void makeModifica(View view)
    {
        Toast.makeText(this,"Eseguito", Toast.LENGTH_SHORT).show();

        // --- Recuperare i dati dalla maschera
        String fldNomeToUpdate = fldNome.getText().toString();
        String fldTelefonoToUpdate = fldTelefono.getText().toString();

        // --- Validare
        if(fldNomeToUpdate.trim().isEmpty() || fldTelefonoToUpdate.trim().isEmpty())
        {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText("I campi devono essere popolati!")
                    .show();
            return;
        }

        // --- Modifico gli attributi della classe
        this.contattoPassed.setNome(fldNomeToUpdate);
        this.contattoPassed.setTelefono(fldTelefonoToUpdate);

        // --- Richiamare il metodo public void aggiornaAnagrafica(Contatto rowToModify)
        this.dbh.aggiornaAnagrafica(this.contattoPassed);

        // --- Tornare alla MainActivity
        Intent back = new Intent(FormActivity.this, MainActivity.class);
        startActivity(back);

    }
}
