package com.gcastro.agsbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;      // https://it.wikipedia.org/wiki/WebKit
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadPage(View view) {
        String url;
        TextView fldUrl = (TextView)findViewById(R.id.fldUrl);

        // === Recupero il valore nella stringa di testo
        url = (String)fldUrl.getText().toString();

        Log.d("MainActivity", "URL=".concat(url));

        // === Visualizzo il contenuto della URL nel browser
        WebView browser = (WebView)findViewById(R.id.fldWebView);
        browser.loadUrl(url);
    }
}
