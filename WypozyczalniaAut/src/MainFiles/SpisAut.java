package MainFiles;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class SpisAut {
    private MenagerDanych menagerDanych;
    private HashMap<String,Samochod> samochody = new HashMap<>();

    public SpisAut(MenagerDanych menagerDanych){
        this.menagerDanych = menagerDanych;
    }


     public void dodajSamochod(Samochod samochod)
     {
         HashMap<String, Samochod> samochody = wczytajKopieSamochod();
         samochody.put(samochod.getId(),samochod);
         menagerDanych.zapiszPojazd(samochody);
     }

    public void usunPojazd(String id) {
        HashMap<String, Samochod> samochody = wczytajKopieSamochod();

        Samochod usunietySamochod = samochody.remove(id);
        if (usunietySamochod != null && usunietySamochod.isDostepny()==false) {

            System.out.println("Ten pojaz jest wypozyczony, ureguluj ta sprawe przed usunieciem go.");
        }
        else if (usunietySamochod != null && usunietySamochod.isDostepny()==true) {
            menagerDanych.zapiszPojazd(samochody);
            System.out.println("Pojazd o id " + id + " został usunięty");

        } else {
            System.out.println("Nie znaleziono pojazdu o id " + id);
        }
    }

     public void wyswietlPojazdy(){
         System.out.println("Wszystkie samochody");

         for(Samochod samochod : wczytajKopieSamochod().values()){
             System.out.println(samochod);
         }


     }

    public void wyswietlautaklienta(String idKlienta) {
        List<Samochod> auta = new ArrayList<>();

        for (Samochod samochod : wczytajKopieSamochod().values()) {
            if (samochod.getIduzytkownika() != null && samochod.getIduzytkownika().equals(idKlienta)) {
                auta.add(samochod);
            }
        }

        if (!auta.isEmpty()) {
            System.out.println("Auta przypisane do klienta o ID " + idKlienta + ":");
            for (Samochod samochod : auta) {
                System.out.println(samochod);
            }
        } else {
            System.out.println("Nie znaleziono żadnych samochodów przypisanych do klienta o ID " + idKlienta);
        }
    }


    public void wyswietldostepne(){
         System.out.println("Wszystkie dostepne pojazdy");
         for(Samochod samochod : wczytajKopieSamochod().values()){
             if(samochod.isDostepny()==true)
                 System.out.println(samochod);
         }


     }

    public void wypozycz(String id, String idk) {
        HashMap<String, Samochod> samochody = wczytajKopieSamochod();
        Samochod samochod = samochody.get(id);
        if (samochod != null&& samochod.getIduzytkownika()==null) {
            samochod.setDostepny(false);
            samochod.setIduzytkownika(idk);  // Poprawiony fragment kodu
            menagerDanych.zapiszPojazd(samochody);
            System.out.println("Pojazd o id " + id + " został wypożyczony");
        } else {
            System.out.println("Nie znaleziono pojazdu o id " + id +" lub zostal juz wypozyczony");
        }
    }

    public void zwroc(String id) {
        HashMap<String, Samochod> samochody = wczytajKopieSamochod();
        Samochod samochod = samochody.get(id);
        if (samochod != null&&samochod.getIduzytkownika()!=null) {
            samochod.setDostepny(true);
            samochod.setIduzytkownika(null); // Ustawienie wartości null
            menagerDanych.zapiszPojazd(samochody);
            System.out.println("Pojazd o id " + id + " został zwrócony");
        } else {
            System.out.println("Nie znaleziono pojazdu o id " + id+" lub nie jest on wypozyczony");
        }
    }
    public int pokazIloscWypozyczonychAut(String idKlienta) {
        int iloscAut = 0;

        for (Samochod samochod : wczytajKopieSamochod().values()) {
            if (samochod.getIduzytkownika() != null && samochod.getIduzytkownika().equals(idKlienta)) {
                iloscAut++;
            }
        }

        return iloscAut;
    }

    private HashMap<String,Samochod> wczytajKopieSamochod(){
        return new HashMap<>( menagerDanych.wczytajPojazd());
    }
}
