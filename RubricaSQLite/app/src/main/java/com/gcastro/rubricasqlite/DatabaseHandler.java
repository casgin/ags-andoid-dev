package com.gcastro.rubricasqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // === Parametri di Connessione al DB
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "rubrica";
    private static final String TABLE = "anagrafica";

    // --- Elenco dei campi della tabella "TABLE"
    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_TELEFONO = "telefono";



    public DatabaseHandler(Context context)
    {
        // --- Richiamo il costruttore della classe parent
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // === Vado a creare la struttura delle tabelle
        String CREATE_TABLE = "CREATE TABLE "+TABLE+" ("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_NOME + " TEXT,"
                    + KEY_TELEFONO + " TEXT)";

        // === Vado ad eseguire la query
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // === Vado a modificare la struttuta delle tabelle

        // --- Elminare tabella precedente
        db.execSQL("DROP TABLE "+TABLE);

        // --- Ricreare la tabella con la nuova struttura
        onCreate(db);

    }

    // === Implemetazione metodi per il CRUD


    /**
     * aggiungiAnagrafica
     * Inserisce un record di tipo Contatto su DB
     *
     * @param rowContatto
     */
    public void aggiungiAnagrafica(Contatto rowContatto)
    {
        // --- 1: Recuperare istanza del DB
        SQLiteDatabase db = this.getWritableDatabase();

        // --- Mapping dei campi e rispettivi valori da inserire
        ContentValues campi = new ContentValues();

        // --- Posso filtrare/elaborare il dato prima si associarlo
        campi.put(KEY_NOME, rowContatto.getNome().toUpperCase());
        campi.put(KEY_TELEFONO, rowContatto.getTelefono());

        // --- Inseriso i campi nella tabella
        db.insert(TABLE, null, campi);
        db.close();
    };


    /**
     * getAnagrafica_by_Id
     * Recupera un record di Contatto by Id
     *
     * @param id
     * @return
     */
    public Contatto getAnagrafica_by_Id(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // === Building della Query e relativa esecuzione
        Cursor cursor = db.query(
                TABLE,                          // Nome della tabella
                new String[] {KEY_ID, KEY_NOME, KEY_TELEFONO},  // Elenco dei campi che voglio recuperare
                KEY_ID + "=?",      // where della select (condizioni di selezione), con placeHolder
                new String[] { String.valueOf(id)},     // valore che deve avere il placeHolder
                null, null, null, null
        );

        // --- Verifico se ho trovato un record
        if(cursor!=null)
        {
            cursor.moveToFirst();
            db.close();
            return null;
        }

        Contatto resContatto = new Contatto(
                Integer.parseInt(cursor.getString(0)),      // campo id
                cursor.getString(1),                        // campo nome
                cursor.getString(2)                         // campo telefono
        );

        db.close();

        return resContatto;
    }


    /**
     * getAllAnagrafica
     * Mi restituisce tutti i record della tabella
     *
     * @return List<Contatto>
     */
    public List<Contatto> getAllAnagrafica()
    {
        // --- Recupero/apro il DB
        SQLiteDatabase db = this.getReadableDatabase();

        // --- Dichiaro un arrayList di Contatti
        List<Contatto> listaContatti = new ArrayList<Contatto>();

        // --- Vado ad Interrogare la tabella
        String query = "select * from "+TABLE;

        // --- Eseguo la query ottenendo sempre un oggetto di tipo Cursor
        Cursor cursor = db.rawQuery(query, null);

        // --- Elaboro il resultSet
        if(cursor.moveToFirst())
        {
            do {
                // --- Istanza della classe Contatto per il mapping
                Contatto row = new Contatto();
                row.setId(Integer.parseInt(cursor.getString(0)));
                row.setNome(cursor.getString(1));
                row.setTelefono(cursor.getString(2));

                listaContatti.add(row);

            } while(cursor.moveToNext());
        }
        db.close();

        return listaContatti;
    }

    public void aggiornaAnagrafica(Contatto rowToModify)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NOME, rowToModify.getNome());
        contentValues.put(KEY_TELEFONO, rowToModify.getTelefono());

        // === Build della query di Update
        db.update(
                TABLE,
                contentValues,
                KEY_ID + "=?",
                new String[]{ String.valueOf(rowToModify.getId()) }
        );
        db.close();
    }

    public void cancellaAnagrafica_by_Id(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                TABLE,
                KEY_ID + "=?",
                new String[] { String.valueOf(id) }
        );
        db.close();
    }

    /**
     * countAnagrafica
     * Voglio sapere quanti record ho nella tabella
     *
     * @return int
     */
    public int countAnagrafica()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryGetAll = "select * from " + TABLE;
        Cursor cursor = db.rawQuery(queryGetAll, null);

        int TotaleRecord = cursor.getCount();
        cursor.close();
        db.close();

        return TotaleRecord;
    }

}
