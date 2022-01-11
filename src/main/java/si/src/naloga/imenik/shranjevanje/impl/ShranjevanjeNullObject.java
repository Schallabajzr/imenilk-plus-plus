package si.src.naloga.imenik.shranjevanje.impl;

import si.src.naloga.core.ShranjevanjeStrategy;
import si.src.naloga.imenik.model.Kontakt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShranjevanjeNullObject implements ShranjevanjeStrategy<Kontakt> {

    private static final String SHRANJEVANJE_MESSAGE = "Shranjevanje ni na voljo izberite nacin shranjevanja.";

    @Override
    public String vrniOpis() {
        return "N za brez shranjevanja";
    }

    @Override
    public List<Kontakt> shrani(Collection<Kontakt> podatki) {
        System.out.println(SHRANJEVANJE_MESSAGE);
        return new ArrayList<>();
    }

    @Override
    public List<Kontakt> pridobi() {
        System.out.println(SHRANJEVANJE_MESSAGE);
        return new ArrayList<>();
    }
}
