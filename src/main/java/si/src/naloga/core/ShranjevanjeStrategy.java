package si.src.naloga.core;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface ShranjevanjeStrategy<T> {

    String vrniOpis();

    void shrani(List<T> podatki) throws IOException, SQLException;

    Collection<T> pridobi() throws IOException, ClassNotFoundException, SQLException;
}
