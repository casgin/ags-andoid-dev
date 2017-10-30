package com.gcastro.rubricasqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity
                            implements ListView.OnItemClickListener {

    private List listaContatti;

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

        // --- Custom Adapter
        CustomAdapter adapter = new CustomAdapter(
                this,
                R.layout.row_contatti,
                listaContatti
        );

        // --- Passare il Custon Adapter alla ListView
        ListView lstContatti = (ListView)findViewById(R.id.lstContatti);

        // --- Inserisco il custom adapter alla ListView
        lstContatti.setAdapter(adapter);

        // --- Associo EventListener
        lstContatti.setOnItemClickListener(this);

    }

    public void manageInserisciNuovo(View view) {
        Intent frmInserisciNuovo = new Intent(
                MainActivity.this,
                FormActivity.class
        );

        startActivity(frmInserisciNuovo);

    }


    /**
     * Metodo delegato alla gestione del click sulla row della listView
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        // === Mostro la posizione dell'elemento selezionato
        Log.d("MainActivity", Integer.toString(position));

        // --- Recupero oggetto Contatto selezionato
        Contatto contattoSelezionato = (Contatto)parent.getItemAtPosition(position);
        Log.d("MainActivity", contattoSelezionato.getNome());



    }
}
