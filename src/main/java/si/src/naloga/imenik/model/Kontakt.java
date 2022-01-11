package si.src.naloga.imenik.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public final class Kontakt implements Serializable {

    @Serial
    private static final long serialVersionUID = -6479947214633439920L;

    private final String id;
    private final String ime;
    private final String priimek;
    private final String naslov;
    private final String elektronskaPosta;
    private final String telefon;
    private final String mobilniTelefon;
    private final String opomba;

    public String getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getElektronskaPosta() {
        return elektronskaPosta;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getMobilniTelefon() {
        return mobilniTelefon;
    }

    public String getOpomba() {
        return opomba;
    }

    public Kontakt(String ime, String priimek, String naslov, String elektronskaPosta, String telefon, String mobilniTelefon, String opomba) {
        this.id = UUID.randomUUID().toString();
        this.ime = ime;
        this.priimek = priimek;
        this.naslov = naslov;
        this.elektronskaPosta = elektronskaPosta;
        this.telefon = telefon;
        this.mobilniTelefon = mobilniTelefon;
        this.opomba = opomba;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", priimek='" + priimek + '\'' +
                ", naslov='" + naslov + '\'' +
                ", elektronskaPosta='" + elektronskaPosta + '\'' +
                ", telefon='" + telefon + '\'' +
                ", mobilniTelefon='" + mobilniTelefon + '\'' +
                ", opomba='" + opomba + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Kontakt kontakt)) {
            return false;
        }
        return id.equals(kontakt.id) && ime.equals(kontakt.ime) && priimek.equals(kontakt.priimek) && naslov.equals(kontakt.naslov) && elektronskaPosta.equals(kontakt.elektronskaPosta) && telefon.equals(kontakt.telefon) && mobilniTelefon.equals(kontakt.mobilniTelefon) && Objects.equals(opomba, kontakt.opomba);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, priimek, naslov, elektronskaPosta, telefon, mobilniTelefon, opomba);
    }
}
