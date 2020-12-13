package ba.unsa.etf.rpr.p1;

import java.util.Map;

public class StranaMasina extends Masina{

    public StranaMasina(String naziv, int serijski) {
        super(naziv, serijski);
    }

    public StranaMasina(String naziv, int serijski, Map<String, Integer> materijali) {
        super(naziv, serijski, materijali);
    }
}
