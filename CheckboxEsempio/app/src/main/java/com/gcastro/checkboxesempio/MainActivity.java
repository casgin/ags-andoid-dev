package com.gcastro.checkboxesempio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void makeAcquista(View view) {

        String report = "Hai Acquistato:", lista="";
        EditText fldReport = (EditText)findViewById(R.id.fldReport);

        // === Elenco delle Checkbox
        CheckBox fldShop1 = (CheckBox)findViewById(R.id.fldShop1);
        CheckBox fldShop2 = (CheckBox)findViewById(R.id.fldShop2);
        CheckBox fldShop3 = (CheckBox)findViewById(R.id.fldShop3);
        CheckBox fldShop4 = (CheckBox)findViewById(R.id.fldShop4);
        CheckBox fldShop5 = (CheckBox)findViewById(R.id.fldShop5);
        CheckBox fldShop6 = (CheckBox)findViewById(R.id.fldShop6);

        // === Verifico quali checkbox sono selezionati
        if(fldShop1.isChecked()) { lista += "\n- ".concat(fldShop1.getText().toString()); }
        if(fldShop2.isChecked()) { lista += "\n- ".concat(fldShop2.getText().toString()); }
        if(fldShop3.isChecked()) { lista += "\n- ".concat(fldShop3.getText().toString()); }
        if(fldShop4.isChecked()) { lista += "\n- ".concat(fldShop4.getText().toString()); }
        if(fldShop5.isChecked()) { lista += "\n- ".concat(fldShop5.getText().toString()); }
        if(fldShop6.isChecked()) { lista += "\n- ".concat(fldShop6.getText().toString()); }

        // === Visualizzo il report
        fldReport.setText(report.concat(lista));
        Log.d("MainActivity", report);
    }
}
