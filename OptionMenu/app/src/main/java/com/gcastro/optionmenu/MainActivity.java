package com.gcastro.optionmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Toast avviso = new Toast(this);
        String testo = "";

        switch (item.getItemId())
        {
            case R.id.item1:
                testo = "Hai selezionato comando 1";
                break;
            case R.id.item2:
                testo = "Hai selezionato comando 2";
                break;
            case R.id.item3:
                testo = "Hai selezionato comando 3";
                break;
            case R.id.item4:
                testo = "Hai selezionato comando 4";
                break;
        }
        avviso.makeText(MainActivity.this, testo, Toast.LENGTH_LONG).show();

        return true;
    }

}
