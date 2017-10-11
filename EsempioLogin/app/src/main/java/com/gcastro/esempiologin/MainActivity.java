package com.gcastro.esempiologin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void makeLogin(View view) {
        String userName, passWord;

        TextView fldUsername, fldPassword;

        // === Recuperare i valori dei campi di imput
        fldUsername = (TextView)findViewById(R.id.fldUsername);
        userName = (String)fldUsername.getText().toString();

        fldPassword = (TextView)findViewById(R.id.fldPassword);
        passWord = (String)fldPassword.getText().toString();

        // === Mostrare contenuto su Log.d
        Log.d("MainActivity", "UserName=".concat(userName));
        Log.d("MainActivity", "Password=".concat(passWord));

        // === Mostrare un customToast (da layout eserno)
        LayoutInflater layoutInflater = getLayoutInflater();

        // --- Creo un nuovo viewLayout
        View viewLayout = layoutInflater.inflate(
                            R.layout.custom_toast,
                            (ViewGroup)findViewById(R.id.CustomToastLayout)
        );

        // --- Richiamare il Toast con il nuovo viewLayout
        Toast customToast = Toast.makeText(
                this,
                "Login effettuato",
                Toast.LENGTH_LONG
        );

        // --- Imposta il posizionamento al centro
        customToast.setGravity(Gravity.CENTER,0,0);

        // --- Imposta quale layout "custom" deve essere disegnato
        customToast.setView(viewLayout);

        // --- Visualizzo il Toast
        customToast.show();
    }
}
