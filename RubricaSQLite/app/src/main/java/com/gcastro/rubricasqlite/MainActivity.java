package com.gcastro.rubricasqlite;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity
                            implements ListView.OnItemClickListener {

    static private List<Contatto> listaContatti;
    static private ListView lstContatti;
    static private DatabaseHandler dbh;
    static public Contatto contattoPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // === @todo: Richiamare Custom Adapter
        // === (vedi DatabaseHandler.getAllAnagrafica())
        dbh = new DatabaseHandler(this);


        this.listaContatti = dbh.getAllAnagrafica();

        // === Iterable
        for(Contatto row : this.listaContatti)
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
        this.lstContatti = (ListView)findViewById(R.id.lstContatti);

        // --- Inserisco il custom adapter alla ListView
        this.lstContatti.setAdapter(adapter);

        // --- Associo EventListener
        this.lstContatti.setOnItemClickListener(this);

        // --- Event Listener per ContextMenu
        registerForContextMenu(this.lstContatti);

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


    private void drawListaContatti_removeItem(int Id, int itemId)
    {
        Id = Id-1;

        Log.d("removeItem", Integer.toString(Id));
        Log.d("removeItem", "Count:"+Integer.toString(this.listaContatti.size()));
        this.listaContatti.remove(Id);
        Log.d("removeItem", "Count:"+Integer.toString(this.listaContatti.size()));

        // === Iterable
        for(Contatto row : this.listaContatti)
        {
            String output = "ID:" + row.getId()
                    +" - Nominativo:" + row.getNome()
                    +" - Telefono:" + row.getTelefono()
                    ;

            Log.d("removeItem", output);
        }


        // --- Ricreo il Custom Adapter, con i nuovi valori della lista
        CustomAdapter adapter = new CustomAdapter(
                this,
                R.layout.row_contatti,
                this.listaContatti
        );

        // --- Passo il nuovo CustomAdapter alla listView
        this.lstContatti.setAdapter(adapter);

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
        position += 1;
        Log.d("position", Integer.toString(position));

        // --- lo aggiungo come "groupId" al metodo "add"
        menu.add(position, v.getId(),0,"Chiama");
        menu.add(position, v.getId(),0,"Modifica");
        menu.add(position, v.getId(),0,"Cancella");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        // --- Recupero l'id/posizione dell'elemento selezionato
        Log.d("onContextItemSelected", Integer.toString(item.getGroupId()));
        Log.d("onContextItemSelected", item.getTitle().toString());
        Log.d("onContextItemSelected", item.getMenuInfo().toString());

        this.contattoPassed = this.dbh.getAnagrafica_by_Id(item.getGroupId());


        switch (item.getTitle().toString())
        {
            case "Chiama":
                // === Imposto i parametri per definire la Intent
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+this.contattoPassed.getTelefono()));


                // --- Chiedo esplicitamente autorizzazione all'esecuzione della intent
                if(
                        ActivityCompat.checkSelfPermission(
                            MainActivity.this,
                                Manifest.permission.CALL_PHONE
                        )
                        !=
                        PackageManager.PERMISSION_GRANTED
                ) {
                    // === Quindi rifiuto la permission
                    Toast.makeText(
                            this,
                            "Dare il permesso di effettuare chiamate",
                            Toast.LENGTH_LONG)
                        .show();

                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1
                    );

                } else {
                    // fai partire la intent
                    startActivity(i);

                }

                Toast.makeText(this,
                        "Hai selezionato Chiama per "+this.contattoPassed.getNome(),
                        Toast.LENGTH_LONG).show();
                break;

            case "Modifica":
                Intent frmInserisciNuovo = new Intent(
                        MainActivity.this,
                        FormActivity.class
                );

                frmInserisciNuovo.putExtra("idRecord", this.contattoPassed.getId());
                frmInserisciNuovo.putExtra("formType", "Modifica");

                startActivity(frmInserisciNuovo);


                Toast.makeText(this,
                        "Hai selezionato Modifica per "+this.contattoPassed.getId(),
                        Toast.LENGTH_LONG).show();
                break;

            case "Cancella":
                // === Faccio comparire la dialog per chiedere conferma della cancellazione
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Vuoi cancellare il record ?")
                        .setContentText("Si tratta di "+this.contattoPassed.getNome().toUpperCase())
                        .setCancelText("No, esci")
                        .setConfirmText("Si")
                        .showCancelButton(true)

                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            // --- Non intendiamo cancellare il record
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {

                                sDialog.setTitleText("Cancellazione annullata")
                                        .setContentText("Il record non viene eliminatro :)")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.ERROR_TYPE);

                            }

                        })

                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            // --- Intendiamo cancellare il record
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // === Qui devo effettivamente cancellare il record
                                // MainActivity.dbh.cancellaAnagrafica_by_Id(MainActivity.contattoPassed.getId());

                                sDialog.setTitleText("Record Cancellato!")
                                        .setContentText("Il record per "
                                                            +MainActivity.contattoPassed.getNome()
                                                            +" è stato cancellato")
                                        .setConfirmText("OK")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);

                                drawListaContatti_removeItem(MainActivity.contattoPassed.getId(),item.getItemId());
                            }

                        })
                        .show()
                        ;

                /*
                Toast.makeText(this,
                        "Hai selezionato Cancella per "+this.contattoPassed.getNome(),
                        Toast.LENGTH_LONG).show();
                */
                break;
        }


        return true;
    }
}
