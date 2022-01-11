package si.src.naloga.core;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface ShranjevanjeStrategy<T> {

    String vrniOpis();

    void shrani(Collection<T> podatki) throws IOException, SQLException;

    Collection<T> pridobi() throws IOException, ClassNotFoundException, SQLException;
}
