package si.src.naloga.imenik.telefon;

import si.src.naloga.core.ShranjevanjeStrategy;
import si.src.naloga.imenik.Imenik;
import si.src.naloga.imenik.model.Kontakt;
import si.src.naloga.imenik.shranjevanje.ShranjevanjeStrategyFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class TelefonskiImenik implements Imenik {

    private final HashMap<String, Kontakt> seznamKontaktov;

    ///default save mode is an Null Object, change its value via si.src.naloga.imenik.telefon.TelefonskiImenik.spremeniNacinShranjevanja
    private ShranjevanjeStrategy<Kontakt> shranjevanjeStrategy = new ShranjevanjeStrategyFactory().pridobiIzbiro(null);

    public TelefonskiImenik() {
        seznamKontaktov = new HashMap<>();
    }

    @Override
    public Collection<Kontakt> izpisiVseKontakte() {
        return seznamKontaktov
                .values();
    }


    @Override
    public Kontakt dodajKontakt(Kontakt kontakt) {
        kontakt.generateId();
        seznamKontaktov.putIfAbsent(kontakt.getId(), kontakt);
        return seznamKontaktov.get(kontakt.getId());
    }

    @Override
    public Kontakt urediKontakt(Kontakt kontakt) {
        seznamKontaktov.put(kontakt.getId(), kontakt);
        return seznamKontaktov.get(kontakt.getId());
    }

    @Override
    public String izbrisiKontaktPoId(String id) {
        return seznamKontaktov.remove(id) != null ? String.format("Kontakt z id %s izbrisan %n", id) : String.format("Kontakta z id %s NI bilo mogoce izbrisati %n", id);
    }

    @Override
    public Kontakt izpisiKontaktZaId(String id) {
        return seznamKontaktov.get(id);
    }

    @Override
    public int izpisiSteviloKontaktov() {
       return seznamKontaktov.size();
    }

    @Override
    public void shraniPodatke() throws IOException, SQLException {
        shranjevanjeStrategy.shrani(seznamKontaktov.values());
    }

    @Override
    public Collection<Kontakt> beriPodatke() throws IOException, ClassNotFoundException, SQLException {
        return shranjevanjeStrategy.pridobi();
    }

    @Override
    public void izvoziPodatkeVCsvDatoteko() throws IOException {
        try (FileWriter csvWriter = new FileWriter("new.csv")) {

            //todo proper csv formating
            csvWriter.append(
                    seznamKontaktov.values().stream()
                            .map(Kontakt::toString)
                            .collect(Collectors.joining(","))
            );

            csvWriter.flush();
        } catch (IOException e) {
            throw new IOException("Napaka pri pisanju v csv datoteko", e);
        }
    }

    @Override
    public void spremeniNacinShranjevanja(ShranjevanjeStrategy<Kontakt> nacinShranjevanja) {
        shranjevanjeStrategy = nacinShranjevanja;
    }
}
