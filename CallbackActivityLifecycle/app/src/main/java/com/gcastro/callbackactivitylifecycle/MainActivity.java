package com.gcastro.callbackactivitylifecycle;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;    // Necessaria per la gestione dei log
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // === Mi serve per "etichettare" il flusso di informazioni
    // === all'interno del log
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Sono il metodo onCreate");
        setContentView(R.layout.activity_main);

        TextView TT = (TextView)findViewById(R.id.textViewsnippet);


    }

    /**
     *  Viene richiamato in automatico dopo la callback di onCreate;
     *  viene utilizzata per ricostruire lo stato della UI
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        // Ricostruisce la UI da una precedente istanza (savedInstanceState)
        // lo stesso Bundle è passato anche alla callback onCreate.
        // Questa callback è chiamata se l'Activity è stata uccisa dal sistema
        // dopo che era stata visibile

        Log.d(TAG, "Sono il metodo onRestoreInstanceState");

    }

    /**
     * Chiamato per slavare i cambiamenti di UI al termine del ciclo di vita visibile dell'Activity
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        // Salvataggio dei cambiamenti della UI nel Bundle outState
        // Questo Bundle sarà successivamente passato al metodo onCreate e onRestoreInstanceState
        // se il processo è stato ucciso e fatto ripartire a runtime

        Log.d(TAG, "Sono il metodo onSaveInstanceState");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG, "Sono il metodo onStop");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG, "Sono il metodo onPause");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "Sono il metodo onDestroy");
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Log.d(TAG, "Sono il metodo onStart");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "Sono il metodo onResume");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d(TAG, "Sono il metodo onRestart");
    }
}
