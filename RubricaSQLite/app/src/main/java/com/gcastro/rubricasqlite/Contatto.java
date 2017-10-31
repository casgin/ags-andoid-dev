package com.gcastro.rubricasqlite;

/**
 * Created by gcastro on 27/10/17.
 */

public class Contatto {

    private int id;
    private String nome;
    private String telefono;


    public Contatto()
    {

    }

    /**
     *
     * @param fldNome
     * @param fldTelefono
     */
    public Contatto(String fldNome, String fldTelefono)
    {
        this.nome = fldNome;
        this.telefono = fldTelefono;
    }

    // === Costruttore da richiamare quando leggo in record e lo devo mappare
    // === tra i campi della classe
    public Contatto(int fldId, String fldNome, String fldTelefono)
    {
        this.id = fldId;
        this.nome = fldNome;
        this.telefono = fldTelefono;
    }

    // === Getters and Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
