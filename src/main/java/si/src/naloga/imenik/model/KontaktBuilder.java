package si.src.naloga.imenik.model;

public class KontaktBuilder {
    private String ime;
    private String priimek;
    private String naslov;
    private String elektronskaPosta;
    private String telefon;
    private String mobilniTelefon;
    private String opomba;

    public KontaktBuilder setIme(String ime) {
        this.ime = ime;
        return this;
    }

    public KontaktBuilder setPriimek(String priimek) {
        this.priimek = priimek;
        return this;
    }

    public KontaktBuilder setNaslov(String naslov) {
        this.naslov = naslov;
        return this;
    }

    public KontaktBuilder setElektronskaPosta(String elektronskaPosta) {
        this.elektronskaPosta = elektronskaPosta;
        return this;
    }

    public KontaktBuilder setTelefon(String telefon) {
        this.telefon = telefon;
        return this;
    }

    public KontaktBuilder setMobilniTelefon(String mobilniTelefon) {
        this.mobilniTelefon = mobilniTelefon;
        return this;
    }

    public KontaktBuilder setOpomba(String opomba) {
        this.opomba = opomba;
        return this;
    }

    public Kontakt createKontakt() {
        return new Kontakt(ime, priimek, naslov, elektronskaPosta, telefon, mobilniTelefon, opomba);
    }
}