package si.src.naloga.imenik.shranjevanje.impl;

import si.src.naloga.core.ShranjevanjeStrategy;
import si.src.naloga.imenik.model.Kontakt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ShranjevanjeSerializacija implements ShranjevanjeStrategy<Kontakt> {

    @Override
    public String vrniOpis() {
        return "S za serializacijo";
    }

    @Override
    public void shrani(List<Kontakt> podatki) throws IOException {
        try (FileOutputStream fout = new FileOutputStream("kontakti.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(podatki);
        } catch (IOException e) {
            throw new IOException("Tezava z serializacijo objektov", e);
        }
    }

    @Override
    public List<Kontakt> pridobi() throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream("kontakti.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (List<Kontakt>) objectInputStream.readObject();
        } catch (IOException e) {
            throw new IOException("Tezava z serializacijo objektov", e);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Kontakta ni bilo mozno serializirati");
        }
    }
}
