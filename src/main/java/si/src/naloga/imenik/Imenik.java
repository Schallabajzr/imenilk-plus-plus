package si.src.naloga.imenik;

import si.src.naloga.core.ShranjevanjeStrategy;
import si.src.naloga.imenik.model.Kontakt;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface Imenik {

    /**
     * Metaoda izpiše vse kontakte
     * @return
     */
    Collection<Kontakt> izpisiVseKontakte();

    /**
     * Metaoda doda nov kontakt v imenik
     *
     * onemogočimo dodajanje dupliciranega kontakta
     * @return
     */
    Kontakt dodajKontakt(Kontakt kontakt);

    /**
     * Metoda popravi podatke na obstoječem kontaktu
     * ID kontakta ni mogoče spreminjati
     * @return
     */
    Kontakt urediKontakt(Kontakt kontakt);

    /**
     * Brisanje kontakta po ID-ju
     */
    String izbrisiKontaktPoId(String id);

    /**
     * Izpis kontakta po ID-ju
     * @return
     */
    String izpisiKontaktZaId(String id);

    /**
     * Izpis kontakta po ID-ju
     * @return
     */
    int izpisiSteviloKontaktov();

    /**
     * Serializiraj seznam kontoktov na disk.
     * Ime datoteke naj bo "kontakti.ser"
     */
    void shraniPodatke() throws IOException;

    /**
     * Pereberi serializiran seznam kontakotv iz diska
     * @return
     */
    Collection<Kontakt> beriPodatke() throws IOException, ClassNotFoundException;

    /**
     * Izvozi seznam kontakov CSV datoteko.
     * Naj uporabnik sam izbere ime izhodne datoteke.
     * @return
     */
    void izvoziPodatkeVCsvDatoteko() throws IOException;

    void spremeniNacinShranjevanja(ShranjevanjeStrategy<Kontakt> nacinShranjevanja);
}
