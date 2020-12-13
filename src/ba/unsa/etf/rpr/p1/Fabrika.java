package ba.unsa.etf.rpr.p1;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Fabrika {
    private List<Masina> masine = new ArrayList<>();

    public Map<Masina, String> najviseProizvoda(){
        TreeMap<Masina,String> rezultat = new TreeMap<>();
        for(Masina m : masine){
            if(m.isUpaljena() && m.getMaterijali().size()!=0) rezultat.put(m,m.dajImeNajkoristenijegMaterijala());
        }
        return rezultat;
    }
    public Masina dodajKupljenuMasinu(String kupljena, int i) {
        StranaMasina dm = new StranaMasina(kupljena,i);
        for(Masina m : masine){
            if(m.getSerijski() == i) return null;
        }
        masine.add(dm);
        return dm;
    }

    public Masina dodajDomacuMasinu(String domaca, int i) {
        DomacaMasina dm = new DomacaMasina(domaca,i);
        boolean trebaDodati = true;
        for(Masina m : masine){
            if(m.getSerijski() == i){
                trebaDodati=false;

            }
        }
        if(trebaDodati)masine.add(dm);
        return dm;
    }

    public void dodajMaterijal(String kupljena, String m5, int kol) {
        boolean dodano=false;
        for(int i=0; i<masine.size(); i++){
            if(masine.get(i).getNaziv().equals(kupljena)){
                dodano=true;
                masine.get(i).registrujMaterijal(m5,kol);
            }
        }
    if(!dodano) throw new IllegalArgumentException();
    }


    public Set<Masina> dajMasine(Predicate<Masina> filter1) {
        TreeSet<Masina> m= new TreeSet<>(new Comparator<Masina>() {
            @Override
            public int compare(Masina o1, Masina o2) {
               if(o1.preostaloSati() < o2.preostaloSati()) return 1;
               else if(o1.preostaloSati() > o2.preostaloSati()) return -1;
               return 0;
            }
        });
        for(Masina ma : masine){
            m.add(ma);
        }
        if(filter1==null)return m;
        return m.stream().filter(filter1).collect(Collectors.toSet());
    }

    public Map<Masina, Integer> cijenaZaMaterijal(String m1) {
        return null;
    }
}
