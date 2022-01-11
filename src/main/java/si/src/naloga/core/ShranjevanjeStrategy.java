package si.src.naloga.core;

import java.io.IOException;
import java.util.Collection;

public interface ShranjevanjeStrategy<T> {

    String vrniOpis();

    Collection<T> shrani(Collection<T> podatki) throws IOException;

    Collection<T> pridobi() throws IOException, ClassNotFoundException;
}
