package com.gcastro.listviewcustomrow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstAnagrafica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // === Recupero istanza della listView
        lstAnagrafica = (ListView)findViewById(R.id.lstAnagrafica);

        // === Creo istanza dell'oggetto "LinkedList"
        List list = new LinkedList();

        // --- Creo una lista hardCoded di contatti
        list.add(new Contatto("Gianfranco","Castro","gianfranco.castro@gmail.com"));
        list.add(new Contatto("Matteo","Renzi","matteo@renzi.it"));
        list.add(new Contatto("Gonzalo","Higuain","gonzalo@higuain.net"));
        list.add(new Contatto("Vasco","Rossi","vasco.rossi@hotmail.com"));
        list.add(new Contatto("Marek","Hamsik","noreply@napoli.it"));
        list.add(new Contatto("Tizio","Caio","tizio@caio.it"));
        list.add(new Contatto("Massimo","Naspardi","massimo@ags.it"));
        list.add(new Contatto("Pippo","Franco","pippo@franco.net"));
        list.add(new Contatto("Belen","Rodriguez","belen@rodriguez.it"));
        list.add(new Contatto("Ilary","Blasy","ilari.blasi@totti.org"));

        // === Utilizzo un Custom Adapter
        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_row, list);

        // === Inseisco il customAdapter dentro la listView
        lstAnagrafica.setAdapter(adapter);

    }
}
