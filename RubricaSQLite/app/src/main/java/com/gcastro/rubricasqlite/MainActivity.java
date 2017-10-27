package com.gcastro.rubricasqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // === @todo: Richiamare Custom Adapter
        // === (vedi DatabaseHandler.getAllAnagrafica())
        DatabaseHandler dbh = new DatabaseHandler(this);

        List<Contatto> listaContatti = dbh.getAllAnagrafica();

        // === Iterable
        for(Contatto row : listaContatti)
        {
            String output = "ID:" + row.getId()
                           +" - Nominativo:" + row.getNome()
                           +" - Telefono:" + row.getTelefono()
                    ;

            Log.d("MainActivity", output);
        }

        // === @todo: Popolare la ListView

    }

    public void manageInserisciNuovo(View view) {
        Intent frmInserisciNuovo = new Intent(
                MainActivity.this,
                FormActivity.class
        );

        startActivity(frmInserisciNuovo);

    }
}
