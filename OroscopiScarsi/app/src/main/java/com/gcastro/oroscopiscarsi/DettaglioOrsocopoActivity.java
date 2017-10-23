package com.gcastro.oroscopiscarsi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import dmax.dialog.SpotsDialog;

public class DettaglioOrsocopoActivity extends AppCompatActivity {

    final String endPoint = "http://www.oroscopi.com/ws/getoroscopo.php";

    private String tipologia;
    private String segno;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OroscopoManager objOroscopo = new OroscopoManager();
        objOroscopo.interrogaWS();

        objOroscopo.getSegno();
        objOroscopo.getTipologia();


        // === Recupero gli extras dalla Activity chiamante
        this.bundle = getIntent().getExtras();
        this.segno = bundle.getString("sign");
        this.tipologia = bundle.getString("type");

        // === In base alla tipologia dell'oroscopo,
        // === scelgo il layout adeguato
        if(this.tipologia.equals("Settimana"))
        {
            setContentView(R.layout.oroscopo_settimana);
        } else {
            setContentView(R.layout.activity_dettaglio_orsocopo);
        }


        // ------------------------------------------------
        // -- Recupero Drawable Dinamico
        // ------------------------------------------------
        ImageView imgSegno = (ImageView)findViewById(R.id.imgSegno);

        int imageId = getResources()
                        .getIdentifier(
                                this.segno,
                                "drawable",
                                getPackageName());

        Log.d("DettaglioOroscopo", "Id risorsa immagine:" +imageId);



        // --- Imposto idRisorsa nella ImageView
        imgSegno.setImageResource(imageId);
        imgSegno.setBackgroundColor(Color.TRANSPARENT);
        imgSegno.setScaleType(ImageView.ScaleType.CENTER_CROP);




        // ------------------------------------------------
        // -- Gestione delle chiamate HTTP
        // ------------------------------------------------

        // --- Il client HTTP che si occupa della comunicazione
        AsyncHttpClient client = new AsyncHttpClient();

        // --- I parametri che verranno inviati dal client
        // --- all'endPoint da richiamare
        RequestParams params = new RequestParams();
        params.put("type", "giorno");
        params.put("sign", "gemelli");


        // --- Effettuo materialmente la chiamata
        RequestHandle requestHandle = client.get(endPoint, params, new TextHttpResponseHandler(){

            // Visualizziamo la dialog di Caricamento in corso
            SpotsDialog dialog;

            @Override
            public void onStart()
            {
                Log.d("MainActivity", "Chiamata HTTP partita");
                dialog = new SpotsDialog(DettaglioOrsocopoActivity.this, "Caricamento in corso");
                dialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String res)
            {
                // Caso di risposta del Server HTTP (controllare sermpre statusCode)
                // vedi elenco e tipologia degli HTTP Status Code su
                // https://it.wikipedia.org/wiki/Codici_di_stato_HTTP

                Log.d("MainActivity", "Chiamata HTTP eseguita con StatusCode:" + statusCode);
                Log.d("MainActivity", "Result:" + res);

                // === Parsing del JSON
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(res);

                // --- Verifico che il parsing sia andato a buon fine
                // --- e che viene riconosciuto un oggetto di tipo JSON
                if(element.isJsonObject())
                {
                    // === Recupero, quindi, la radice del mio JSON
                    JsonObject radice = element.getAsJsonObject();

                    // --- Recupero la chive "oroscopo" che so essere un array
                    JsonArray arOroscopo = radice.get("oroscopo").getAsJsonArray();

                    /*
                     * nel caso ho esigenza di ciclare dentro l'array
                    for(int i=0; i<arOroscopo.size(); i++)
                    {
                        JsonObject orosopoData = arOroscopo.get(i).getAsJsonObject();
                    }
                    */

                    // --- Recupero l'elemeto ZERO dell'array
                    JsonObject orosopoData = arOroscopo.get(0).getAsJsonObject();

                    // --- Recupero le chiavi dei dati che mi servono
                    String segnoOroscopo = orosopoData.get("segno").getAsString();
                    String dataOroscopo = orosopoData.get("data").getAsString();
                    String testoOroscopo = orosopoData.get("testo").getAsString();

                    // --- Visualizzo in logcat
                    Log.d("MainActivity", "Segno:" + segnoOroscopo);
                    Log.d("MainActivity", "Data:" + dataOroscopo);
                    Log.d("MainActivity", "Testo:" + testoOroscopo);

                    // --- Inserisco le informazioni sul layout
                    TextView txtTitolo = (TextView)findViewById(R.id.txtTitolo);
                    txtTitolo.setText("Oroscopo del giorno per " + segnoOroscopo.toUpperCase());

                    TextView txtTestoOroscopo = (TextView)findViewById(R.id.txtTestoOroscopo);
                    txtTestoOroscopo.setText(testoOroscopo);


                }

                // --- Chiudo la Dialog
                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t)
            {
                Log.d("MainActivity", "Chiamata HTTP fallita con StatusCode:" + statusCode);

            }

        });



    }

    public void tornaIndietro(View view) {
        Intent tornaIndietro = new Intent(DettaglioOrsocopoActivity.this, MainActivity.class);
        startActivity(tornaIndietro);

    }
}
