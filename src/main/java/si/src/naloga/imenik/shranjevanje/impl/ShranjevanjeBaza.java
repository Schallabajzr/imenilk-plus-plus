package si.src.naloga.imenik.shranjevanje.impl;

import si.src.naloga.core.ShranjevanjeStrategy;
import si.src.naloga.imenik.model.Kontakt;

import java.util.Collection;
import java.util.List;

public class ShranjevanjeBaza implements ShranjevanjeStrategy<Kontakt> {

    @Override
    public String vrniOpis() {
         return "B za shranjevanje v bazo";
     }

    @Override
    public List shrani(Collection podatki) {
        return null;
    }

    @Override
    public List pridobi() {
        return null;
    }
}
