package com.gcastro.intentemail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void inviaEmail(View view) {

        // === Recupero i due valori
        String oggettoEmail = (String)((TextView)findViewById(R.id.fldOggetto)).getText().toString();
        String testoEmail = (String)((TextView)findViewById(R.id.fldMessaggio)).getText().toString();

        Log.d("MainActivity", oggettoEmail);
        Log.d("MainActivity", testoEmail);

        // === Richiamo la Intent Email
        Intent email = new Intent(Intent.ACTION_SEND);

        String[] destinatari = {"ciccio@ciccio.net", "quelloli@gmail.com"};
        email.putExtra(Intent.EXTRA_EMAIL, destinatari);
        email.putExtra(Intent.EXTRA_SUBJECT, oggettoEmail);
        email.putExtra(Intent.EXTRA_TEXT, testoEmail);

        // --- E' una costante che indica la tipologia di Intent
        email.setType("message/rfc822");

        // --- Chiedo all'utente come vuole gestire questo "type" di intent
        Intent chooser = Intent.createChooser(email, "Come la vuoi inviare");

        startActivity(chooser);
    }
}
