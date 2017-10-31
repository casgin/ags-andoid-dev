package com.gcastro.rubricasqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
                            implements ListView.OnItemClickListener {

    private List listaContatti;
    static private DatabaseHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // === @todo: Richiamare Custom Adapter
        // === (vedi DatabaseHandler.getAllAnagrafica())
        dbh = new DatabaseHandler(this);


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

        // --- Event Listener per ContextMenu
        registerForContextMenu(lstContatti);

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


    /**
     * Creo il menu contestuale da abbinare alla riga di ogni row
     * della listView
     *
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        // === Imposto il titolo del menu contestuale
        menu.setHeaderTitle("Scegli un'opzione");

        // === Recupero la posizione dell'elemento selezionato
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position = info.position;

        // --- lo aggiungo come "groupId" al metodo "add"
        menu.add(position, v.getId(),0,"Chiama");
        menu.add(position, v.getId(),0,"Modifica");
        menu.add(position, v.getId(),0,"Cancella");


        Log.d("position", Integer.toString(position));

    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        // --- Recupero l'id/posizione dell'elemento selezionato
        Log.d("onContextItemSelected", Integer.toString(item.getGroupId()));
        Log.d("onContextItemSelected", item.getTitle().toString());
        Log.d("onContextItemSelected", item.getMenuInfo().toString());

        Contatto c = this.dbh.getAnagrafica_by_Id(item.getGroupId());
        // Contatto c = this.dbh.getAnagrafica_by_Id(3);



        switch (item.getTitle().toString())
        {
            case "Chiama":
                Toast.makeText(this,
                        "Hai selezionato Cancella per "+c.getId(),
                        Toast.LENGTH_LONG).show();
                break;

            case "Modifica":
                Toast.makeText(this,
                        "Hai selezionato Modifica per "+c.getId(),
                        Toast.LENGTH_LONG).show();
                break;

            case "Cancella":
                Toast.makeText(this,
                        "Hai selezionato Cancella per "+c.getNome(),
                        Toast.LENGTH_LONG).show();
                break;
        }


        return true;
    }
}
