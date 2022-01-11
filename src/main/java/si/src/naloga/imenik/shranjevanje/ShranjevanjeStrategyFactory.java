package si.src.naloga.imenik.shranjevanje;

import si.src.naloga.core.ShranjevanjeStrategy;
import si.src.naloga.imenik.shranjevanje.impl.ShranjevanjeBaza;
import si.src.naloga.imenik.shranjevanje.impl.ShranjevanjeNullObject;
import si.src.naloga.imenik.shranjevanje.impl.ShranjevanjeSerializacija;
import si.src.naloga.imenik.model.Kontakt;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShranjevanjeStrategyFactory {

    private final Map<String, ShranjevanjeStrategy<Kontakt>> naciniShranjevanja = new HashMap<>();

    public ShranjevanjeStrategyFactory() {
        naciniShranjevanja.put("S", new ShranjevanjeSerializacija());
        naciniShranjevanja.put("B", new ShranjevanjeBaza());
    }

    public String vrniOpcije() {
        return naciniShranjevanja.values().stream()
                .map(ShranjevanjeStrategy::vrniOpis)
                .collect(Collectors.joining(","));
    }

    public ShranjevanjeStrategy<Kontakt> pridobiIzbiro(String mode) {
        return Objects.requireNonNullElseGet(naciniShranjevanja.get(mode), ShranjevanjeNullObject::new);
    }
}
