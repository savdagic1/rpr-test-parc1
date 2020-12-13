package ba.unsa.etf.rpr.p1;

import java.util.Map;

public class DomacaMasina extends Masina{
    public DomacaMasina(String naziv, int serijski) {
        super(naziv, serijski);
    }

    public DomacaMasina(String naziv, int serijski, Map<String, Integer> materijali) {
        super(naziv, serijski, materijali);
    }
}
