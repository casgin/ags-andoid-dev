package com.gcastro.rubricasqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CustomAdapter extends ArrayAdapter<Contatto> {

    public CustomAdapter(Context context, int textViewResourceId, List<Contatto> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // === Gestisco la lettura della "row_contatti" e la initetto per il disegno
        // === nella list View
        LayoutInflater inflater = (LayoutInflater)getContext()
                                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // === Inserisco/leggo la nostra row "custom" e vado a sovrascrivere
        // === l'oggetto "convertView" che rappresenta il row "standard" della ListView
        convertView = inflater.inflate(R.layout.row_contatti, null);

        // --- Vado a recuperare le due labl
        TextView lblNominativo = (TextView)convertView.findViewById(R.id.lblNominativo);
        TextView lblTefono = (TextView)convertView.findViewById(R.id.lblTelefono);
        TextView hidden_ContattoId = (TextView)convertView.findViewById(R.id.hidden_ContattoId);

        // --- Vado a Recuperare istanza della classe Entity Contatti
        Contatto c = getItem(position);

        // --- Popolo le label
        lblNominativo.setText(c.getNome());
        lblTefono.setText(c.getTelefono());
        hidden_ContattoId.setText(Integer.toString(c.getId()));

        return convertView;
    }
}


