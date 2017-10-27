package com.gcastro.esempiolistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
                        implements ListView.OnItemClickListener {

    private ListView lstElencoNomi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // === Creo un array manuale di nominativo
        String[] elencoInviati = new String[]{
                "Gianfranco", "Matteo", "Silvio", "Gaetano",
                "Marcello", "Serena", "Valentino", "Marco",
                "Eleonora", "Irene", "Giovanni", "Francesco",
                "Simone", "Bruno", "Andrea", "Claudio",
                "Monica", "Filippo"
        };

        // === Vado a recueperare istanza della listView
        lstElencoNomi = (ListView)findViewById(R.id.lstElencoNomi);

        // --- Gli associo l'eventListener
        lstElencoNomi.setOnItemClickListener(this);

        // === Creo un oggetto di tipo ArrayList
        ArrayList<String> invitatiList = new ArrayList<String>();

        // --- Inserisco dentro l'arrayList, l'array "elencoInvitati"
        invitatiList.addAll(Arrays.asList(elencoInviati));

        // === Creo istanza di ArrayAdapter
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                this,                   // context
                R.layout.custom_row,    // layout del rigo
                invitatiList            // ArrayList da disegnare
        );

        // === Inserisco l'ArrayAdapter dentro la ListView
        lstElencoNomi.setAdapter(listAdapter);




    }

    /**
     * Questo metodo viene eseguito al click su ogni singolo ROW
     * della listView
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // === Recupero il testo del row sulla quale si è verificato l'evento
        String itemValue = (String)lstElencoNomi.getItemAtPosition(position);

        Toast.makeText(this, itemValue, Toast.LENGTH_LONG).show();
        

    }
}
