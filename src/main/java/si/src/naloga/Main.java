package si.src.naloga;

import si.src.naloga.imenik.Imenik;
import si.src.naloga.imenik.model.Kontakt;
import si.src.naloga.imenik.model.KontaktBuilder;
import si.src.naloga.imenik.shranjevanje.ShranjevanjeStrategyFactory;
import si.src.naloga.imenik.telefon.TelefonskiImenik;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Imenik imenik = new TelefonskiImenik();

        Scanner in = new Scanner(System.in);

        // zanka za izris menija
        while (true) {
            izpisiMenu();

            switch (in.next()) {
                case "1" -> imenik.izpisiVseKontakte().stream()
                        .map(Kontakt::toString)
                        .forEach(System.out::println);

                case "2" -> {
                    System.out.println("Vnasanje kontakta");
                    imenik.dodajKontakt(vnosKontakta(in));
                }
                case "3" -> {
                    System.out.println("Urejanje kontakta");
                    imenik.urediKontakt(vnosKontakta(in));
                }
                case "4" -> {
                    System.out.println("Vnesi id:");
                    System.out.println(imenik.izbrisiKontaktPoId(in.next()));
                }
                case "5" -> {
                    System.out.println("Vnesi id:");
                    System.out.println(imenik.izpisiKontaktZaId(in.next()));
                }
                case "6" -> System.out.printf("Stevilo kontaktov je %d %n", imenik.izpisiSteviloKontaktov());
                case "7" -> imenik.shraniPodatke();
                case "8" -> imenik.beriPodatke();
                case "9" -> {
                    try {
                        imenik.izvoziPodatkeVCsvDatoteko();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "0" -> {
                    return;
                }
                case "M" -> {
                    ShranjevanjeStrategyFactory factory = new ShranjevanjeStrategyFactory();
                    System.out.println("Na izbiro imate naslednje opcije:");
                    System.out.println(factory.vrniOpcije());
                    imenik.spremeniNacinShranjevanja(factory.pridobiIzbiro(in.next()));
                }
                default -> System.out.println("Napačna izbira!!!");
            }
        }
    }

    private static Kontakt vnosKontakta(Scanner in) {
        System.out.println("-------------------");
        return new KontaktBuilder()
                .setIme(zahtevajVnos("Vnesi Ime", in))
                .setPriimek(zahtevajVnos("Vnesi priimek", in))
                .setTelefon(zahtevajVnos("Vnesi telefonsko", in))
                .setMobilniTelefon(zahtevajVnos("Vnesi telefonsko mobilnega telefona", in))
                .setElektronskaPosta(zahtevajVnos("Vnesi elektronsko posto", in))
                .setNaslov(zahtevajVnos("Vnesi naslov", in))
                .setOpomba(zahtevajVnos("Vnesi opombo", in))
                .createKontakt();
    }

    private static String zahtevajVnos(String message, Scanner scanner) {
        System.out.println(message);
        return scanner.next();
    }

    /**
     * Uporabniku izpišemo menu
     */
    private static void izpisiMenu() {
        System.out.println();
        System.out.println();
        System.out.println("Aplikacija telefonski imenik:");
        System.out.println("-----------------------------------");
        System.out.println("Akcije:");
        System.out.println("1 - izpiši vse kontakte v imeniku");
        System.out.println("2 - dodaj kontakt v imenik");
        System.out.println("3 - uredi obstoječi kontakt");
        System.out.println("4 - briši kontakt po ID-ju");
        System.out.println("5 - izpiši kontakt po ID-ju");
        System.out.println("6 - izpiši število vseh kontaktov");
        System.out.println("7 - Shrani kontakte na disk (serializacija)");
        System.out.println("8 - Preberi kontake iz serializirano datoteke");
        System.out.println("9 - Izvozi kontakte v csv");
        System.out.println("M - Spremeni nacin shranjevanja");
        System.out.println();
        System.out.println("0 - Izhod iz aplikacije");
        System.out.println("----------------------------------");
        System.out.println("Akcija: ");
    }
}
