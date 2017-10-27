package com.gcastro.listviewcustomrow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gcastro on 26/10/17.
 */

public class CustomAdapter extends ArrayAdapter {

    public CustomAdapter(Context context, int resource, List<Contatto> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent)
    {
        // === Creo instanza Inflater, per iniettare la rowcustom (layout/custom_row.xml),
        // === del disegno di ogni singolo row della listView

        LayoutInflater inflater = (LayoutInflater)getContext()
                                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // --- Sovrascrivo oggetto "contentView naturale"
        contentView = inflater.inflate(R.layout.custom_row, null);

        // --- Recuperiamo gli elementi di UI di questo CustomRow
        TextView fldNomivativo = (TextView)contentView.findViewById(R.id.fldNominativo);
        TextView fldEmail = (TextView)contentView.findViewById(R.id.fldEmail);

        // === Vado a popolare questi due TextView

        // --- Recupero istanza della classe Contatto per quell'Item sulla quale
        // --- oggetto di disegno
        Contatto contatto = (Contatto) getItem(position);

        fldNomivativo.setText(contatto.getNominativo());
        fldEmail.setText(contatto.getEmail());

        // === Restituisco la "view" di questa row "modificata"
        return contentView;
    }
}
