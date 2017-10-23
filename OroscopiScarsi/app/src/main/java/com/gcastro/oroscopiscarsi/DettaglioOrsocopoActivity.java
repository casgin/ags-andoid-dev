package com.gcastro.oroscopiscarsi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_orsocopo);

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
}
