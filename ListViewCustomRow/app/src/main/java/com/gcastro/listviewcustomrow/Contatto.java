package com.gcastro.listviewcustomrow;

/**
 * Created by gcastro on 26/10/17.
 */

public class Contatto {
    private String nome;
    private String cognome;
    private String email;

    public Contatto(String fldNome, String fldCognome, String fldEmail) {
        this.nome = fldNome;
        this.cognome = fldCognome;
        this.email = fldEmail;

    }

    // === Setter
    public void setEmail(String email) {
        this.email = email;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // === Getter
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getNominativo()
    {
        String Nominativo = this.nome.toUpperCase()
                + " "
                + this.cognome.toUpperCase();

        return Nominativo;
    }
}
