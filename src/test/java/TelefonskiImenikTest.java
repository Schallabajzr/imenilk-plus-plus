import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import si.src.naloga.imenik.model.Kontakt;
import si.src.naloga.imenik.model.KontaktBuilder;
import si.src.naloga.imenik.telefon.TelefonskiImenik;

class TelefonskiImenikTest {

    TelefonskiImenik telefonskiImenik;

    @BeforeEach
    void setUp() {
        telefonskiImenik = new TelefonskiImenik();
    }

    @Test
    @DisplayName("Dodaj kontakt")
    void testDodajKontakt() {

        telefonskiImenik.dodajKontakt(new KontaktBuilder()
                .setIme("Ana")
                .setPriimek("Novak")
                .setTelefon("02 20202")
                .setMobilniTelefon("112")
                .setElektronskaPosta("ana.novak@gmail.com")
                .setNaslov("Vesela ulica 123")
                .setOpomba("")
                .createKontakt());

        Assertions.assertNotNull(telefonskiImenik.izpisiVseKontakte());
    }

    @Test
    @DisplayName("Uredi kontakt")
    void testUrediKontakt() {

        Kontakt kontakt = telefonskiImenik.dodajKontakt(new KontaktBuilder()
                .setIme("Ana")
                .setPriimek("Novak")
                .setTelefon("02 20202")
                .setMobilniTelefon("112")
                .setElektronskaPosta("ana.novak@gmail.com")
                .setNaslov("Vesela ulica 123")
                .setOpomba("")
                .createKontakt());

        Kontakt posodobljen = new KontaktBuilder()
                .setId(kontakt.getId())
                .setIme("Posodobljen")
                .setPriimek(kontakt.getPriimek())
                .setTelefon(kontakt.getTelefon())
                .setMobilniTelefon(kontakt.getMobilniTelefon())
                .setElektronskaPosta(kontakt.getElektronskaPosta())
                .setNaslov(kontakt.getNaslov())
                .setOpomba(kontakt.getOpomba())
                .createKontakt();

        Assertions.assertEquals(posodobljen, telefonskiImenik.urediKontakt(posodobljen));
    }

    @Test
    @DisplayName("Izbrisi kontakt")
    void testIzbrisiKontakt() {

        Kontakt kontakt = telefonskiImenik.dodajKontakt(new KontaktBuilder()
                .setIme("Ana")
                .setPriimek("Novak")
                .setTelefon("02 20202")
                .setMobilniTelefon("112")
                .setElektronskaPosta("ana.novak@gmail.com")
                .setNaslov("Vesela ulica 123")
                .setOpomba("")
                .createKontakt());

        Assertions.assertEquals(String.format("Kontakt z id %s izbrisan %n", kontakt.getId()), telefonskiImenik.izbrisiKontaktPoId(kontakt.getId()));
    }


}
