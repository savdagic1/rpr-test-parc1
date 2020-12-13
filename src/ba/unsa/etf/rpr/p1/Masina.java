package ba.unsa.etf.rpr.p1;

import com.sun.source.tree.Tree;

import java.util.*;

public class Masina {
    private String naziv;
    private int serijski;
    private Map<String,Integer> materijali = new TreeMap<>();
    private int brojRadnihSati=0;
    private boolean upaljena=false;

    public Masina(String naziv, int serijski) {
        if(serijski<=0 || serijski >=100000) throw new IllegalArgumentException();
        if(naziv.length() < 2) throw new IllegalArgumentException();
        for(char c : naziv.toCharArray()){
            if(!Character.isAlphabetic(c)) throw new IllegalArgumentException();
        }


        this.naziv = naziv;
        this.serijski = serijski;
    }

    public boolean isUpaljena() {
        return upaljena;
    }

    public Map<String, Integer> getMaterijali() {
        return materijali;
    }

    public int getBrojRadnihSati() {
        return brojRadnihSati;
    }

    public Masina(String naziv, int serijski, Map<String, Integer> materijali) {
        if(serijski<=0 || serijski >=100000) throw new IllegalArgumentException();
        if(naziv.length() < 2) throw new IllegalArgumentException();
        for(char c : naziv.toCharArray()){
            if(!Character.isAlphabetic(c)) throw new IllegalArgumentException();
        }

        this.naziv = naziv;
        this.serijski = serijski;
        this.materijali = materijali;
    }
    public String dajImeNajkoristenijegMaterijala(){
        int maks = -1;
        String najkoristeniji = "";
        for(String s : materijali.keySet()){

           int cijena= materijali.get(s);

           if(cijena > maks){
               maks=cijena;
               najkoristeniji=s;
           }
        }
        return najkoristeniji;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setSerijski(int serijski) {
        this.serijski = serijski;
    }
    public int getSerijski() {
        return serijski;
    }
    public void upali() throws WrongMachineState {
        if(upaljena) throw new WrongMachineState();
        upaljena=true;
        brojRadnihSati=8;
    }

    public void ugasi() throws WrongMachineState{
        if(!upaljena) throw new WrongMachineState();
        brojRadnihSati=0;
        upaljena=false;
    }

    public int cijena(String materijal){

        try{
            int trazenaCijena = materijali.get(materijal);
            return trazenaCijena;
        }catch(Exception e){
            throw new IllegalArgumentException();
        }

    }

    public void proizvedi(String materijal) {
        try{
            int trazenaCijena = cijena(materijal);
            if(trazenaCijena<=brojRadnihSati )brojRadnihSati-=trazenaCijena;
            throw new IllegalArgumentException();

        }catch(Exception e){
            throw new IllegalArgumentException();
        }
    }

    public int preostaloSati(){
        return brojRadnihSati;
    }
    public void registrujMaterijal(String naziv, int cijena){
        if(cijena==0) throw new IllegalArgumentException();
        materijali.put(naziv,cijena);
    }
    public Set<String> dajMaterijaleMoguceZaProizvesti(){
        TreeSet<String> rezultat = new TreeSet<>();

        for(String s : materijali.keySet()){
            int cijena = materijali.get(s);
            if(cijena <= brojRadnihSati) rezultat.add(s);
        }

        return rezultat;
    }
    public Map<String, Integer> dajMogucnostProizvodnje(){
        HashMap<String,Integer> rezultat = new HashMap<>();
        for(String s :  materijali.keySet()){
            int cijena = materijali.get(s);
            rezultat.put(s,(brojRadnihSati/cijena));
        }
        return rezultat;
    }
    public void resetuj() throws WrongMachineState{
        if(!upaljena) throw new WrongMachineState();
        ugasi();
        upali();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Masina masina = (Masina) o;
        return serijski == masina.serijski;
    }


}
