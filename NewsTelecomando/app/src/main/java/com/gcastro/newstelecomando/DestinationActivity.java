package com.gcastro.newstelecomando;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DestinationActivity extends AppCompatActivity {

    private Bundle bundleData;
    private TextView txtTitolo, txtSottotitolo, txtTestoLungo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        // === Recuperare i dati in ingresso
        bundleData = getIntent().getExtras();

        // === Popolare il layout
        txtTitolo = (TextView)findViewById(R.id.txtTitolo);
        txtSottotitolo = (TextView)findViewById(R.id.txtSottotitolo);
        txtTestoLungo = (TextView)findViewById(R.id.txtTestoLungo);

        txtTitolo.setText(bundleData.getString("fldNomeSegno"));
        txtSottotitolo.setText(bundleData.getString("fldSottoTitolo"));
        txtTestoLungo.setText(bundleData.getString("fldTesto"));

    }
}
