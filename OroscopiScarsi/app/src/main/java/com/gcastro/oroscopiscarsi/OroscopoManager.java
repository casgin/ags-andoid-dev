package com.gcastro.oroscopiscarsi;

/**
 * Created by gcastro on 23/10/17.
 */

public class OroscopoManager {

    private String segno;
    private String tipologia;

    public OroscopoManager()
    {
        this.segno = "esempio";
        this.tipologia = "esempio tipologia";
    }


    public boolean interrogaWS()
    {
        // === Faccio la chiama asincrona

        return true;
    }

    public String getSegno() {
        return segno;
    }

    public String getTipologia() {
        return tipologia;
    }
}
