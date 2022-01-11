package si.src.naloga.imenik.shranjevanje.impl;

import si.src.naloga.core.ShranjevanjeStrategy;
import si.src.naloga.imenik.model.Kontakt;
import si.src.naloga.imenik.model.KontaktBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShranjevanjeBaza implements ShranjevanjeStrategy<Kontakt> {

    private static final String URL = "jdbc:postgresql://localhost:5438/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String INSERT_KONTAKT_SQL = "INSERT INTO kontakt" +
            "  (id, ime, priimek, naslov, elektronska_posta, telefon, mobilni_telefon, opomba) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String SELECT_FROM_KONTAKT = "SELECT * FROM kontakt";

    @Override
    public String vrniOpis() {
        return "B za shranjevanje v bazo";
    }

    @Override
    public void shrani(Collection<Kontakt> podatki) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_KONTAKT_SQL)) {

            for (Kontakt kontakt : podatki) {
                preparedStatement.setString(1, kontakt.getId());
                preparedStatement.setString(2, kontakt.getIme());
                preparedStatement.setString(3, kontakt.getPriimek());
                preparedStatement.setString(4, kontakt.getNaslov());
                preparedStatement.setString(5, kontakt.getElektronskaPosta());
                preparedStatement.setString(6, kontakt.getTelefon());
                preparedStatement.setString(7, kontakt.getMobilniTelefon());
                preparedStatement.setString(8, kontakt.getOpomba());

                System.out.println(preparedStatement);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new SQLException("Napaka pri shranjevanju v bazo", e);
        }
    }

    @Override
    public List<Kontakt> pridobi() throws SQLException {
        List<Kontakt> result = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_FROM_KONTAKT)) {

            while (rs.next()) {
                result.add(new KontaktBuilder()
                        .setId(rs.getString("id"))
                        .setIme("ime")
                        .setPriimek("priimek")
                        .setNaslov("naslov")
                        .setElektronskaPosta("elektronska_posta")
                        .setTelefon("telefon")
                        .setMobilniTelefon("mobilni_telefon")
                        .setOpomba("opomba")
                        .createKontakt());
            }
        } catch (SQLException e) {
            throw new SQLException("Napaka pri pridobivanju iz baze", e);
        }

        return result;
    }
}
