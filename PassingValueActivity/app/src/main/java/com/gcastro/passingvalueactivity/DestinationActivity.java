package com.gcastro.passingvalueactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class DestinationActivity extends AppCompatActivity {

    String fldNominativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        // === da "getExtras" vado a recuperare il valore di una chiave
        fldNominativo = getIntent().getExtras().getString("fldNominativo");
        Log.d("DestinationActivity", "fldNominativo=".concat(fldNominativo));

        TextView txtNominativo = (TextView)findViewById(R.id.txtNominativo);
        txtNominativo.setText(fldNominativo);

    }
}
