package com.gcastro.oroscopiscarsi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private String tipoOroscopo = "Giorno";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // === Recuperiamo le istanze di ogni segno
        ImageView ariete = (ImageView)findViewById(R.id.btnAriete);
        ImageView toro = (ImageView)findViewById(R.id.btnToro);
        ImageView gemelli = (ImageView)findViewById(R.id.btnGemelli);
        ImageView cancro = (ImageView)findViewById(R.id.btnCancro);
        ImageView leone = (ImageView)findViewById(R.id.btnLeone);
        ImageView vergine = (ImageView)findViewById(R.id.btnVergine);
        ImageView bilancia = (ImageView)findViewById(R.id.btnBilancia);
        ImageView scorpione = (ImageView)findViewById(R.id.btnScorpione);
        ImageView sagittario = (ImageView)findViewById(R.id.btnSagittario);
        ImageView capricorno = (ImageView)findViewById(R.id.btnCapricorno);
        ImageView acquario = (ImageView)findViewById(R.id.btnAcquario);
        ImageView pesci = (ImageView)findViewById(R.id.btnPesci);

        // === Associo l'eventListener per il click
        ariete.setOnClickListener(this);
        toro.setOnClickListener(this);
        gemelli.setOnClickListener(this);
        cancro.setOnClickListener(this);
        leone.setOnClickListener(this);
        vergine.setOnClickListener(this);
        bilancia.setOnClickListener(this);
        scorpione.setOnClickListener(this);
        sagittario.setOnClickListener(this);
        capricorno.setOnClickListener(this);
        acquario.setOnClickListener(this);
        pesci.setOnClickListener(this);

    }

    /**
     * Richiamo il file XML di descrizione del topMenu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.tipi_oroscopi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.giorno:
                this.tipoOroscopo = "Giorno";
                break;

            case R.id.settimana:
                this.tipoOroscopo = "Settimana";
                break;

            default:
                this.tipoOroscopo = "Giorno";
                break;
        }

        TextView lblTipoOroscopo = (TextView)findViewById(R.id.lblTipoOroscopo);
        lblTipoOroscopo.setText(this.tipoOroscopo);

        return true;
    }


    @Override
    public void onClick(View view) {

        String sign = "";

        switch (view.getId())
        {
            case R.id.btnAcquario:
                sign = "acquario";
                break;

            case R.id.btnToro:
                sign = "toro";
                break;

            case R.id.btnGemelli:
                sign = "gemelli";
                break;

            case R.id.btnCancro:
                sign = "cancro";
                break;

            case R.id.btnLeone:
                sign = "leone";
                break;

            case R.id.btnVergine:
                sign = "vergina";
                break;

            case R.id.btnBilancia:
                sign = "bilancia";
                break;

            case R.id.btnScorpione:
                sign = "scorpione";
                break;

            case R.id.btnSagittario:
                sign = "sagittario";
                break;

            case R.id.btnCapricorno:
                sign = "capricorno";
                break;

            case R.id.btnAriete:
                sign = "ariete";
                break;

            case R.id.btnPesci:
                sign = "pesci";
                break;

        }


        // === Qui avviene la chiama all'activity DettaglioOroscopo
        // --- type, sign

        Intent dettaglioOroscopo = new Intent(MainActivity.this, DettaglioOrsocopoActivity.class);
        dettaglioOroscopo.putExtra("sign", sign);
        dettaglioOroscopo.putExtra("type", this.tipoOroscopo);

        startActivity(dettaglioOroscopo);


    }
}
